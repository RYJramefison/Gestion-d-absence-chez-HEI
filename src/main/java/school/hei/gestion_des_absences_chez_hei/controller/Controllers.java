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

    @GetMapping("/students/search")
    public List<Student> getAllStudents(@RequestParam @MatrixVariable int page,@RequestParam  @MatrixVariable int size) {
        int offset = page * size;
        return services.getAllStudent(size, offset);
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable String id) {
        return services.getOneStudent(id);
    }

    @PostMapping("/student")
    public ResponseEntity<Void> createStudent(@RequestBody Student toAdd) {
        services.addStudent(toAdd);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        services.removeStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable String id, @RequestBody Student student) {
        services.updateStudent(id, student);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    // CONTROLLER COURSE

    @GetMapping("/courses")
    public List<Course> getALlCourse() {
        return services.getAllCourse();
    }

    @GetMapping("/courses/search")
    public List<Course> getAllCourses(@RequestParam @MatrixVariable int page,@RequestParam  @MatrixVariable int size) {
        int offset = page * size;
        return services.getAllCourse(size, offset);
    }

    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable int id) {
        return services.getOneCourse(id);
    }

    @PostMapping("/course")
    public ResponseEntity<Void> createCourse(@RequestBody Course toAdd) {
        services.addCourse(toAdd);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        services.removeCourse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable int id, @RequestBody Course course) {
        services.updateCourse(id, course);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    // CONTROLLER ADMIN

    @GetMapping("/admins")
    public List<Admin> getALlAdmin(){
        return services.getAllAdmin();
    }

    @GetMapping("/admins/search")
    public List<Admin> getAllAdmins(@RequestParam @MatrixVariable int page,@RequestParam  @MatrixVariable int size) {
        int offset = page * size;
        return services.getAllAdmin(size, offset);
    }

    @GetMapping("/admins/{id}")
    public Admin getAdminById(@PathVariable String id) {
        return services.getOneAdmin(id);
    }

    @PostMapping("/admin")
    public ResponseEntity<Void> createAdmin(@RequestBody Admin toAdd) {
        services.addAdmin(toAdd);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable String id) {
        services.removeAdmin(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Void> updateAdmin(@PathVariable String id, @RequestBody Admin admin) {
        services.updateAdmin(id, admin);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    // CONTROLLER ABSENCE

    @GetMapping("/absences")
    public List<Map<String, Object>> getAllAbsences() {
        return services.getAllAbsences();
    }

    @GetMapping("/absences/search")
    public List<Map<String, Object>> getAllAbsences(@RequestParam @MatrixVariable int page,@RequestParam  @MatrixVariable int size) {
        int offset = page * size;
        return services.getAllAbsences(size, offset);
    }

    @GetMapping("/absences/{id}")
    public List<Map<String, Object>> getAbsencesByStudentId(@PathVariable String id) {
        return services.getAbsencesByStudentId(id);
    }

    @PutMapping("/absence")
    public ResponseEntity<Void> updateAbsence(@RequestParam String studentId, @RequestParam int courseId, @RequestParam boolean isJustify) {
        services.updateAbsence(studentId, courseId, isJustify);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/absence")
    public ResponseEntity<Void> createAbsence(@RequestBody Absence absenceRequest) {
        services.addAbsence(absenceRequest.getStudentId(), absenceRequest.getCourseId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/absence")
    public ResponseEntity<Void> deleteAbsence(@RequestParam String studentId, @RequestParam int courseId) {
        services.deleteAbsence(studentId, courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    // CONTROLLER JUSTIFICATION

    @GetMapping("/justifications")
    public List<Map<String, Object>> getAllJustifications() {
        return services.getAllJustifications();
    }

    @GetMapping("/justifications/search")
    public List<Map<String, Object>> getAllJustifications(@RequestParam @MatrixVariable int page,@RequestParam  @MatrixVariable int size) {
        int offset = page * size;
        return services.getAllJustifications(size, offset);
    }

    @GetMapping("/justifications/{studentId}/{courseId}")
    public Map<String, Object> getJustification(@PathVariable String studentId, @PathVariable int courseId) {
        return services.getJustification(studentId, courseId);
    }

    @PostMapping("/justification")
    public void saveJustification(@RequestBody Justification justification) {
        services.saveJustification(justification);
    }

    @PutMapping("/justification/{studentId}/{courseId}")
    public void updateJustification(@PathVariable String studentId, @PathVariable int courseId, @RequestBody Justification justification) {
        services.updateJustification(studentId, courseId, justification);
    }

    @DeleteMapping("/justification/{studentId}/{courseId}")
    public void deleteJustification(@PathVariable String studentId, @PathVariable int courseId) {
        services.deleteJustification(studentId, courseId);
    }


    // CONTROLLER COR

    @GetMapping("/cors")
    public List<Map<String, Object>> getAllCORs() {
        return services.getAllCORs();
    }

    @GetMapping("/cors/search")
    public List<Map<String, Object>> getAllCORs(@RequestParam @MatrixVariable int page,@RequestParam  @MatrixVariable int size) {
        int offset = page * size;
        return services.getAllCORs(size, offset);
    }

    @GetMapping("/cors/{studentId}/{corId}")
    public Map<String, Object> getCOR(@PathVariable String studentId, @PathVariable int corId) {
        return services.getCOR(studentId, corId);
    }

    @PostMapping("/cor")
    public void saveCOR(@RequestBody COR cor) {
        services.saveCOR(cor);
    }

    @PutMapping("/cor/{studentId}/{corId}")
    public void updateCOR(@PathVariable String studentId, @PathVariable int corId, @RequestBody COR cor) {
        services.updateCOR(studentId, corId, cor);
    }

    @DeleteMapping("/cor/{studentId}/{corId}")
    public void deleteCOR(@PathVariable String studentId, @PathVariable int corId) {
        services.deleteCOR(studentId, corId);
    }
}
