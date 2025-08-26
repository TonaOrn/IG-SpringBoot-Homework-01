package com.ig.training;

import com.ig.training.base.response.ResponseMessage;
import com.ig.training.dto.AccountDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TrainingApplication {

	public static void main(String[] args) {
        SpringApplication.run(TrainingApplication.class, args);
	}

}
