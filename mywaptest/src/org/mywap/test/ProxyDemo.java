package org.mywap.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by s on 2017/10/18.
 */
public class ProxyDemo {

    public static void main(String[] args) {
        Man man=new Man();
        Hand h=new Hand(man);
        Subject  subject  = (org.mywap.test.Subject) Proxy.newProxyInstance(man.getClass().getClassLoader(), new Class[]{Subject.class}, h);
        subject.shopping();
        subject.say("hello");


    }
}

class Hand implements InvocationHandler{

    private Object target;//要代理的真实对象

    public Hand(Object target){
        this.target=target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getName());
        System.out.println(method.getClass().getName());
        System.out.println(args);
        method.invoke(target,args);
        System.out.println("-------------------------------");

        return null;
    }
}

//被代理人和代理人要执行的动作
interface Subject{
    void shopping();
    void say(String say);
}
//被代理类
class Man implements Subject{
    private String name="Tom";
    @Override
    public void shopping() {
        System.out.println(name+"要去买东西...");
    }

    @Override
    public void say(String say) {
        System.out.println(name+ " say "+say);
    }

}