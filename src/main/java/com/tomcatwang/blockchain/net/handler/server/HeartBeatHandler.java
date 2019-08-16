package com.tomcatwang.blockchain.net.handler.server;

import com.tomcatwang.blockchain.net.base.AbstractBlockHandler;
import com.tomcatwang.blockchain.net.body.HeartBeatBody;
import com.tomcatwang.blockchain.net.packet.BlockPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

/**
 * 客户端心跳包
 * @author wuweifeng wrote on 2018/3/12.
 */
@Deprecated
public class HeartBeatHandler extends AbstractBlockHandler<HeartBeatBody> {
    private Logger logger = LoggerFactory.getLogger(HeartBeatHandler.class);

    @Override
    public Class<HeartBeatBody> bodyClass() {
        return HeartBeatBody.class;
    }

    @Override
    public Object handler(BlockPacket packet, HeartBeatBody heartBeatBody, ChannelContext channelContext) throws Exception {
        logger.info("收到<心跳包>消息", heartBeatBody.getText());

        return null;
    }
}
