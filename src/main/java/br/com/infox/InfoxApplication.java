package br.com.infox;

import br.com.infox.displaykey.DisplayKey;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InfoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfoxApplication.class, args);
        DisplayKey.setLocale("pt");
    }

}

