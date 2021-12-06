package com.example.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(CustomerRepository repository) {

    return args -> {
       Customer cus = new Customer();
       cus.setFirstname("Manager");
       cus.setLastname(" ");
       cus.setEmail("admin@gmail.com");
       cus.setPassword("adminwelcome");
      log.info("Preloading " + repository.save(cus));
    };
  }
}