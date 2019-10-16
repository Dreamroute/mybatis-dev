package com.github.dreamroute.mybatis.fly;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.parsing.GenericTokenParser;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ognl.Ognl;
import ognl.OgnlException;

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
    public void ognlTest() {
        User root = new User(); 
        root.setId(19612); 
        root.setName("sakura");     
        Map context = new HashMap(); 
        context.put("who", "Who am i?");   
        try {       
          String who1 = (String)Ognl.getValue("#who", context, root);   
          String who2 = (String)Ognl.getValue("#context.who", context, root); 
            Object whoExp = Ognl.parseExpression("#who"); 
          String who3 = (String)Ognl.getValue(whoExp, context, root); 
          //who1 who2 who3 返回同样的值， whoExp 重复使用可以提高效率        
          String name1 = (String)Ognl.getValue("name", root); 
          String name2 = (String)Ognl.getValue("#root.name", root);           
          //name1 name2 返回同样的值
        } catch (OgnlException e) { 
          //error handling 
        }
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    Integer id;
    String name;
}
