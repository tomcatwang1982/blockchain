package com.tomcatwang.blockchain.net.pbft.listener;


import com.tomcatwang.blockchain.net.body.VoteBody;
import com.tomcatwang.blockchain.net.client.PacketSender;
import com.tomcatwang.blockchain.net.packet.BlockPacket;
import com.tomcatwang.blockchain.net.packet.PacketBuilder;
import com.tomcatwang.blockchain.net.packet.PacketType;
import com.tomcatwang.blockchain.net.pbft.event.MsgCommitEvent;
import com.tomcatwang.blockchain.net.pbft.msg.VoteMsg;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 监听block可以commit消息
 * @author tomcatwang wrote on 2019/08/19.
 */
@Component
public class CommitEventListener {
    @Resource
    private PacketSender packetSender;

    /**
     * block已经开始进入commit状态，广播消息
     *
     * @param msgCommitEvent
     *         msgCommitEvent
     */
    @EventListener
    public void msgIsCommit(MsgCommitEvent msgCommitEvent) {
        VoteMsg voteMsg = (VoteMsg) msgCommitEvent.getSource();

        //群发消息，通知所有节点，我已对该Block Prepare
        BlockPacket blockPacket = new PacketBuilder<>().setType(PacketType.PBFT_VOTE).setBody(new
                VoteBody(voteMsg)).build();

        //广播给所有人我已commit
        packetSender.sendGroup(blockPacket);
    }
}
