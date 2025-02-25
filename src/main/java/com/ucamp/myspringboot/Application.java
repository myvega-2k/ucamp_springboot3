package com.ucamp.myspringboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		//SpringApplication.run(Application.class, args);
		SpringApplication application = new SpringApplication(Application.class);
		//Application type 변경
		application.setWebApplicationType(WebApplicationType.SERVLET);
		//application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}
	//Configuration 클래스로서의 역할을 한다.
	//Object Mapping 을 지원하는 ModelMapper 를 SpringBean 으로 설정한다.
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
