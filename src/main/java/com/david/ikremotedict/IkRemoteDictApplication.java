package com.david.ikremotedict;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.david.ikremotedict.mapper")
public class IkRemoteDictApplication {

    public static void main(String[] args) {
        SpringApplication.run(IkRemoteDictApplication.class, args);
    }

}
