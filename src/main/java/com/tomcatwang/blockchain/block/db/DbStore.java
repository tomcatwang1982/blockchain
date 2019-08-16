package com.tomcatwang.blockchain.block.db;

/**
 * key-value型DB数据库操作接口
 * @author tomcatwang wrote on 2019/08/19.
 */
public interface DbStore {
    /**
     * 数据库key value
     *
     * @param key
     *         key
     * @param value
     *         value
     */
    void put(String key, String value);

    /**
     * get By Key
     *
     * @param key
     *         key
     * @return value
     */
    String get(String key);

    /**
     * remove by key
     *
     * @param key
     *         key
     */
    void remove(String key);
}
