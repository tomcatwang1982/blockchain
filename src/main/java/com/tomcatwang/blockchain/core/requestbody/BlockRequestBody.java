package com.tomcatwang.blockchain.core.requestbody;


import com.tomcatwang.blockchain.block.BlockBody;

/**
 * 生成Block时传参
 * @author tomcatwang wrote on 2019/08/19.
 */
public class BlockRequestBody {
    private String publicKey;
    private BlockBody blockBody;

    @Override
    public String toString() {
        return "BlockRequestBody{" +
                "publicKey='" + publicKey + '\'' +
                ", blockBody=" + blockBody +
                '}';
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public BlockBody getBlockBody() {
        return blockBody;
    }

    public void setBlockBody(BlockBody blockBody) {
        this.blockBody = blockBody;
    }
}
