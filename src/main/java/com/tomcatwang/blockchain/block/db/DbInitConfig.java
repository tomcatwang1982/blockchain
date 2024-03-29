package com.tomcatwang.blockchain.block.db;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * 配置启用哪个db，部分Windows机器用不了rocksDB，可以选择levelDB
 * @author tomcatwang wrote on 2019/08/19.
 */
@Configuration
public class DbInitConfig {

    @Value("${rocksDBStorePath}")
    private String rocksDBStorePath;

    @Bean
    @ConditionalOnProperty("db.rocksDB")
    public RocksDB rocksDB() {
        RocksDB.loadLibrary();

        Options options = new Options().setCreateIfMissing(true);
        try {
            return RocksDB.open(options, rocksDBStorePath);
        } catch (RocksDBException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    @ConditionalOnProperty("db.levelDB")
    public DB levelDB() throws IOException {
        org.iq80.leveldb.Options options = new org.iq80.leveldb.Options();
        options.createIfMissing(true);
        return Iq80DBFactory.factory.open(new File("./levelDB"), options);
    }
}
