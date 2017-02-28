package com.example.administrator.myapplication.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xcy on 2017/1/3 0003.
 */
interface ProxyInterFace {
    public void proxyMethod();

    public void proxyMethod11();

    public void proxyMethod22();

}

class TargetObject implements ProxyInterFace {

    public void proxyMethod() {
        System.out.println("我被代理了，哈哈！");
    }

    @Override
    public void proxyMethod11() {
        System.out.println("yasd");

    }

    @Override
    public void proxyMethod22() {
        System.out.println("hihihi");

    }
}

class TargetObject1 implements ProxyInterFace {

    public void proxyMethod() {
        System.out.println("我被代理了，哈哈！111");
    }

    @Override
    public void proxyMethod11() {
        System.out.println("yasd11");

    }

    @Override
    public void proxyMethod22() {
        System.out.println("hihihi111");

    }
}

class ProxyObject implements InvocationHandler {
    //代码的对象
    public Object targetObject;

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //调用，传入一个目标对象，和对应的对象参数
        System.out.println("正在开始");
        return method.invoke(targetObject, args);
    }
}

public class ProxyMain {
    public static void main(String[] args) {
//        //代理的目标对象
//        ProxyInterFace proxyInterface = new TargetObject();
//        //代理器
//        ProxyObject proxyObject = new ProxyObject();
//        proxyObject.setTargetObject(proxyInterface);
//        //转换成InvocationHandler
////        InvocationHandler handler = proxyObject;
//        //执行代码任务
//        ProxyInterFace proxy = (ProxyInterFace) Proxy.newProxyInstance(ProxyInterFace.class
//                .getClassLoader(), proxyInterface.getClass().getInterfaces(), proxyObject);
//
//        //转换成目标对象，调用目标对象的方法
//        proxy.proxyMethod();
//        proxy.proxyMethod11();
//        proxy.proxyMethod22();
//        System.out.println(proxy instanceof Proxy);

        PayProxyManager manager = new PayProxyManager();
        manager.startPay("asdasd");

    }
}