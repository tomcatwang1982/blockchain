package com.tomcatwang.blockchain.core.sqlite.config;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 构建一个存放表名和model实体class的对应关系，如account_entity:AccountEntity.class
 *
 * @author wangxinyi wrote on 2019/08/19.
 */
@Configuration
@AutoConfigureAfter(JpaConfiguration.class)
public class ModelMetaData {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean(name = "metaMap")
    public Map<String, Class> metaMap(EntityManagerFactory factory) throws ClassNotFoundException {
        //factory.getMetamodel().
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        //SessionFactory sessionFactory = factory.unwrap(SessionFactory.class);
        Metamodel metamodel = factory.getMetamodel();
        Set<EntityType<?>> entities = metamodel.getEntities();
        //metamodel.getManagedTypes();
        List<Class> classes = entities.stream()
                .map(EntityType::getJavaType)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Map<String, Class> map = new HashMap<String, Class>(classes.size());
        for (Class cls : classes) {
            String tableName = cls.getSimpleName().toLowerCase().replaceAll("entity", "");
            map.put(tableName, Class.forName(cls.getName()));
        }


        //Map<String, ClassMetadata> metaMap = sessionFactory.getAllClassMetadata();
//        classes.forEach(cls -> {
//            AbstractEntityPersister classMetadata = (AbstractEntityPersister) cls;
//            String tableName = classMetadata.getTableName().toLowerCase();
//            int index = tableName.indexOf(".");
//            if (index >= 0) {
//                tableName = tableName.substring(index + 1);
//            }
//            map.put(tableName, ((AbstractEntityPersister) cls).getMappedClass());
//        });
        //

//        for (String key : metaMap.keySet()) {
//            AbstractEntityPersister classMetadata = (AbstractEntityPersister) metaMap
//                    .get(key);
//            String tableName = classMetadata.getTableName().toLowerCase();
//            int index = tableName.indexOf(".");
//            if (index >= 0) {
//                tableName = tableName.substring(index + 1);
//            }
//            map.put(tableName, Class.forName(key));
//        }
        return map;
    }

}
