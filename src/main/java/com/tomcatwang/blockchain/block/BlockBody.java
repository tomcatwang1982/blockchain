package com.tomcatwang.blockchain.block;

import java.util.List;

/**
 * 区块body，里面存放交易的数组
 * @author tomcatwang wrote on 2019/08/19.
 */
public class BlockBody {
    private List<Instruction> instructions;

    @Override
    public String toString() {
        return "BlockBody{" +
                "instructions=" + instructions +
                '}';
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }
}
