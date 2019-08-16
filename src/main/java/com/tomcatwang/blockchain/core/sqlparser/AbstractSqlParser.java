package com.tomcatwang.blockchain.core.sqlparser;


import com.tomcatwang.blockchain.core.model.base.BaseEntity;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
public abstract class AbstractSqlParser<T extends BaseEntity> {
    /**
     * 解析sql的方法
     * @param operation 是什么操作
     * @param id 主键
     * @param entity 对象entity
     */
    abstract void parse(byte operation, String id, T entity);

    /**
     * 对象的类
     *
     * @return Class
     */
    abstract Class getEntityClass();
}
