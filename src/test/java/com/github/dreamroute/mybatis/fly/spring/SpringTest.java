/**
 *    Copyright 2009-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.github.dreamroute.mybatis.fly.spring;

import java.util.List;

import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.dreamroute.mybatis.fly.domain.User;
import com.github.dreamroute.mybatis.fly.mapper.UserMapper;

import ognl.Ognl;

public class SpringTest {
    
    @Test
    public void insertUserTest() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        SqlSessionFactory sqlSessionFactory = context.getBean(SqlSessionFactory.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(null, "w.dehai", "3333");
        int result = userMapper.insertUser(user);
        System.err.println(result);
        context.close();
    }
    
    @Test
    public void selectByIdTest() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        SqlSessionFactory sqlSessionFactory = context.getBean(SqlSessionFactory.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectById(1L);
        System.err.println(users);
        context.close();
    }
    
    @Test
    public void ognlTest() throws Exception {
        User user = new User(100L, "w.dehai", "123456");
        DynamicContext.ContextMap context = new DynamicContext.ContextMap(new Configuration().newMetaObject(user), false);
        Object v = Ognl.getValue("name", context, user);
        System.err.println(v);
    }

}
