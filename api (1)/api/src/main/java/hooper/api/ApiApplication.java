package hooper.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

    // essa classe é criada pelo Spring e esse método main roda o projeto
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
