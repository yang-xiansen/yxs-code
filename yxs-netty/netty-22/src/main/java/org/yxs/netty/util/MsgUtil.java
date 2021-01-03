package org.yxs.netty.util;

import org.yxs.netty.domain.MsgDemo1;
import org.yxs.netty.domain.MsgDemo2;
import org.yxs.netty.domain.MsgDemo3;

public class MsgUtil {

    public static MsgDemo1 buildMsg1(String channelId, String msgContent) {
        return new MsgDemo1(channelId, msgContent);
    }

    public static MsgDemo2 buildMsg2(String channelId, String msgContent) {
        return new MsgDemo2(channelId, msgContent);
    }

    public static MsgDemo3 buildMsg3(String channelId, String msgContent) {
        return new MsgDemo3(channelId, msgContent);
    }
}
