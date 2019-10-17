package com.github.dreamroute.mybatis.fly;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.parsing.GenericTokenParser;
import org.apache.ibatis.parsing.TokenHandler;
import org.junit.jupiter.api.Test;

public class Mm {

    @Test
    public void mmm() {
        Map<String, String> names = new HashMap<String, String>() {
            private static final long serialVersionUID = 1L;
            {
                put("name", "Dreamroute");
            }
        };
        GenericTokenParser parser = new GenericTokenParser("${", "}", token -> {
            return names.get("name");
        });
        String result = parser.parse("my name is ${name}");
        System.err.println(result);

    }
    
    @Test
    public void mm1() {
        TokenHandler th;
    }

}
