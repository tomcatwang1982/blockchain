package com.tomcatwang.blockchain.core.service;

import com.tomcatwang.blockchain.block.PairKey;
import com.tomcatwang.blockchain.common.TrustSDK;
import com.tomcatwang.blockchain.common.exception.TrustSDKException;
import org.springframework.stereotype.Service;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
@Service
public class PairKeyService {

    /**
     * 生成公私钥对
     * @return PairKey
     * @throws TrustSDKException TrustSDKException
     */
    public PairKey generate() throws TrustSDKException {
        return TrustSDK.generatePairKey(true);
    }
}
