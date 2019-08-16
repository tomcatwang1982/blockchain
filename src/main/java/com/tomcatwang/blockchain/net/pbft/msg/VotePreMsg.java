package com.tomcatwang.blockchain.net.pbft.msg;

import com.tomcatwang.blockchain.block.Block;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
public class VotePreMsg extends VoteMsg {
    private Block block;

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
