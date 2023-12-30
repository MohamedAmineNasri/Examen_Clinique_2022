package tn.esprit.examenclinique2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExamenClinique2022Application {

    public static void main(String[] args) {
        SpringApplication.run(ExamenClinique2022Application.class, args);
    }

}
