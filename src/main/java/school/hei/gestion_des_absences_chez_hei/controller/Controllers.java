package school.hei.gestion_des_absences_chez_hei.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Controllers {
    @GetMapping("/hello")
    public String HelloWorld(){
        return "hi";
    }
}
