package com.tomcatwang.blockchain.core.sqlparser;

import com.tomcatwang.blockchain.block.InstructionBase;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
public interface InstructionParser {
    boolean parse(InstructionBase instructionBase);
}
