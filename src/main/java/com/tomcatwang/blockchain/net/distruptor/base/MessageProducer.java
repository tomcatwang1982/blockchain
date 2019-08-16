package com.tomcatwang.blockchain.net.distruptor.base;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
public interface MessageProducer {
    void publish(BaseEvent baseEvent);
}
