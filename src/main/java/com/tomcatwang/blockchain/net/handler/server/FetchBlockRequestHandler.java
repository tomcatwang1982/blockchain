package com.tomcatwang.blockchain.net.handler.server;

import com.tomcatwang.blockchain.ApplicationContextProvider;
import com.tomcatwang.blockchain.block.Block;
import com.tomcatwang.blockchain.core.manager.DbBlockManager;
import com.tomcatwang.blockchain.net.base.AbstractBlockHandler;
import com.tomcatwang.blockchain.net.body.RpcBlockBody;
import com.tomcatwang.blockchain.net.body.RpcSimpleBlockBody;
import com.tomcatwang.blockchain.net.packet.BlockPacket;
import com.tomcatwang.blockchain.net.packet.PacketBuilder;
import com.tomcatwang.blockchain.net.packet.PacketType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;

/**
 * 请求别人某个区块的信息
 * @author tomcatwang wrote on 2019/08/19.
 */
public class FetchBlockRequestHandler extends AbstractBlockHandler<RpcSimpleBlockBody> {
    private Logger logger = LoggerFactory.getLogger(FetchBlockRequestHandler.class);

    @Override
    public Class<RpcSimpleBlockBody> bodyClass() {
        return RpcSimpleBlockBody.class;
    }

    @Override
    public Object handler(BlockPacket packet, RpcSimpleBlockBody rpcBlockBody, ChannelContext channelContext) {
        logger.info("收到来自于<" + rpcBlockBody.getAppId() + "><请求该Block>消息，block hash为[" + rpcBlockBody.getHash() + "]");
        Block block = ApplicationContextProvider.getBean(DbBlockManager.class).getBlockByHash(rpcBlockBody.getHash());

        BlockPacket blockPacket = new PacketBuilder<>().setType(PacketType.FETCH_BLOCK_INFO_RESPONSE).setBody(new
                RpcBlockBody(block)).build();
        Aio.send(channelContext, blockPacket);

        return null;
    }
}
