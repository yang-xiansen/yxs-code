package org.yxs.netty.domain;

import org.yxs.netty.domain.protocol.Command;
import org.yxs.netty.domain.protocol.Packet;

/**
 * @author: y-xs
 * @date: 2020/12/30 16:50
 * @description: 消息载体
 */
public class MsgDemo1 extends Packet {

    private String channelId;

    private String demo1;

    public MsgDemo1() {
    }

    public MsgDemo1(String channelId, String demo1) {
        this.channelId = channelId;
        this.demo1 = demo1;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDemo1() {
        return demo1;
    }

    public void setDemo1(String demo1) {
        this.demo1 = demo1;
    }

    @Override
    public byte getCommand() {
        return Command.Demo1;
    }
}
