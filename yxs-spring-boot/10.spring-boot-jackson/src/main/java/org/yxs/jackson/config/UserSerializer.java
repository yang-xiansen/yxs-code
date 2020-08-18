package org.yxs.jackson.config;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.yxs.jackson.entity.User;

/**
* @Description: 自定义序列化
 * 使用方式 @JsonSerialize(using = UserSerializer.class)
* @Author: yang-xiansen
* @Date: 2020/08/18 13:39
*/
public class UserSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(User user, JsonGenerator generator, SerializerProvider provider)
        throws IOException, JsonProcessingException {
        generator.writeStartObject();
        generator.writeStringField("user-name", user.getUserName());
        generator.writeEndObject();
    }
}
