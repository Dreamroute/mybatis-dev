package com.github.dreamroute.mybatis.fly;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.io.ResolverUtil.IsA;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.parsing.PropertyParser;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.BeanWrapper;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.junit.Test;

import com.github.dreamroute.mybatis.fly.domain.User;

import lombok.Data;

public class BaseTest {

    @Test
    public void xpathTest() throws Exception {
        String resource = "conf/config.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        XPathParser parser = new XPathParser(in, true, null, new XMLMapperEntityResolver());
        XNode env = parser.evalNode("/configuration/environments/environment");
        String path = env.getPath();
        System.err.println(path);
        String value = env.getValueBasedIdentifier();
        System.err.println(value);

        String strBody = env.getStringBody();
        System.err.println(strBody);
    }

    @Test
    public void propertyParserTest() {
        Properties props = new Properties();
        props.setProperty("name", "w.dehai");
        String name = PropertyParser.parse("${name}", props);
        System.err.println(name);
    }

    @Test
    public void lineSeparatorTest() {
        String lineSeparator = System.getProperty("line.separator", "\n");
        System.err.println(lineSeparator);
    }

    @Test
    public void reflectorFactoryTest() {
        Reflector r = new Reflector(User.class);
        System.err.println(r);

        ReflectorFactory factory = new DefaultReflectorFactory();
        Reflector re = factory.findForClass(User.class);
        System.err.println(re);

    }

    @Test
    public void metaClsTest() {
        MetaClass mc = MetaClass.forClass(User.class, new DefaultReflectorFactory());
        System.err.println(mc);
    }

    @Test
    public void vfsTest() throws Exception {
        VFS vfs = VFS.getInstance();
        List<String> list = vfs.list("");
        System.err.println(list);
    }
    
    // VFS和ResolveUtil适合用于ES的的搜索业务代码
    @Test
    public void resolverUtilTest() {
        ResolverUtil<Class<?>> resolverUtil = new ResolverUtil<>();
        ResolverUtil<Class<?>> result = resolverUtil.find(new IsA(Object.class), "com.github.dreamroute.mybatis.fly.domain");
        System.err.println(result);

        Set<Class<? extends Class<?>>> clsSet = resolverUtil.getClasses();
        System.err.println(clsSet);
        int modifier = clsSet.iterator().next().getModifiers();
        System.err.println(modifier);

        Set<Class<? extends Class<?>>> clsSet2 = resolverUtil.findAnnotated(Data.class, "com.github.dreamroute.mybatis.fly.domain").getClasses();
        System.err.println(clsSet2);

        Set<Class<? extends Class<?>>> clsSet3 = resolverUtil.findImplementations(User.class, "com.github.dreamroute.mybatis.fly.domain").getClasses();
        System.err.println(clsSet3);

        try {
            List<String> children = VFS.getInstance().list("com/github/dreamroute/mybatis/fly/domain");
            System.err.println(children);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pluginTest() {

    }

    @Test
    public void objectFactoryTest() {
        ObjectFactory objectFactory = new DefaultObjectFactory();
        User user = objectFactory.create(User.class);
        System.err.println(user);
    }

    @Test
    public void metaObjTest() {
        try {
            ObjectFactory objectFactory = new DefaultObjectFactory();
            ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
            ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
            User user = new User();
            user.setId(100L);
            MetaObject mo = MetaObject.forObject(user, objectFactory, objectWrapperFactory, reflectorFactory);
            mo.setValue("id", 101L);
            Object id = mo.getValue("id");
            System.err.println(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void objectWrapperTest() {
        User user = new User();
        user.setId(100L);
        ObjectWrapper wrap = new BeanWrapper(new Configuration().newMetaObject(user), user);
        Object id = wrap.get(new PropertyTokenizer("id"));
        System.err.println(id);
    }

    @Test
    public void objectWrapperFactoryTest() {
        ObjectWrapperFactory objWrapFactory = new DefaultObjectWrapperFactory();
        objWrapFactory.hasWrapperFor(new User());
        objWrapFactory.getWrapperFor(null, new User());
    }

    @Test
    public void propertyTokenizerTest() {
        PropertyTokenizer pt = new PropertyTokenizer("user[0].name");
        System.err.println(pt);
    }

    @Test
    public void unpooledDsTest() {
        try {
            DataSource ds = new UnpooledDataSource("com.mysql.cj.jdbc.Driver", "jdbc:mysql://10.82.12.63:3306/mybatis-fly?useSSL=false", "root", "123456");
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into user (name, password) values (?, ?)");
            ps.setString(1, "w.dehai");
            ps.setString(2, "pwd");
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pooledDsTest() {
        try {
            UnpooledDataSource unpooledDs = new UnpooledDataSource("com.mysql.cj.jdbc.Driver", "jdbc:mysql://10.82.12.63:3306/mybatis-fly?useSSL=false", "root", "123456");
            PooledDataSource ds = new PooledDataSource(unpooledDs);
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into user (name, password) values (?, ?)");
            ps.setString(1, "w.dehai");
            ps.setString(2, "pwd2");
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void txFactoryTest() throws Exception {
        TransactionFactory txFactory = new JdbcTransactionFactory();
        Transaction tx = txFactory.newTransaction(null);
        System.err.println(tx);

        JdbcTransaction jdbcTx = new JdbcTransaction(null);
        jdbcTx.commit();
    }

    @Test
    public void enumTest() {
        Season.values();
        JdbcType.forCode(1);
        Season s = Season.valueOf("SPRING");
        System.err.println(s);
    }

    static enum Season {
        SPRING(1), SUMMER(2);

        private int code;

        private Season(int code) {
            this.code = code;
        }

        int getCode() {
            return this.code;
        }

    }

    @Test
    public void typeAliasTest() {
        TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
        Class<?> integerCls = typeAliasRegistry.resolveAlias("int");
        System.err.println(integerCls);
    }

    class Car {
        void drive(Type type) {
            System.err.println(type);
            String typeName = type.getTypeName();
            System.err.println(typeName);
        }
    }

    @Test
    public void typeHandlerTest() {
        TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();
        System.err.println(typeHandlerRegistry);
    }

    @Test
    public void cacheTest() {
        Cache cache = new PerpetualCache("selectUserById");
    }

    @Test
    public void linkedHashMapTest() {
        Map<Integer, String> map = new LinkedHashMap<Integer, String>(3, 1, true) {
            private static final long serialVersionUID = 7069977178177649304L;

            @Override
            protected boolean removeEldestEntry(java.util.Map.Entry<Integer, String> eldest) {
                return this.size() > 3;
            }
        };
        for (int i = 0; i < 10; i++)
            map.put(i, String.valueOf(i));
        map.get(8);
        System.err.println(map);

    }

    @Test
    public void linkedTest() {
        int size = 3;
        LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>(size, 1, true) {
            private static final long serialVersionUID = -3846158632279964414L;

            @Override
            protected boolean removeEldestEntry(java.util.Map.Entry<Integer, String> eldest) {
                int real = this.size();
                return real > size;
            }
        };
        for (int i = 0; i < 10; i++)
            map.put(i, String.valueOf(i));
        map.get(7);
        map.put(10, "10");
        System.err.println(map);

    }
    
    @Test
    public void configurationTest() {
        Configuration config = new Configuration();
        System.err.println(config);
        TypeAliasRegistry registry = new TypeAliasRegistry();
        Class<?> c = registry.resolveAlias("string");
        System.err.println(c);
        Object obj = registry.getTypeAliases();
        System.err.println(obj);
        
        Map<String, String> map = new LinkedHashMap<String, String>(16, 0.75F, true) {
            private static final long serialVersionUID = 839284228506046923L;
            @Override
            protected boolean removeEldestEntry(java.util.Map.Entry<String, String> eldest) {
                return size() > 16;
            }
        };
        
        for (int i=0; i<100; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        System.err.println(map);
        
        User user = new User();
        MetaObject mo = SystemMetaObject.forObject(user);
        mo.getValue("");
    }
    
}













