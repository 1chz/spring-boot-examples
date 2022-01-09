package io.github.shirohoo.reactive;

import java.io.FileInputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        BlockHound.builder()
            .allowBlockingCallsInside(TemplateEngine.class.getCanonicalName(), "process")
            .allowBlockingCallsInside(FileInputStream.class.getCanonicalName(), "readBytes")
            .install();

        SpringApplication.run(Application.class, args);
    }
}
