package com.tomcatwang.blockchain.net.pbft.queue;

import com.tomcatwang.blockchain.ApplicationContextProvider;
import com.tomcatwang.blockchain.block.Block;
import com.tomcatwang.blockchain.core.event.AddBlockEvent;
import com.tomcatwang.blockchain.net.pbft.msg.VoteMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Confirm阶段的消息队列
 * 每个节点收到超过2f+1个不同节点（包括自己）的commit消息后，就认为该区块已经达成一致，进入committed状态，并将其持久化到区块链数据库中
 *
 * @author tomcatwang wrote on 2019/08/19.
 */
@Component
public class CommitMsgQueue extends AbstractVoteMsgQueue {
    @Resource
    private PreMsgQueue preMsgQueue;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void deal(VoteMsg voteMsg, List<VoteMsg> voteMsgs) {
        String hash = voteMsg.getHash();

        //通过校验agree数量，来决定是否在本地生成Block
        long count = voteMsgs.stream().filter(VoteMsg::isAgree).count();
        logger.info("已经commit为true的数量为:"+ count);
        if (count >= pbftAgreeSize()) {
            Block block = preMsgQueue.findByHash(hash);
            if (block == null) {
                return;
            }
            //本地落地
            voteStateConcurrentHashMap.put(hash, true);
            ApplicationContextProvider.publishEvent(new AddBlockEvent(block));
        }
    }

    /**
     * 新区块生成后，clear掉map中number比区块小的所有数据
     */
    @Order(3)
    @EventListener(AddBlockEvent.class)
    public void blockGenerated(AddBlockEvent addBlockEvent) {
        Block block = (Block) addBlockEvent.getSource();
        clearOldBlockHash(block.getBlockHeader().getNumber());
    }

}
