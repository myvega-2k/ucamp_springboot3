package com.ucamp.myspringboot.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ucamp.myspringboot.config.vo.CustomVO;
import com.ucamp.myspringboot.property.MyBootProperties;

@Component
@Order(2)
public class MyRunner implements ApplicationRunner {
    @Value("${myboot.name}")
    String name;

    @Value("${myboot.age}")
    int age;

    @Autowired
    private MyBootProperties properties;

    @Autowired
    private CustomVO customVO;

    private final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Logger 구현체 클래스명 = {}", logger.getClass().getName());
        logger.info("현재 활성화 된 CustomVO = {}", customVO);
        logger.info("MyBootProperties getFullName() = {}", properties.getFullName());
        logger.info("MyBootProperties getAge() = {}", properties.getAge());
        logger.info("myboot.name 환경변수 값 = {}", name);
        logger.info("myboot.age 환경변수 값 = {}", age);

        logger.debug("VM argument foo = {}", args.containsOption("foo"));
        logger.debug("Program argument bar = {}", args.containsOption("bar"));
        args.getOptionNames().forEach(System.out::println);

        if(args.containsOption("server.port")) {
            System.out.println(args.getOptionValues("server.port"));
        }
    }
}
