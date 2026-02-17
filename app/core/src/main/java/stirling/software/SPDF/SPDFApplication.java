package stirling.software.SPDF;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Minimal Spring Boot entrypoint used when proprietary/common advanced initializers are not
 * present. Keeps application runnable while removing tight coupling.
 */
@SpringBootApplication
public class SPDFApplication {
    public static void main(String[] args) {
        SpringApplication.run(SPDFApplication.class, args);
    }
}
