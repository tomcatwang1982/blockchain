package com.tomcatwang.blockchain.net.handler.server;

import com.tomcatwang.blockchain.ApplicationContextProvider;
import com.tomcatwang.blockchain.block.Block;
import com.tomcatwang.blockchain.block.check.CheckerManager;
import com.tomcatwang.blockchain.net.base.AbstractBlockHandler;
import com.tomcatwang.blockchain.net.body.RpcBlockBody;
import com.tomcatwang.blockchain.net.body.RpcCheckBlockBody;
import com.tomcatwang.blockchain.net.packet.BlockPacket;
import com.tomcatwang.blockchain.net.pbft.VoteType;
import com.tomcatwang.blockchain.net.pbft.msg.VotePreMsg;
import com.tomcatwang.blockchain.net.pbft.queue.MsgQueueManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

/**
 * 收到请求生成区块消息，进入PrePre队列
 *
 * @author tomcatwang wrote on 2019/08/19.
 */
public class GenerateBlockRequestHandler extends AbstractBlockHandler<RpcBlockBody> {
    private Logger logger = LoggerFactory.getLogger(GenerateBlockRequestHandler.class);


    @Override
    public Class<RpcBlockBody> bodyClass() {
        return RpcBlockBody.class;
    }

    @Override
    public Object handler(BlockPacket packet, RpcBlockBody rpcBlockBody, ChannelContext channelContext) {
        Block block = rpcBlockBody.getBlock();
        logger.info("收到来自于<" + rpcBlockBody.getAppId() + "><请求生成Block>消息，block信息为[" + block + "]");

        CheckerManager checkerManager = ApplicationContextProvider.getBean(CheckerManager.class);
        //对区块的基本信息进行校验，校验通过后进入pbft的Pre队列
        RpcCheckBlockBody rpcCheckBlockBody = checkerManager.check(block);
        logger.info("校验结果:" + rpcCheckBlockBody.toString());
        if (rpcCheckBlockBody.getCode() == 0) {
            VotePreMsg votePreMsg = new VotePreMsg();
            votePreMsg.setBlock(block);
            votePreMsg.setVoteType(VoteType.PREPREPARE);
            votePreMsg.setNumber(block.getBlockHeader().getNumber());
            votePreMsg.setAppId(rpcBlockBody.getAppId());
            votePreMsg.setHash(block.getHash());
            votePreMsg.setAgree(true);
            //将消息推入PrePrepare队列
            ApplicationContextProvider.getBean(MsgQueueManager.class).pushMsg(votePreMsg);
        }

        return null;
    }
}
