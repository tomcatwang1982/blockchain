package com.tomcatwang.blockchain.net.distruptor;

import com.lmax.disruptor.EventHandler;

import com.tomcatwang.blockchain.ApplicationContextProvider;
import com.tomcatwang.blockchain.net.distruptor.base.BaseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
public class DisruptorServerHandler implements EventHandler<BaseEvent> {
	
	private Logger logger = LoggerFactory.getLogger(DisruptorServerHandler.class);

    @Override
    public void onEvent(BaseEvent baseEvent, long sequence, boolean endOfBatch) throws Exception {
    	try {
    		ApplicationContextProvider.getBean(DisruptorServerConsumer.class).receive(baseEvent);
		} catch (Exception e) {
			logger.error("Disruptor事件执行异常",e);
		}
    }
}
