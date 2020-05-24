package br.com.infox.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.infox.*")
@EntityScan(basePackages = "br.com.infox.*")
@EnableJpaRepositories(basePackages = "br.com.infox.*")
public class InfoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfoxApplication.class, args);
    }
}

