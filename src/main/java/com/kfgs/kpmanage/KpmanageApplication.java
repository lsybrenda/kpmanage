package com.kfgs.kpmanage;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.kfgs.kpmanage.mapper")
@SpringBootApplication
@EnableScheduling
public class KpmanageApplication {

    private static Logger logger = Logger.getLogger(KpmanageApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KpmanageApplication.class, args);
    }

}
