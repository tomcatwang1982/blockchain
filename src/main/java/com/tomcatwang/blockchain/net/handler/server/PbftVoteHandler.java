package com.tomcatwang.blockchain.net.handler.server;

import com.tomcatwang.blockchain.ApplicationContextProvider;
import com.tomcatwang.blockchain.net.base.AbstractBlockHandler;
import com.tomcatwang.blockchain.net.body.VoteBody;
import com.tomcatwang.blockchain.net.packet.BlockPacket;
import com.tomcatwang.blockchain.net.pbft.msg.VoteMsg;
import com.tomcatwang.blockchain.net.pbft.queue.MsgQueueManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

/**
 * pbft投票处理
 *
 * @author wuweifeng wrote on 2018/3/12.
 */
public class PbftVoteHandler extends AbstractBlockHandler<VoteBody> {
    private Logger logger = LoggerFactory.getLogger(PbftVoteHandler.class);


    @Override
    public Class<VoteBody> bodyClass() {
        return VoteBody.class;
    }

    @Override
    public Object handler(BlockPacket packet, VoteBody voteBody, ChannelContext channelContext) {
        VoteMsg voteMsg = voteBody.getVoteMsg();
        logger.info("收到来自于<" + voteMsg.getAppId() + "><投票>消息，投票信息为[" + voteMsg + "]");

        ApplicationContextProvider.getBean(MsgQueueManager.class).pushMsg(voteMsg);
        return null;
    }
}
