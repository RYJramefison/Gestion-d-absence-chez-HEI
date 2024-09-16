package school.hei.gestion_des_absences_chez_hei.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import school.hei.gestion_des_absences_chez_hei.entity.Student;
import school.hei.gestion_des_absences_chez_hei.service.Services;

import java.util.List;

@AllArgsConstructor

@RestController
public class Controllers {
    private Services services;

    @GetMapping("/hello")
    public String HelloWorld(){
        return "hi";
    }

    @GetMapping("/allStudent")
    public List<Student> getALlTodo(){
        return services.getAllTodos();
    }
}
