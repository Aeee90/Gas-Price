package aeee.api.gasprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:properties/infura.properties")
public class GaspriceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaspriceApplication.class, args);
	}

}
