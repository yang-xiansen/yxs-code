package org.yxs.netty.util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: y-xs
 * @date: 2020/12/30 10:40
 * @description: protostuff 序列化工具
 */
public class SerializationUtil {

    private static Map<Class<?>, Schema<?>> schemaMap = new ConcurrentHashMap<>();

    private static Objenesis objenesis = new ObjenesisStd();

    private SerializationUtil() {
    }

    /**
     * @param obj
     * @author: y-xs
     * @date: 2020/12/30 11:20
     * @description: 序列化（对象->字节数组）
     * @return: byte[]
     */
    public static <T> byte[] serialize(T obj) {
        Class<T> tClass = (Class<T>) obj.getClass();
        LinkedBuffer linkedBuffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE); //设置大小
        try {
            Schema<T> schema = getSchema(tClass);
            //Protostuff序列化对象
            return ProtostuffIOUtil.toByteArray(obj, schema, linkedBuffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            linkedBuffer.clear();
        }

    }

    /**
     * @param data
     * @param tClass
     * @author: y-xs
     * @date: 2020/12/30 11:31
     * @description: 反序列化（字节数组 -> 对象）
     * @return: T
     */
    public static <T> T deserialize(byte[] data, Class<T> tClass) {
        try {
            T message = objenesis.newInstance(tClass);
            Schema<T> schema = getSchema(tClass);
            //Protostuff反序列化
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }


    private static <T> Schema<T> getSchema(Class<T> tClass) {
        Schema<T> schema = (Schema<T>) schemaMap.get(tClass);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(tClass);
            schemaMap.put(tClass, schema);
        }
        return schema;
    }


}
