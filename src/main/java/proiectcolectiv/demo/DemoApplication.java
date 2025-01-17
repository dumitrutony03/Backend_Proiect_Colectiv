package proiectcolectiv.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import proiectcolectiv.service.HospitalsService;

@ComponentScan(basePackages = {"proiectcolectiv.mapper", "proiectcolectiv.controllers", "proiectcolectiv.service", "proiectcolectiv.config"})
@SpringBootApplication(scanBasePackages = "proiectcolectiv.mapper")
@EnableMongoRepositories
public class DemoApplication implements CommandLineRunner {

    @Autowired
    static HospitalsService hospitalsService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
    }


}


