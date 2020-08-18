package org.yxs.jackson.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: user
 * @Author: yang-xiansen
 * @Date: 2020/08/18 13:09
 */
@Data
@JsonIgnoreProperties({"password", "password1"}) //忽视一组属性
//@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)  //字段到前端展示的命名策略
public class User implements Serializable {
    private static final long serialVersionUID = 6222176558369919436L;

    // @JsonView，作用在类或者属性上，用来定义一个序列化组。 比如对于User对象，某些情况下只返回userName属性就行，而某些情况下需要返回全部属性。
    public interface UserNameView {};
    public interface AllUserFieldView extends UserNameView {};


    @JsonView(UserNameView.class)
    private String userName;

    @JsonView(AllUserFieldView.class)
    private int age;

    @JsonIgnore //忽视该属性 不传给前端
    private String password;

    private String password1;

    @JsonProperty("btn") //作用在属性上，用来为JSON Key指定一个别名。
    private Date birthday;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  //格式化日期
    private Date createDate;
}
