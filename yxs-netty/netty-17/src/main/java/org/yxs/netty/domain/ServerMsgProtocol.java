package org.yxs.netty.domain;

/**
 * @author: yang-xiansen
 * @Date: 2021/01/02 20:30
 * @Description: 服务端端信息属性
 */
public class ServerMsgProtocol {

    /**
     * 链接信息;1自发信息、2群发消息
     */
    private int type;

    /**
     * 通信管道ID，实际使用中会映射成用户名
     */
    private String channelId;

    /**
     * 用户头像[模拟分配]
     */
    private String userHeadImg;

    /**
     * 通信消息
     */
    private String msgInfo;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }
}
