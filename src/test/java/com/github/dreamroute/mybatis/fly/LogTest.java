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
package com.github.dreamroute.mybatis.fly;

import java.util.StringJoiner;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.junit.jupiter.api.Test;
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
    
    @Test
    public void stringJoinerTest() {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        sj.add("w.dehai").add("jaedong").add("Dreamroute");
        System.err.println(sj.toString());
    }

}
