package com.tomcatwang.blockchain.net.packet;

import com.tomcatwang.blockchain.ApplicationContextProvider;
import com.tomcatwang.blockchain.core.event.ClientRequestEvent;
import com.tomcatwang.blockchain.core.manager.DbBlockManager;
import com.tomcatwang.blockchain.net.body.RpcSimpleBlockBody;

/**
 * 构建向别的节点请求next block的builder.带着自己最后一个block的hash
 * @author tomcatwang wrote on 2019/08/19.
 */
public class NextBlockPacketBuilder {
    public static BlockPacket build() {
        return build(null);
    }

    public static BlockPacket build(String responseId) {
        String hash = ApplicationContextProvider.getBean(DbBlockManager.class).getLastBlockHash();

        RpcSimpleBlockBody rpcBlockBody = new RpcSimpleBlockBody(hash);
        rpcBlockBody.setResponseMsgId(responseId);
        BlockPacket blockPacket = new PacketBuilder<>().setType(PacketType.NEXT_BLOCK_INFO_REQUEST).setBody
                (rpcBlockBody).build();
        //发布client请求事件
        ApplicationContextProvider.publishEvent(new ClientRequestEvent(blockPacket));
        return blockPacket;
    }

}
