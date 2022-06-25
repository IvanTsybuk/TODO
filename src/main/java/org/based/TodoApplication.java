package org.based;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class TodoApplication {
    public static void main(String[] args) {
        log.debug("TodoApplication starts");
        SpringApplication.run(TodoApplication.class);
    }
}
