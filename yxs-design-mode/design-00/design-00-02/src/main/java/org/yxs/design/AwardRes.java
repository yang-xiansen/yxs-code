package org.yxs.design;

import lombok.Data;

/**
 * @author: y-xs
 * @date: 2020/12/03 17:22
 * @description: 响应参数
 */
@Data
public class AwardRes {

    private String code; // 编码
    private String info; // 描述

    public AwardRes(String code, String info) {
        this.code = code;
        this.info = info;
    }

}
