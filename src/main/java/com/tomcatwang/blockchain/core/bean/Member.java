package com.tomcatwang.blockchain.core.bean;


import java.util.Date;

/**
 * 联盟的成员
 * @author tomcatwang wrote on 2019/08/19.
 */
public class Member {
    /**
     * 成员id，用于校验该客户是否合法，客户端启动时需要带着该值。一个公司可能有多个appId，相当于多个服务器节点
     */
    private String appId;
    /**
     * 成员名
     */
    private String name;
    /**
     * ip（可不设置，由该成员客户端启动后自行检测）
     */
    private String ip;

    private Date createTime;

    private Date updateTime;

    private String tioServerPort = "";

    @Override
    public String toString() {
        return "Member{" +
                "appId='" + appId + '\'' +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTioServerPort() {
        return tioServerPort;
    }

    public void setTioServerPort(String tioServerPort) {
        this.tioServerPort = tioServerPort;
    }

}
