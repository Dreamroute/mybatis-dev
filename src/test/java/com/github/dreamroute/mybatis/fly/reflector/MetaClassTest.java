package com.github.dreamroute.mybatis.fly.reflector;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.junit.jupiter.api.Test;

import com.github.dreamroute.mybatis.fly.domain.Dev;
import com.github.dreamroute.mybatis.fly.domain.User;

public class MetaClassTest {
    
    @Test
    public void reflectorTest() {
        Reflector reflector = new Reflector(Dev.class);
        boolean controllerMemberAccessible = Reflector.canControlMemberAccessible();
        System.err.println(controllerMemberAccessible);
    }
    
    @Test
    public void mcTest() {
        ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
        MetaClass mc = MetaClass.forClass(User.class, reflectorFactory);
    }

}
