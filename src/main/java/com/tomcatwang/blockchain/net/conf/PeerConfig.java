package com.tomcatwang.blockchain.net.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/peer.properties")
public class PeerConfig {

    @Value("${blockchain.body.charset}")
    private String charset = "utf-8";
}
