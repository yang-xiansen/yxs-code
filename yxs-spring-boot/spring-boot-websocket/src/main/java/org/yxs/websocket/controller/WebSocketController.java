package org.yxs.websocket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.yxs.websocket.config.WebSocketServer;

import java.io.IOException;

@Controller
public class WebSocketController {

    @GetMapping("index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("请求成功");
    }

    @GetMapping("page")
    public ModelAndView page(){
        return new ModelAndView("websocket");
    }

    @RequestMapping("/push/{userId}")
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String userId) throws IOException {
        WebSocketServer.sendInfo(message,userId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }

}
