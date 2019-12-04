package com.github.hykes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * application
 * @author hehaiyangwork@gmail.com
 * @date 2019-10-20 15:09:15
 */
@SpringBootApplication
public class JSR303WebApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(JSR303WebApplication.class);
        application.run(args);
    }

}
