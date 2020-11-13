package org.yxs.auto.annotation;


import org.springframework.context.annotation.Import;
import org.yxs.auto.configuration.HelloWorldConfiguration;
import org.yxs.auto.selector.HelloWorldImportSelector;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HelloWorldConfiguration.class) //注解驱动  参考EnableWebMvc
//@Import(HelloWorldImportSelector.class) //接口编程驱动 参考EnableCaching
public @interface EnableHelloWorld {
}
