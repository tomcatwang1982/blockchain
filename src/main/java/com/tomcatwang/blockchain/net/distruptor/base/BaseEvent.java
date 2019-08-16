package com.tomcatwang.blockchain.net.distruptor.base;


import com.tomcatwang.blockchain.net.packet.BlockPacket;
import org.tio.core.ChannelContext;

import java.io.Serializable;

/**
 * 生产、消费者之间传递消息用的event
 *
 * @author tomcatwang wrote on 2019/08/19.
 */
public class BaseEvent implements Serializable {
    private BlockPacket blockPacket;
    private ChannelContext channelContext;

    public BaseEvent(BlockPacket blockPacket, ChannelContext channelContext) {
        this.blockPacket = blockPacket;
        this.channelContext = channelContext;
    }

    public BaseEvent(BlockPacket blockPacket) {
        this.blockPacket = blockPacket;
    }

    public BaseEvent() {
    }

    public ChannelContext getChannelContext() {
        return channelContext;
    }

    public void setChannelContext(ChannelContext channelContext) {
        this.channelContext = channelContext;
    }

    public BlockPacket getBlockPacket() {
        return blockPacket;
    }

    public void setBlockPacket(BlockPacket blockPacket) {
        this.blockPacket = blockPacket;
    }
}
