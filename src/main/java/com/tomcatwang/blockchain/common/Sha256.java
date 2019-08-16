package com.tomcatwang.blockchain.common;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
public class Sha256 {
    public static String sha256(String input) {
        return DigestUtil.sha256Hex(input);
    }

}
