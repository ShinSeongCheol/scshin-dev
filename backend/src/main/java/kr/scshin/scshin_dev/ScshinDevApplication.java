package kr.scshin.scshin_dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ScshinDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScshinDevApplication.class, args);
	}

}
