package com.tomcatwang.blockchain.core.bean;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
public class BaseData {
    private int code;
    private String message;
    private Object data;

    @Override
    public String toString() {
        return "BaseData{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public BaseData setCode(ResultCode resultCode) {
        this.code = resultCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public BaseData setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseData setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public BaseData setData(Object data) {
        this.data = data;
        return this;
    }
}
