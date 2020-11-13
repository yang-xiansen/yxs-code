package org.yxs.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
public class User {

    /**
     * @id标治主键字段
     */
    @Id
    private String id;

    private String name;

    private Integer age;

    private String description;

    /**
     * @Transient 标志非表字段！
     */
    @Transient
    private Double height;


}
