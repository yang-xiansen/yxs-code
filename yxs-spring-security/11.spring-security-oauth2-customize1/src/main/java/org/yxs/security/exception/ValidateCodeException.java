package org.yxs.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description: 自定义认证异常
 * @Author: y-xs
 * @Date: 2020/09/21 15:46
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 5022575393500654458L;

    public ValidateCodeException(String message) {
        super(message);
    }
}
