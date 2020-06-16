package com.anupesh.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.anupesh.demo.audit.AuditorAwareImpl;

@SpringBootApplication
@EnableJpaAuditing  (auditorAwareRef = "auditorAware") 
public class DemoApplication {

	private static final Logger LOGGER=LoggerFactory.getLogger(DemoApplication.class);
	
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		LOGGER.info("Simple log statement with inputs {}, {} and {}", 1,2,3);
	}

}
