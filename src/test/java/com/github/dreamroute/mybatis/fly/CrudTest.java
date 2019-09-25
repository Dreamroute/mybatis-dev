package com.github.dreamroute.mybatis.fly;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.executor.resultset.ResultSetWrapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.github.dreamroute.mybatis.fly.domain.User;

public class CrudTest {

    @Test
    public void insertTest() {
        try {
            String resource = "conf/config.xml";
            InputStream in = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            User user = User.builder().name("w.dehai").password("123456").build();
            int result = sqlSession.insert("com.github.dreamroute.mybatis.fly.mapper.UserMapper.insertUser", user);
            // int result = sqlSession.getMapper(UserMapper.class).insert(user);
            System.err.println(result);

            RowBounds bounds = new RowBounds(0, 10);
            System.err.println(bounds);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectByIdTest() {
        try {
            String resource = "conf/config.xml";
            InputStream in = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            // User result = sqlSession.selectOne("com.github.dreamroute.mybatis.fly.mapper.UserMapper.selectById", 1L);
            // System.err.println(result);
            List<User> users = sqlSession.selectList("com.github.dreamroute.mybatis.fly.mapper.UserMapper.selectById", 1L, new RowBounds(0, 1));
            // UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // List<User> users = userMapper.selectById(1L);
            System.err.println(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void resultSetWrapperTest() throws Exception {
        ResultSetWrapper rsw = new ResultSetWrapper(null, null);
        System.err.println(rsw);
    }

}

















