package com.tomcatwang.blockchain.net.pbft.event;

import com.tomcatwang.blockchain.net.pbft.msg.VoteMsg;
import org.springframework.context.ApplicationEvent;

/**
 * 消息已被验证，进入到Commit集合中
 * @author tomcatwang wrote on 2019/08/19.
 */
public class MsgCommitEvent extends ApplicationEvent {
    public MsgCommitEvent(VoteMsg source) {
        super(source);
    }
}
