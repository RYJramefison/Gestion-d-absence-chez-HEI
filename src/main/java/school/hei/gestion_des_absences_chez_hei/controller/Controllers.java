package school.hei.gestion_des_absences_chez_hei.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.gestion_des_absences_chez_hei.entity.*;
import school.hei.gestion_des_absences_chez_hei.service.Services;

import java.util.List;
import java.util.Map;

@AllArgsConstructor

@RestController
public class Controllers {
    private Services services;


    // CONTROLLER STUDENT

    @GetMapping("/students")
    public List<Student> getALlStudent(){
        return services.getAllStudent();
    }

    @GetMapping("/search")
    public List<Student> getAllStudents(@RequestParam @MatrixVariable int page,@RequestParam  @MatrixVariable int size) {
        int offset = page * size;
        return services.getAllStudent(size, offset);
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable String id) {
        return services.getOneStudent(id);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Void> createStudent(@RequestBody Student toAdd) {
        services.addStudent(toAdd);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        services.removeStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable String id, @RequestBody Student student) {
        services.updateStudent(id, student);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    // CONTROLLER COURSE

    @GetMapping("/courses")
    public List<Course> getALlCourse() {
        return services.getAllCourse();
    }

    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable int id) {
        return services.getOneCourse(id);
    }

    @PostMapping("/addCourse")
    public ResponseEntity<Void> createCourse(@RequestBody Course toAdd) {
        services.addCourse(toAdd);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        services.removeCourse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable int id, @RequestBody Course course) {
        services.updateCourse(id, course);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    // CONTROLLER ADMIN

    @GetMapping("/admins")
    public List<Admin> getALlAdmin(){
        return services.getAllAdmin();
    }

    @GetMapping("/admins/{id}")
    public Admin getAdminById(@PathVariable String id) {
        return services.getOneAdmin(id);
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<Void> createAdmin(@RequestBody Admin toAdd) {
        services.addAdmin(toAdd);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable String id) {
        services.removeAdmin(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<Void> updateAdmin(@PathVariable String id, @RequestBody Admin admin) {
        services.updateAdmin(id, admin);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    // CONTROLLER ABSENCE

    @GetMapping("/absents")
    public List<Map<String, Object>> getAllAbsences() {
        return services.getAllAbsences();
    }

    @GetMapping("/absents/{id}")
    public List<Map<String, Object>> getAbsencesByStudentId(@PathVariable String id) {
        return services.getAbsencesByStudentId(id);
    }

    @PutMapping("/absent")
    public ResponseEntity<Void> updateAbsence(@RequestParam String studentId, @RequestParam int courseId, @RequestParam boolean isJustify) {
        services.updateAbsence(studentId, courseId, isJustify);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/addAbsent")
    public ResponseEntity<Void> createAbsence(@RequestBody Absence absenceRequest) {
        services.addAbsence(absenceRequest.getStudentId(), absenceRequest.getCourseId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/deleteAbsent")
    public ResponseEntity<Void> deleteAbsence(@RequestParam String studentId, @RequestParam int courseId) {
        services.deleteAbsence(studentId, courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    // CONTROLLER JUSTIFICATION

    @GetMapping("/justifications")
    public List<Map<String, Object>> getAllJustifications() {
        return services.getAllJustifications();
    }

    @GetMapping("/justification/{studentId}/{courseId}")
    public Map<String, Object> getJustification(@PathVariable String studentId, @PathVariable int courseId) {
        return services.getJustification(studentId, courseId);
    }

    @PostMapping("/justification")
    public void saveJustification(@RequestBody Justification justification) {
        services.saveJustification(justification);
    }

    @PutMapping("/justification/{studentId}/{courseId}")
    public void updateJustification(@PathVariable String studentId, @PathVariable int courseId,
                                    @RequestBody Justification justification) {
        services.updateJustification(studentId, courseId, justification);
    }

    @DeleteMapping("/justification/{studentId}/{courseId}")
    public void deleteJustification(@PathVariable String studentId, @PathVariable int courseId) {
        services.deleteJustification(studentId, courseId);
    }
}
