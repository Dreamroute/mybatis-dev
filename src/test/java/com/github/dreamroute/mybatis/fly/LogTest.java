package com.github.dreamroute.mybatis.fly;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerFactory;

public class LogTest {
    
    @Test
    public void logTest() {
        Log log = LogFactory.getLog(LogTest.class);
        if (log.isDebugEnabled()) {
            log.debug("test log.");
        }
    }
    
    @Test
    public void mmTest() {
        Slf4jImpl slf4j = new Slf4jImpl(LogTest.class.getName());
        slf4j.debug("test debug.");
    }
    
    @Test
    public void slf4jTest() {
        Logger logger = LoggerFactory.getLogger(LogTest.class);
        logger.debug("the name is {}.", "w.dehai");
    }
    
    @Test
    public void log4jFactoryTest() {
        new Log4jLoggerFactory();
        System.err.println(123);
    }

}
