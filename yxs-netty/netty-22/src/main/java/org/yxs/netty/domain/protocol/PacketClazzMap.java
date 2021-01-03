package org.yxs.netty.domain.protocol;

import org.yxs.netty.domain.MsgDemo1;
import org.yxs.netty.domain.MsgDemo2;
import org.yxs.netty.domain.MsgDemo3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PacketClazzMap {

    public final static Map<Byte, Class<? extends Packet>> packetTypeMap = new ConcurrentHashMap<>();

    static {
        packetTypeMap.put(Command.Demo1, MsgDemo1.class);
        packetTypeMap.put(Command.Demo2, MsgDemo2.class);
        packetTypeMap.put(Command.Demo3, MsgDemo3.class);
    }

}
