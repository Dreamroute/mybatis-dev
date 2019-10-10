package com.github.dreamroute.mybatis.fly;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.ibatis.executor.resultset.ResultSetWrapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.JdbcType;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.github.dreamroute.mybatis.fly.domain.User;

public class CrudTest {

    @Test
    public void insertTest() {
        try {
            String resource = "conf/config.xml";
            InputStream in = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sqlSessionFactory.openSession(false);
            User user = User.builder().name("w.dehai").password("123456").build();
            int result = sqlSession.insert("com.github.dreamroute.mybatis.fly.mapper.UserMapper.insertUser", user);
            // int result = sqlSession.getMapper(UserMapper.class).insert(user);
            System.err.println(result);
            sqlSession.commit();

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
    public void selectByMapTest() {
        try {
            String resource = "conf/config.xml";
            InputStream in = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            Map<String, Long> idMap = new HashMap<>();
            idMap.put("id", 20L);
            List<User> users = sqlSession.selectList("com.github.dreamroute.mybatis.fly.mapper.UserMapper.selectByMap", idMap, new RowBounds(0, 1));
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
    
    @Test
    public void mmmmmmmm() throws Exception {
        InputStream in = Resources.getResourceAsStream("user.xml");
        InputSource source = new InputSource(in);
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(source);
        NodeList children = doc.getChildNodes();
        if (children.getLength() > 0) {
            for (int i=0, len = children.getLength(); i< len; i++) {
                Node child = children.item(i);
                NodeList userAttr = child.getChildNodes();
                if (userAttr.getLength() > 0) {
                    for (int j=0, length = userAttr.getLength(); j<length; j++) {
                        Node attr = userAttr.item(j);
                        System.err.println(attr.getNodeName() + "[" + attr.getNodeType() + "]: " + attr.getTextContent());
                    }
                }
            }
        }
        
        XPathParser parser = new XPathParser(doc);
        XNode userNode = parser.evalNode("user");
        String ident = userNode.getValueBasedIdentifier();
        System.err.println(ident);
        String id = userNode.evalString("id");
        System.err.println(id);
    }
    
    @Test
    public void mq() {
        JdbcType vc = JdbcType.valueOf("VARCHAR");
        System.err.println(vc);
    }

}

















