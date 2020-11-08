package org.yxs.mqtt.provider;


import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;
import org.yxs.mqtt.common.MqttConnect;

/**
 * @Description: 发布者 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
 * @Author: yang-xiansen
 * @Date: 2020/09/07 15:21
 */
@Component
@Slf4j
public class MqttServer {

    public static final String HOST = "tcp://127.0.0.1:1883";

    private static final String clientId = "server";

    public MqttClient client;
    public MqttTopic topic;

    public MqttMessage message;

    private static MqttConnect mqttConnect = new MqttConnect();

    public MqttServer() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
//        client = new MqttClient(HOST, clientid, new MemoryPersistence());
        connect();

    }


    public void connect() throws MqttException {
        //防止重复创建MQTTClient实例
        if (client == null) {
            //就是这里的clientId，服务器用来区分用户的，不能重复
            client = new MqttClient(HOST, clientId, new MemoryPersistence());// MemoryPersistence设置clientid的保存形式，默认为以内存保存

            //设置回调
//            client.setCallback(new PushCallback());
        }
        MqttConnectOptions options = mqttConnect.getOptions();
        //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
        if (!client.isConnected()) {
            client.connect(options);
            log.info("---------------------连接成功");
        } else {//这里的逻辑是如果连接成功就重新连接
            client.disconnect();
            client.connect(mqttConnect.getOptions(options));
            log.info("---------------------连接成功");
        }
    }


    public boolean publish(MqttTopic topic, MqttMessage message) throws MqttPersistenceException,
        MqttException {

        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! "
            + token.isComplete());
        return token.isComplete();
    }


    /**
     * @param topic
     * @param data
     * @Description: MQTT发送指令
     * @return: void
     * @Author: yang-xiansen
     * @Date: 2020/09/07 15:22
     */
    public static void sendMQTTMessage(String topic, String data) throws MqttException {

        MqttServer server = new MqttServer();
        server.topic = server.client.getTopic(topic);
        server.message = new MqttMessage();
        //消息等级
        //level 0：最多一次的传输
        //level 1：至少一次的传输，(鸡肋)
        //level 2： 只有一次的传输
        server.message.setQos(2);
        //如果重复消费，则把值改为true,然后发送一条空的消息，之前的消息就会覆盖，然后在改为false
        server.message.setRetained(false);

        server.message.setPayload(data.getBytes());
        server.publish(server.topic, server.message);

    }

    public static void main(String[] args) throws MqttException {
        sendMQTTMessage("你的发布主题", "");
    }

}
