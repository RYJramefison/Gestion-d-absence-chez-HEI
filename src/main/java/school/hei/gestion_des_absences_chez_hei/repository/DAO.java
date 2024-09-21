package school.hei.gestion_des_absences_chez_hei.repository;

import school.hei.gestion_des_absences_chez_hei.entity.Absence;
import school.hei.gestion_des_absences_chez_hei.entity.Admin;
import school.hei.gestion_des_absences_chez_hei.entity.Course;
import school.hei.gestion_des_absences_chez_hei.entity.Student;

import java.util.List;
import java.util.Map;

public interface DAO {

    // CRUD STUDENT

    List<Student> getAllStudent();

    Student getOneStudent(String id);

    void saveStudent(Student student);

    void updateStudent(String id, Student student);

    void deleteStudent(String id);

    // CRUD COURSE

    List<Course> getAllCourse();

    Course getOneCourse(int id);

    void saveCourse(Course course);

    void updateCourse(int id, Course course);

    void deleteCourse(int id);

    // CRUD ADMIN

    List<Admin> getAllAdmin();

    Admin getOneAdmin(String id);

    void saveAdmin(Admin admin);

    void updateAdmin(String id, Admin admin);

    void deleteAdmin(String id);

    // CRUD ABSENCE

    List<Map<String, Object>> getAllAbsences();

    void addAbsence(String studentId, int courseId);

    List<Map<String, Object>> getAbsencesByStudentId(String studentId);

    void deleteAbsence(String studentId, int courseId);

}
