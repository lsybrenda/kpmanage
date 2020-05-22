package com.kfgs.kpmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.kfgs.kpmanage.mapper")
@SpringBootApplication
public class KpmanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(KpmanageApplication.class, args);
    }

}
