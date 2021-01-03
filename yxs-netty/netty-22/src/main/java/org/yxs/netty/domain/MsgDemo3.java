package org.yxs.netty.domain;

import org.yxs.netty.domain.protocol.Command;
import org.yxs.netty.domain.protocol.Packet;

/**
 * @author: y-xs
 * @date: 2020/12/30 16:50
 * @description: 消息载体
 */
public class MsgDemo3 extends Packet {

    private String channelId;

    private String demo3;

    public MsgDemo3() {
    }

    public MsgDemo3(String channelId, String demo3) {
        this.channelId = channelId;
        this.demo3 = demo3;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDemo3() {
        return demo3;
    }

    public void setDemo3(String demo3) {
        this.demo3 = demo3;
    }

    @Override
    public byte getCommand() {
        return Command.Demo3;
    }
}
