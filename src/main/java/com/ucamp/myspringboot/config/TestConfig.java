package com.ucamp.myspringboot.config;

import com.ucamp.myspringboot.config.vo.CustomVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestConfig {
    @Bean
    public CustomVO customVO() {
        return CustomVO.builder()  //CustomVOBuilder
                .mode("테스트 환경")
                .rate(0.5)
                .build();
    }
}
