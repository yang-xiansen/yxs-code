package org.yxs.batch.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class TestData {

    private int id;
    private String field1;
    private String field2;
    @NotBlank
    private String field3;

}
