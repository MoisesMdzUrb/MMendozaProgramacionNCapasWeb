package com.digis01.MMendozaProgramacionNCapasWeb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MMendozaProgramacionNCapasWebApplication implements CommandLineRunner {
    
    private Logger LOG = LoggerFactory.getLogger(MMendozaProgramacionNCapasWebApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MMendozaProgramacionNCapasWebApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        LOG.warn("Hola Mundo");
    }

}
