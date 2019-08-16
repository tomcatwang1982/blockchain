package com.tomcatwang.blockchain.block;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
public interface Operation {
    byte ADD = 1;
    byte DELETE = -1;
    byte UPDATE = 2;

}
