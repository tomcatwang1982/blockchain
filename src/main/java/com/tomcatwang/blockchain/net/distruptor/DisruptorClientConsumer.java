package com.tomcatwang.blockchain.net.distruptor;

import cn.hutool.core.util.StrUtil;
import com.tomcatwang.blockchain.common.AppId;
import com.tomcatwang.blockchain.net.base.AbstractBlockHandler;
import com.tomcatwang.blockchain.net.body.BaseBody;
import com.tomcatwang.blockchain.net.distruptor.base.BaseEvent;
import com.tomcatwang.blockchain.net.distruptor.base.MessageConsumer;
import com.tomcatwang.blockchain.net.handler.client.FetchBlockResponseHandler;
import com.tomcatwang.blockchain.net.handler.client.NextBlockResponseHandler;
import com.tomcatwang.blockchain.net.handler.client.TotalBlockInfoResponseHandler;
import com.tomcatwang.blockchain.net.packet.BlockPacket;
import com.tomcatwang.blockchain.net.packet.PacketType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.tio.utils.json.Json;

import java.util.HashMap;
import java.util.Map;

/**
 * 所有server发来的消息都在这里处理
 * @author tomcatwang wrote on 2019/08/19.
 */
@Component
public class DisruptorClientConsumer implements MessageConsumer {
    private static Map<Byte, AbstractBlockHandler<?>> handlerMap = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(getClass());

    static {
        handlerMap.put(PacketType.TOTAL_BLOCK_INFO_RESPONSE, new TotalBlockInfoResponseHandler());
        handlerMap.put(PacketType.NEXT_BLOCK_INFO_RESPONSE, new NextBlockResponseHandler());
        handlerMap.put(PacketType.FETCH_BLOCK_INFO_RESPONSE, new FetchBlockResponseHandler());
    }

    @Override
    public void receive(BaseEvent baseEvent) throws Exception {
        BlockPacket blockPacket = baseEvent.getBlockPacket();
        Byte type = blockPacket.getType();
        AbstractBlockHandler<?> blockHandler = handlerMap.get(type);
        if (blockHandler == null) {
            return;
        }

        //消费消息
        BaseBody baseBody = Json.toBean(new String(blockPacket.getBody()), BaseBody.class);
        //logger.info("收到来自于<" + baseBody.getAppId() + ">针对msg<" + baseBody.getResponseMsgId() + ">的回应");

        String appId = baseBody.getAppId();
        if (StrUtil.equals(AppId.value, appId)) {
            //是本机
            //return;
        }

        blockHandler.handler(blockPacket, baseEvent.getChannelContext());
    }
}
