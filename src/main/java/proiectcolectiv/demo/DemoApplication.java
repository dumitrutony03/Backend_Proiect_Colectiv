package proiectcolectiv.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"proiectcolectiv.mapper", "proiectcolectiv.controllers", "proiectcolectiv.service"})
//@SpringBootApplication(scanBasePackages = "proiectcolectiv.mapper")
@EnableMongoRepositories
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}


