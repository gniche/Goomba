package goomba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Gino on 16/04/2017.
 */
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Starting Goomba REST Spring-Boot Application...");
        SpringApplication.run(Application.class, args);
        logger.info("Application started...");
    }
}