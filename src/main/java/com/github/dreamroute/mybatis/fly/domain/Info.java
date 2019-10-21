package com.github.dreamroute.mybatis.fly.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Info {
    
    private Long id;
    private String desc;
}
