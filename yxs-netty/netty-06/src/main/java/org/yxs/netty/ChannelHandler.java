package org.yxs.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/29 6:49
 * @Description: 使用ChannelGroup方式进行群发消息
 * ChannelGroup中提供了一些基础的方法；添加、异常、查找、清空、发放消息、关闭等。
 * 多个群聊可以定义ConcurrentHashMap结构来存放ChannelGroup
 */
public class ChannelHandler {

    //用于存放用户channel信息 也可以建立map模拟不同的消息群
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
