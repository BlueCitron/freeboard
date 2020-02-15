package bluecitron.sample.freeboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SampleBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleBoardApplication.class, args);
	}

}
