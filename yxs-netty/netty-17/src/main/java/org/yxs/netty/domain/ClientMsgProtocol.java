package org.yxs.netty.domain;

/**
 * @author: yang-xiansen
 * @Date: 2021/01/02 20:32
 * @Description: 客户端消息属性
 */
public class ClientMsgProtocol {

    /**
     * 1请求个人信息，2发送聊天信息
     */
    private int type;

    /**
     * 消息
     */
    private String msgInfo;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }
}
