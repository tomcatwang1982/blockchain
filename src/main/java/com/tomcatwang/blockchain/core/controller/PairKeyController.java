package com.tomcatwang.blockchain.core.controller;

import com.tomcatwang.blockchain.common.exception.TrustSDKException;
import com.tomcatwang.blockchain.core.bean.BaseData;
import com.tomcatwang.blockchain.core.bean.ResultGenerator;
import com.tomcatwang.blockchain.core.service.PairKeyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
@Api(tags = "区块链接口", description = "公私钥接口")
@RestController
@RequestMapping("/pairKey")
public class PairKeyController {
    @Resource
    private PairKeyService pairKeyService;

    /**
     * 生成公钥私钥
     */
    @ApiOperation(value = "区块链公私钥接口", notes = "生成区块链节点公私钥", httpMethod = "GET", response = BaseData.class)
    @GetMapping("/random")
    public BaseData generate() throws TrustSDKException {
         return ResultGenerator.genSuccessResult(pairKeyService.generate());
    }
}
