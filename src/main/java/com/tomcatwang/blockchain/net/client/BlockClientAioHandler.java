package com.tomcatwang.blockchain.net.client;

//import com.mindata.blockchain.ApplicationContextProvider;
//import com.mindata.blockchain.socket.base.AbstractAioHandler;
//import com.mindata.blockchain.socket.distruptor.base.BaseEvent;
//import com.mindata.blockchain.socket.distruptor.base.MessageProducer;
//import com.mindata.blockchain.socket.packet.BlockPacket;
import com.tomcatwang.blockchain.ApplicationContextProvider;
import com.tomcatwang.blockchain.net.base.AbstractAioHandler;
import com.tomcatwang.blockchain.net.distruptor.base.BaseEvent;
import com.tomcatwang.blockchain.net.distruptor.base.MessageProducer;
import com.tomcatwang.blockchain.net.packet.BlockPacket;
import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;

/**
 * @author wuweifeng wrote on 2018/3/12.
 */
public class BlockClientAioHandler extends AbstractAioHandler implements ClientAioHandler {

    @Override
    public BlockPacket heartbeatPacket() {
        //心跳包的内容就是隔一段时间向别的节点获取一次下一步区块（带着自己的最新Block获取别人的next Block）
        //return NextBlockPacketBuilder.build();
        return null;
    }

    /**
     * server端返回的响应会先进到该方法，将消息全丢到Disruptor中
     */
    @Override
    public void handler(Packet packet, ChannelContext channelContext)  {
        BlockPacket blockPacket = (BlockPacket) packet;

        //使用Disruptor来publish消息。所有收到的消息都进入Disruptor，同BlockServerAioHandler
        ApplicationContextProvider.getBean(MessageProducer.class).publish(new BaseEvent(blockPacket, channelContext));
    }
}
