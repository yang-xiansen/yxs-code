package org.yxs.mqtt.consumer;


import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;
import org.yxs.mqtt.callback.PushCallback;
import org.yxs.mqtt.common.MqttConnect;

@Component
@Slf4j
public class MqttSubscribe {


    public static final String HOST = "tcp://127.0.0.1:1883";

    /**
     * 测试和正式环境不要使用同样的clientId 和主题
     * 如果和正式环境一样，正式环境启动后，本地再次启动会频繁断开重连，订阅的主题一样的话，测试的数据正式环境也会消费这些数据
     */
    private static final String clientid = "测试clientId";//测试

//    private static final String clientid = "正式ClientID";//正式

    /**
     * 主题
     */
    private String topic = "测试环境主题";//测试

//    private String topic = "正式环境主题";//正式

    public MqttClient client;

    private MqttConnect mqttConnect = new MqttConnect();

    //方法实现说明 断线重连方法，如果是持久订阅，重连是不需要再次订阅，如果是非持久订阅，重连是需要重新订阅主题 取决于options.setCleanSession(true);
    // true为非持久订阅
    public void connect() throws MqttException {
        //防止重复创建MQTTClient实例
        if (client == null) {
            //就是这里的clientId，服务器用来区分用户的，不能重复,clientId不能和发布的clientId一样，否则会出现频繁断开连接和重连的问题
            //不仅不能和发布的clientId一样，而且也不能和其他订阅的clientId一样，如果想要接收之前的离线数据，这就需要将client的 setCleanSession
            // 设置为false，这样服务器才能保留它的session，再次建立连接的时候，它就会继续使用这个session了。 这时此连接clientId 是不能更改的。
            //但是其实还有一个问题，就是使用热部署的时候还是会出现频繁断开连接和重连的问题，可能是因为刚启动时的连接没断开，然后热部署的时候又进行了重连，重启一下就可以了
            //+ System.currentTimeMillis()
            client = new MqttClient(HOST, clientid, new MemoryPersistence());// MemoryPersistence设置clientid的保存形式，默认为以内存保存
            //如果是订阅者则添加回调类，发布不需要
            client.setCallback(new PushCallback(MqttSubscribe.this));
//            client.setCallback(new PushCallback());
        }
        MqttConnectOptions options = mqttConnect.getOptions();
        //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
        if (!client.isConnected()) {
            client.connect(options);
            log.info("----------连接成功");
        } else {//这里的逻辑是如果连接成功就重新连接
            client.disconnect();
            client.connect(mqttConnect.getOptions(options));
            log.info("----------连接成功");
        }
    }

    public void init() {
        try {
            connect();
            subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅某个主题，qos默认为0
     *
     * @param topic .
     */
    public void subscribe(String topic) {
        subscribe(topic, 2);
    }

    /**
     * 订阅某个主题
     *
     * @param topic .
     * @param qos   .
     */
    public void subscribe(String topic, int qos) {

        try {
            client.subscribe(topic, 2);
            //MQTT 协议中订阅关系是持久化的，因此如果不需要订阅某些 Topic，需要调用 unsubscribe 方法取消订阅关系。
//            client.unsubscribe("需要解除订阅关系的主题");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}
