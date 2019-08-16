package com.tomcatwang.blockchain.net.pbft.queue;

import com.tomcatwang.blockchain.ApplicationContextProvider;
import com.tomcatwang.blockchain.net.pbft.VoteType;
import com.tomcatwang.blockchain.net.pbft.msg.VoteMsg;
import org.springframework.stereotype.Component;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
@Component
public class MsgQueueManager {

    public void pushMsg(VoteMsg voteMsg) {
    	BaseMsgQueue baseMsgQueue = null;
        switch (voteMsg.getVoteType()) {
            case VoteType
                    .PREPREPARE:
                baseMsgQueue = ApplicationContextProvider.getBean(PreMsgQueue.class);
                break;
            case VoteType.PREPARE:
                baseMsgQueue = ApplicationContextProvider.getBean(PrepareMsgQueue.class);
                break;
            case VoteType.COMMIT:
                baseMsgQueue = ApplicationContextProvider.getBean(CommitMsgQueue.class);
                break;
            default:
                break;
        }
        if(baseMsgQueue != null) {
        	baseMsgQueue.push(voteMsg);
        }
    }
}
