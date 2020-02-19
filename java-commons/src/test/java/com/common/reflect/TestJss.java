package com.common.reflect;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * TODO
 *
 * @date:2020/2/4 17:50
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class TestJss {


    /**
     *
     * @throws Exception
     */
    @Test
    public void testJSS() throws Exception {
        ProxyFactory factory = new ProxyFactory();

        factory.setSuperclass(TestBean.class);


        factory.setFilter(new MethodFilter() {
            @Override
            public boolean isHandled(Method m) {
                return m.getName().equals("getName");
            }
        });

        factory.setUseCache(false);

        factory.setHandler(new MethodHandler() {
            @Override
            public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
                return "change name";
            }
        });

        Class aClass = factory.createClass();
        TestBean o = (TestBean) aClass.getConstructor(String.class).newInstance("name");


        String name = o.getName();

        System.out.println(name);


    }


}
