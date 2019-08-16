package com.tomcatwang.blockchain.net.handler.client;

import com.tomcatwang.blockchain.ApplicationContextProvider;
import com.tomcatwang.blockchain.block.Block;
import com.tomcatwang.blockchain.block.check.CheckerManager;
import com.tomcatwang.blockchain.core.event.AddBlockEvent;
import com.tomcatwang.blockchain.net.base.AbstractBlockHandler;
import com.tomcatwang.blockchain.net.body.RpcBlockBody;
import com.tomcatwang.blockchain.net.body.RpcCheckBlockBody;
import com.tomcatwang.blockchain.net.client.PacketSender;
import com.tomcatwang.blockchain.net.packet.BlockPacket;
import com.tomcatwang.blockchain.net.packet.NextBlockPacketBuilder;
import com.tomcatwang.blockchain.net.pbft.queue.NextBlockQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

/**
 * 对方根据我们传的hash，给我们返回的block
 *
 * @author tomcatwang wrote on 2019/08/19.
 */
public class FetchBlockResponseHandler extends AbstractBlockHandler<RpcBlockBody> {
    private Logger logger = LoggerFactory.getLogger(TotalBlockInfoResponseHandler.class);

    @Override
    public Class<RpcBlockBody> bodyClass() {
        return RpcBlockBody.class;
    }

    @Override
    public Object handler(BlockPacket packet, RpcBlockBody rpcBlockBody, ChannelContext channelContext) {
        logger.info("收到来自于<" + rpcBlockBody.getAppId() + ">的回复，Block为：" + Json.toJson(rpcBlockBody));

        Block block = rpcBlockBody.getBlock();
        //如果为null，说明对方也没有该Block
        if (block == null) {
            logger.info("对方也没有该Block");
        } else {
            //此处校验传过来的block的合法性，如果合法，则更新到本地，作为next区块
        	if(ApplicationContextProvider.getBean(NextBlockQueue.class).pop(block.getHash()) == null) return null;
        	
            CheckerManager checkerManager = ApplicationContextProvider.getBean(CheckerManager.class);
            RpcCheckBlockBody rpcCheckBlockBody = checkerManager.check(block);
            //校验通过，则存入本地DB，保存新区块
            if (rpcCheckBlockBody.getCode() == 0) {
                ApplicationContextProvider.publishEvent(new AddBlockEvent(block));
                //继续请求下一块
                BlockPacket blockPacket = NextBlockPacketBuilder.build();
                ApplicationContextProvider.getBean(PacketSender.class).sendGroup(blockPacket);
            }
        }

        return null;
    }
}
