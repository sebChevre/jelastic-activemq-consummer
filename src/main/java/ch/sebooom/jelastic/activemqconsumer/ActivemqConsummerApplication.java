package ch.sebooom.jelastic.activemqconsumer;



import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ActivemqConsummerApplication {


	public static void main(String[] args) {
		SpringApplication.run(ActivemqConsummerApplication.class, args);
	}

}
