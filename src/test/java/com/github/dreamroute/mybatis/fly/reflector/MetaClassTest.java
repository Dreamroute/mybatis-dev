package com.github.dreamroute.mybatis.fly.reflector;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.junit.jupiter.api.Test;

import com.github.dreamroute.mybatis.fly.domain.User;

public class MetaClassTest {
    
    @Test
    public void mcTest() {
        ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
        MetaClass mc = MetaClass.forClass(User.class, reflectorFactory);
        String name = mc.findProperty("name");
        assertEquals("name", name);
    }
    
    @Test
    public void moTest() {
        User user = new User();
        user.setId(100L);
        user.setName("wangdehai");
        user.setPassword("123456");
        MetaObject mo = SystemMetaObject.forObject(user);
        mo.setValue("id", 99L);
        assertEquals(99L, user.getId());
        
        Map<String, String> map = new HashMap<>();
        MetaObject mm = SystemMetaObject.forObject(map);
        mm.setValue("name", "w.dehai");
        System.err.println(map);
    }
    
    @Test
    public void proxyTest() {
        
        InvocationHandler handler = (proxy, method, args) -> {
            System.err.println("我们自己的逻辑");
            return null;
        };
        
        Car audi = (Car) Proxy.newProxyInstance(Car.class.getClassLoader(), new Class<?>[] {Car.class}, handler);
        audi.drive();
    }

}
