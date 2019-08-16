package com.tomcatwang.blockchain.net.pbft.event;

import com.tomcatwang.blockchain.net.pbft.msg.VoteMsg;
import org.springframework.context.ApplicationEvent;

/**
 * 消息已被验证，进入到Prepare集合中
 * @author tomcatwang wrote on 2019/08/19.
 */
public class MsgPrepareEvent extends ApplicationEvent {
    public MsgPrepareEvent(VoteMsg source) {
        super(source);
    }
}
