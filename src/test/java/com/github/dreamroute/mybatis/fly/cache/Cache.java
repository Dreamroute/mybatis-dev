package com.github.dreamroute.mybatis.fly.cache;

public interface Cache {
    
    String getId();
    
    void putObject(Object key, Object value);
    
    Object get(Object key);
    
    void clear();
    
    int size();

}
