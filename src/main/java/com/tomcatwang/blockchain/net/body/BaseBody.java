package com.tomcatwang.blockchain.net.body;

import com.tomcatwang.blockchain.common.AppId;
import com.tomcatwang.blockchain.common.CommonUtil;

/**
 * @author tomcatwang wrote on 2019/08/19.
 */
public class BaseBody {

    /**
     * 消息发送时间
     */
    private Long time = System.currentTimeMillis();
    /**
     * 每条消息的唯一id
     */
    private String messageId = CommonUtil.generateUuid();
    /**
     * 回复的哪条消息
     */
    private String responseMsgId;
    /**
     * 自己是谁
     */
    private String appId = AppId.value;

    public BaseBody() {
    }

    /**
     * @return the time
     */
    public Long getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Long time) {
        this.time = time;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getResponseMsgId() {
        return responseMsgId;
    }

    public void setResponseMsgId(String responseMsgId) {
        this.responseMsgId = responseMsgId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "BaseBody{" +
                "time=" + time +
                ", messageId='" + messageId + '\'' +
                ", responseMsgId='" + responseMsgId + '\'' +
                '}';
    }
}
