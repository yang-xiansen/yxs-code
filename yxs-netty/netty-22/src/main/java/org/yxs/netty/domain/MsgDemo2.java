package org.yxs.netty.domain;

import org.yxs.netty.domain.protocol.Command;
import org.yxs.netty.domain.protocol.Packet;

/**
 * @author: y-xs
 * @date: 2020/12/30 16:50
 * @description: 消息载体
 */
public class MsgDemo2 extends Packet {

    private String channelId;

    private String demo2;

    public MsgDemo2() {
    }

    public MsgDemo2(String channelId, String demo2) {
        this.channelId = channelId;
        this.demo2 = demo2;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDemo2() {
        return demo2;
    }

    public void setDemo2(String demo2) {
        this.demo2 = demo2;
    }

    @Override
    public byte getCommand() {
        return Command.Demo2;
    }
}
