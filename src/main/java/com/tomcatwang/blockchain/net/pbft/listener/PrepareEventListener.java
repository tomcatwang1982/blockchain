package com.tomcatwang.blockchain.net.pbft.listener;


import com.tomcatwang.blockchain.net.body.VoteBody;
import com.tomcatwang.blockchain.net.client.PacketSender;
import com.tomcatwang.blockchain.net.packet.BlockPacket;
import com.tomcatwang.blockchain.net.packet.PacketBuilder;
import com.tomcatwang.blockchain.net.packet.PacketType;
import com.tomcatwang.blockchain.net.pbft.event.MsgPrepareEvent;
import com.tomcatwang.blockchain.net.pbft.msg.VoteMsg;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
@Component
public class PrepareEventListener {
    @Resource
    private PacketSender packetSender;

    /**
     * block已经开始进入Prepare状态
     *
     * @param msgPrepareEvent
     *         msgIsPrepareEvent
     */
    @EventListener
    public void msgIsPrepare(MsgPrepareEvent msgPrepareEvent) {
        VoteMsg voteMsg = (VoteMsg) msgPrepareEvent.getSource();

        //群发消息，通知别的节点，我已对该Block Prepare
        BlockPacket blockPacket = new PacketBuilder<>().setType(PacketType.PBFT_VOTE).setBody(new
                VoteBody(voteMsg)).build();

        //广播给所有人我已Prepare
        packetSender.sendGroup(blockPacket);
    }
}
