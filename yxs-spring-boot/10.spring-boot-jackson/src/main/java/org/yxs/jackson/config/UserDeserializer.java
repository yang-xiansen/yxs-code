package org.yxs.jackson.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.yxs.jackson.entity.User;

/**
* @Description: 自定义反序列化
 * 使用方式 @JsonDeserialize (using = UserDeserializer.class)
* @Author: yang-xiansen
* @Date: 2020/08/18 13:40
*/
public class UserDeserializer extends JsonDeserializer<User> {

    @Override
    public User deserialize(JsonParser parser, DeserializationContext context)
        throws IOException, JsonProcessingException {
        JsonNode node = parser.getCodec().readTree(parser);
        String userName = node.get("user-name").asText();
        User user = new User();
        user.setUserName(userName);
        return user;
    }
}
