package org.yxs.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yxs.mqtt.consumer.MqttSubscribe;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MqttApplication {

    @Autowired
    private MqttSubscribe mqttSubscribe;

    /**
     * 接受订阅的接口和消息,mqtt消费端
     */
    @PostConstruct
    public void consumeMqttClient() throws MqttException {
        mqttSubscribe.init();
    }

    public static void main(String[] args) {
        SpringApplication.run(MqttApplication.class, args);
    }
}
