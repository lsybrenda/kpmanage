package com.kfgs.kpmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.logging.Logger;

@MapperScan("com.kfgs.kpmanage.mapper")
@SpringBootApplication
@EnableScheduling
public class KpmanageApplication {


    public static void main(String[] args) {
        SpringApplication.run(KpmanageApplication.class, args);
    }

}
