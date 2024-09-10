package id.mhafizsir.fgswsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FgsWsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FgsWsDemoApplication.class, args);
    }

}
