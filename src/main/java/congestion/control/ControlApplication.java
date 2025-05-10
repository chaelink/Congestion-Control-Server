package congestion.control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlApplication.class, args);
	}

}
