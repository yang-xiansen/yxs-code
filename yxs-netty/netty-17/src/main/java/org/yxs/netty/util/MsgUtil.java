package org.yxs.netty.util;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.yxs.netty.domain.ServerMsgProtocol;

/**
 * @author: yang-xiansen
 * @Date: 2021/01/02 20:35
 * @Description: 模拟服务器端消息
 */
public class MsgUtil {

    public static TextWebSocketFrame buildMsgAll(String channelId, String msgInfo) {
        //模拟头像
        int i = Math.abs(channelId.hashCode()) % 10;

        ServerMsgProtocol msg = new ServerMsgProtocol();
        msg.setType(2); //链接信息;1自发信息、2群发消息
        msg.setChannelId(channelId);
        msg.setUserHeadImg("head" + i + ".jpg");
        msg.setMsgInfo(msgInfo);

        return new TextWebSocketFrame(JSON.toJSONString(msg));
    }

    public static TextWebSocketFrame buildMsgOwner(String channelId) {
        ServerMsgProtocol msg = new ServerMsgProtocol();
        msg.setType(1); //链接信息;1链接信息、2消息信息
        msg.setChannelId(channelId);
        return new TextWebSocketFrame(JSON.toJSONString(msg));
    }

}
