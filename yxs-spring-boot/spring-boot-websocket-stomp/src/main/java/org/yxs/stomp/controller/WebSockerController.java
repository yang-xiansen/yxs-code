package org.yxs.stomp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.yxs.stomp.entity.User;

@Controller
public class WebSockerController {

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * @Description: 一对多 广播消息
     * @Author: yang-xiansen
     * @Date: 2020/08/12 9:26
     */
    @Scheduled(fixedRate = 10000)
    public void sendTopicMessage() {
        System.out.println("后台广播推送！");
        User user = new User();
        user.setUserName("oyzc");
        user.setAge(10);
        this.template.convertAndSend("/topic/getResponse", user);
    }


    /**
     * @Description: 后台一对一推送消息
     * @Author: yang-xiansen
     * @Date: 2020/08/12 9:30
     */
    @Scheduled(fixedRate = 10000)
    public void sendQueueMessage() {
        System.out.println("后台一对一推送！");
        User user = new User();
        user.setId(1);
        user.setUserName("oyzc");
        user.setAge(10);
        this.template.convertAndSendToUser(user.getId() + "", "/queue/getResponse", user);
    }



    /**
    * @Description: 前端连接时发送消息告诉服务器已经连上去
    * @Author: yang-xiansen
    * @Date: 2020/08/12 17:26
    */
    @MessageMapping("/online")
    public void addUser(@Payload User user,
                        SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", user.getId());
        System.out.println(user.getId() + "  进入");
        //处理历史数据
        //。。。
    }


}
