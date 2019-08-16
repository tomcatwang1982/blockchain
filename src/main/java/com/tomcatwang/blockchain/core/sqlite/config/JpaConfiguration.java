package com.tomcatwang.blockchain.core.sqlite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxinyi wrote on 2019/08/19.
 */
//todo 这个由于springboot版本升级，需要重写这个类
@Configuration
@EnableJpaRepositories(
        basePackages = "com.tomcatwang.blockchain.core.repository",
        transactionManagerRef = "jpaTransactionManager",
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBean"
)
@EnableTransactionManagement
public class JpaConfiguration {

    @Autowired
    private HibernateProperties hibernateProperties;
    @Resource
    private JpaProperties jpaProperties;

    @Autowired
    @Bean
    public JpaTransactionManager jpaTransactionManager(@Qualifier(value = "EmbeddeddataSource") DataSource
                                                               dataSource, EntityManagerFactory
                                                               entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager
                = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        jpaTransactionManager.setDataSource(dataSource);

        return jpaTransactionManager;
    }

    @Autowired
    @Bean
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(@Qualifier(value =
            "EmbeddeddataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
        Map<String, String> result = new HashMap<String, String>();
        properties.forEach((k, v) -> {
            if (null != v) {
                String value = v.toString();
                result.put(k, value);
            } else {
                result.put(k, "");
            }
        });
        return builder.dataSource(dataSource)
                .packages("com.tomcatwang.blockchain.core.model")
                .properties(result)
                .build();
    }
}