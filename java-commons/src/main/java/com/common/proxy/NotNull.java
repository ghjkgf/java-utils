package com.common.proxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO
 *
 * @date:2020/2/4 14:27
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
@Target({ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
}
