package com.tomcatwang.blockchain.core.bean;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static BaseData genSuccessResult() {
        return new BaseData()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static BaseData genSuccessResult(Object data) {
        return new BaseData()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static BaseData genFailResult(String message) {
        return new BaseData()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    public static BaseData genFailResult(ResultCode resultCode, String message) {
        return new BaseData()
                .setCode(resultCode)
                .setMessage(message);
    }
}
