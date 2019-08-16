package com.tomcatwang.blockchain.net.distruptor.base;

import com.lmax.disruptor.EventFactory;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
public class BaseEventFactory implements EventFactory<BaseEvent> {
    @Override
    public BaseEvent newInstance() {
        return new BaseEvent();
    }

}
