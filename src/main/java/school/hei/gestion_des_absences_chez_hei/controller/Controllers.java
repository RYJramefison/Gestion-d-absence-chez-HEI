package school.hei.gestion_des_absences_chez_hei.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.gestion_des_absences_chez_hei.entity.Student;
import school.hei.gestion_des_absences_chez_hei.service.Services;

import java.util.List;

@AllArgsConstructor

@RestController
public class Controllers {
    private Services services;

    @GetMapping("/allStudent")
    public List<Student> getALlTodo(){
        return services.getAllTodos();
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Void> createTodo(@RequestBody Student toAdd) {
        services.addStudent(toAdd);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        services.removeStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
