package org.yxs.mqtt.callback;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.yxs.mqtt.consumer.MqttSubscribe;

/**
 * 发布消息的回调类
 *
 * 必须实现MqttCallback的接口并实现对应的相关接口方法CallBack 类将实现 MqttCallBack。
 * 每个客户机标识都需要一个回调实例。在此示例中，构造函数传递客户机标识以另存为实例数据。
 * 在回调中，将它用来标识已经启动了该回调的哪个实例。
 * 必须在回调类中实现三个方法：
 *
 *  public void messageArrived(MqttTopic topic, MqttMessage message)接收已经预订的发布。
 *
 *  public void connectionLost(Throwable cause)在断开连接时调用。
 *
 *  public void deliveryComplete(MqttDeliveryToken token))
 *  接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用。
 *  由 MqttClient.connect 激活此回调。
 *
 */

@Slf4j
public class PushCallback implements MqttCallback {

    /**
     * 主题
     */
    private String topic = "测试环境主题";//测试

//    private String topic = "正式环境主题";//正式

    private MqttSubscribe mqttSubscribe;
//    private MQTTSubsribe mqttSubsribe = new MQTTSubsribe();

    public PushCallback(MqttSubscribe subscribe) {
        this.mqttSubscribe = subscribe;
    }


    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        log.info("---------------------连接断开，可以做重连");
        // deliveryComplete(null);

        while (true){
            try {//如果没有发生异常说明连接成功，如果发生异常，则死循环
                Thread.sleep(1000);
                mqttSubscribe.init();
                break;
            }catch (Exception e){
//                e.printStackTrace();
                continue;
            }
        }

    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        String result = new String(message.getPayload(),"UTF-8");
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());
        System.out.println("接收消息内容 : " + result);
        //这里可以针对收到的消息做处理
    }

}

