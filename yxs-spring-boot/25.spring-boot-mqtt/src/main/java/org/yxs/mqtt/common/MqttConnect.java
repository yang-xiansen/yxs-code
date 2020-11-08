package org.yxs.mqtt.common;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/**
 * @Description: mqtt连接
 * @Author: yang-xiansen
 * @Date: 2020/09/07 15:15
 */
public class MqttConnect {


    public static final String HOST = "tcp://127.0.0.1:1883";

    private static final String clientId = "server";

    public MqttClient client;
    public MqttTopic topic;
    private String userName = "yxs";
    private String passWord = "123456";

    //生成配置对象，用户名，密码等
    public MqttConnectOptions getOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        //设置false 断线的时候不会清除未接收的历史消息
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        options.setConnectionTimeout(10);
        //设置心跳
        options.setKeepAliveInterval(20);
        return options;
    }

    public MqttConnectOptions getOptions(MqttConnectOptions options) {

        //设置false 断线的时候不会清除未接收的历史消息
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
        return options;
    }
}
