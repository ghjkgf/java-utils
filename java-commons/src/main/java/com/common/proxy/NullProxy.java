package com.common.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * TODO
 *
 * @date:2020/2/4 14:22
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class NullProxy<T> {

    // 代理bean
    private final T proxyBean;

    public NullProxy(T proxy) {
        this.proxyBean = proxy;
    }

    @SuppressWarnings("all")
    public T getProxy() throws Exception {
        // 判断前置逻辑
        if (proxyBean == null) {
            throw new RuntimeException("代理对象不能为 NULL");
        }
        Class<?>[] interfaces = proxyBean.getClass().getInterfaces();
        if (interfaces == null || interfaces.length == 0) {
            throw new RuntimeException("代理对象必须是接口实现类");
        }
        // 通过底层获取一个代理的字节码
        Class<?> proxyClass = Proxy.getProxyClass(proxyBean.getClass().getClassLoader(), proxyBean.getClass().getInterfaces());
        // 构造器,需要传入一个InvocationHandler,最后调用用户实现就可以了.
        return (T) proxyClass.getConstructor(InvocationHandler.class).newInstance((InvocationHandler) (proxy, method, args) -> {
            Parameter[] parameters = method.getParameters();
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    Parameter parameter = parameters[i];
                    Annotation annotation = parameter.getAnnotatedType().getAnnotation(NotNull.class);
                    if (annotation != null) {
                        if (args[i] == null) {
                            String name = parameter.getName();
                            throw new NullPointerException(name + " 不能为空");
                        }
                    }
                }
            }
            return method.invoke(NullProxy.this.proxyBean, args);
        });
    }
}
