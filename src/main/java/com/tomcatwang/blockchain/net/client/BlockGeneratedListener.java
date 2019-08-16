package com.tomcatwang.blockchain.net.client;

import com.tomcatwang.blockchain.block.Block;
import com.tomcatwang.blockchain.core.event.AddBlockEvent;
import com.tomcatwang.blockchain.net.body.RpcSimpleBlockBody;
import com.tomcatwang.blockchain.net.packet.BlockPacket;
import com.tomcatwang.blockchain.net.packet.PacketBuilder;
import com.tomcatwang.blockchain.net.packet.PacketType;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 本地新生成区块后，需要通知所有group内的节点
 * @author tomcatwang wrote on 2019/08/19.
 */
@Component
public class BlockGeneratedListener {
    @Resource
    private PacketSender packetSender;

    @Order(2)
    @EventListener(AddBlockEvent.class)
    public void blockGenerated(AddBlockEvent addBlockEvent) {
        Block block = (Block) addBlockEvent.getSource();
        BlockPacket blockPacket = new PacketBuilder<>().setType(PacketType.GENERATE_COMPLETE_REQUEST).setBody(new
                RpcSimpleBlockBody(block.getHash())).build();

        //广播给其他人做验证
        packetSender.sendGroup(blockPacket);
    }
}
