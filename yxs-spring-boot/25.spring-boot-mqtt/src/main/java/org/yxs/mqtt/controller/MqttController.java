package org.yxs.mqtt.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yxs.mqtt.provider.MqttServer;

@RestController
public class MqttController {

    @SneakyThrows
    @GetMapping("/send")
    public String sendMsg(@RequestParam String topic, @RequestParam String data) {
        MqttServer.sendMQTTMessage(topic, data);
        return "send success";
    }


}
