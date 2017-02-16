package org.leo.shiro.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理指的是通过一个代理对象来创建需要的业务对象，然后在这个代理对象统一进行各种需求的处理
 * <p/>
 * <p/>
 * 1.写一个类实现InvocationHandler接口
 * <p/>
 *
 */
public class LogProxy implements InvocationHandler {

    //2.创建一个要代理的对象
    private Object target;

    //3.创建一个方法来生成对象，这个方法的参数是要代理的对象
    public static Object getInstance(Object o) {
        //3.1 创建一个LogProxy对象
        LogProxy proxy = new LogProxy();
        //3.2 设置这个代理对象
        proxy.target = o;
        //3.3 通过proxy的方法创建代理对象，第一个参数是要代理的classLoader
        //第二个参数是要代理对象实现的所有接口，第三个参数是实现类InvacationHandler对象
        Object result = Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), proxy);

        return result;
    }


    /**
     * 当有了代理对象后，不管这个代理对象执行什么方法，都会调用一下的invoke方法
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = method.invoke(target, args);
        return obj;
    }
}
