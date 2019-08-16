package com.tomcatwang.blockchain.net.distruptor;

import com.lmax.disruptor.EventHandler;
import com.tomcatwang.blockchain.ApplicationContextProvider;
import com.tomcatwang.blockchain.net.distruptor.base.BaseEvent;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
public class DisruptorClientHandler implements EventHandler<BaseEvent> {

    @Override
    public void onEvent(BaseEvent baseEvent, long sequence, boolean endOfBatch) throws Exception {
        ApplicationContextProvider.getBean(DisruptorClientConsumer.class).receive(baseEvent);
    }
}
