package school.hei.gestion_des_absences_chez_hei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"school.hei.gestion_des_absences_chez_hei.controller"})
public class GestionDesAbsencesChezHeiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionDesAbsencesChezHeiApplication.class, args);
    }

}
