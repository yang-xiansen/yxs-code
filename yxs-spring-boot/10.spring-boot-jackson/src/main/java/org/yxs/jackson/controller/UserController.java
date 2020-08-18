package org.yxs.jackson.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yxs.jackson.entity.User;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Description: userController
 * @Author: yang-xiansen
 * @Date: 2020/08/18 13:11
 */
@RestController
public class UserController {

    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/get/user")
    public User getUser() {
        User user = new User();
        user.setUserName("yxs");
        user.setBirthday(new Date());
        return user;
    }

    /**
     * @Description: jackson通过ObjectMapper中的writeValueAssString方法将对象序列化json字符串
     * @Author: yang-xiansen
     * @Date: 2020/08/18 13:18
     */
    @GetMapping("/get/user1")
    public String getUser1() throws JsonProcessingException {
        User user = new User();
        user.setUserName("yxs");
        user.setBirthday(new Date());
        String str = mapper.writeValueAsString(user);
        return str;
    }

    /**
     * @Description: jackson通过ObjectMapper中的readTree方法读取json
     * @Author: yang-xiansen
     * @Date: 2020/08/18 13:18
     */
    @GetMapping("/get/user2")
    public User getUser2() throws IOException {
        String json = "{\"name\":\"yxs\",\"age\":26}";
        JsonNode jsonNode = mapper.readTree(json);
        User user = new User();
        user.setUserName(jsonNode.get("name").asText());
        user.setAge(jsonNode.get("age").asInt());
        return user;
    }

    /**
     * 高级解析
     * String json = "{\"name\":\"mrbird\",\"hobby\":{\"first\":\"sleep\",\"second\":\"eat\"}}";;
     * JsonNode node = this.mapper.readTree(json);
     * JsonNode hobby = node.get("hobby");
     * String first = hobby.get("first").asText();
     */


    /**
     * @Description: json数据与对象绑定
     * @Author: yang-xiansen
     * @Date: 2020/08/18 13:18
     */
    @GetMapping("/get/user3")
    public User getUser3() throws IOException {
        String json = "{\"userName\":\"yxs\",\"age\":26}";
        User user = mapper.readValue(json, User.class);
        return user;
    }

    /**
     * @Description: jsonView
     * @Author: yang-xiansen
     * @Date: 2020/08/18 13:18
     */
    @JsonView(User.UserNameView.class)
    @GetMapping("/get/user4")
    public User getUser4() {
        User user = new User();
        user.setAge(20);
        user.setUserName("yxs");
        user.setBirthday(new Date());
        return user;
    }

    /**
     * @Description: json数据与对象绑定
     * @Author: yang-xiansen
     * @Date: 2020/08/18 13:18
     */
    @JsonView(User.AllUserFieldView.class)
    @GetMapping("/get/user5")
    public User getUser5() {
        User user = new User();
        user.setUserName("yxs");
        user.setAge(20);
        user.setBirthday(new Date());
        return user;
    }


    /**
     * @Description: 集合的反序列化
     * ＠RequestBody将提交的JSON自动映射到方法参数上
     * 能自动识别出List对象包含的是User类，因为在方法中定义的泛型的类型会被保留在字节码中，所以Spring Boot能识别List包含的泛型类型从而能正确反序列化。
     * @Author: yang-xiansen
     * @Date: 2020/08/18 13:18
     */
    @GetMapping("/update/user")
    public User update(@RequestBody List<User> list) {
        User user = new User();
        user.setUserName("yxs");
        user.setAge(20);
        user.setBirthday(new Date());
        return user;
    }

    /**
    * @Description: 以下代码不能泛序列化  泛型已经被擦除
    * @Author: yang-xiansen
    * @Date: 2020/08/18 13:57

    @GetMapping("customize")
    public String customize() throws JsonParseException, JsonMappingException, IOException {
        String jsonStr = "[{\"userName\":\"mrbird\",\"age\":26},{\"userName\":\"scott\",\"age\":27}]";
        // 泛型擦除 不能通过映射来反序列化
        List<User> list = mapper.readValue(jsonStr, List.class);
        String msg = "";
        for (User user : list) {
            msg += user.getUserName();
        }
        return msg;
    }
    */


    /**
    * @Description: Jackson提供了JavaType ，用来指明集合类型
    * @Author: yang-xiansen
    * @Date: 2020/08/18 14:15
    */
    @GetMapping("customize")
    public String customize() throws IOException {
        String jsonStr = "[{\"userName\":\"mrbird\",\"age\":26},{\"userName\":\"scott\",\"age\":27}]";

//        为了提供泛型信息，Jackson提供了JavaType ，用来指明集合类型
        JavaType type = mapper.getTypeFactory().constructParametricType(List.class, User.class);
        List<User> list = mapper.readValue(jsonStr, type);
        String msg = "";
        for (User user : list) {
            msg += user.getUserName();
        }
        return msg;
    }


}
