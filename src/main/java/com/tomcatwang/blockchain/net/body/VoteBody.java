package com.tomcatwang.blockchain.net.body;

import com.tomcatwang.blockchain.net.pbft.msg.VoteMsg;

/**
 * pbft投票
 * @author tomcatwang wrote on 2019/08/19.
 */
public class VoteBody extends BaseBody {
    private VoteMsg voteMsg;

    public VoteBody() {
        super();
    }

    public VoteBody(VoteMsg voteMsg) {
        super();
        this.voteMsg = voteMsg;
    }

    public VoteMsg getVoteMsg() {
        return voteMsg;
    }

    public void setVoteMsg(VoteMsg voteMsg) {
        this.voteMsg = voteMsg;
    }
}
