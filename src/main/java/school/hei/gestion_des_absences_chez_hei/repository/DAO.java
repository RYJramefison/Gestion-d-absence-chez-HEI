package school.hei.gestion_des_absences_chez_hei.repository;

import school.hei.gestion_des_absences_chez_hei.entity.*;

import java.util.List;
import java.util.Map;

public interface DAO {

    // CRUD STUDENT

    List<Student> getAllStudent();

    List<Student> getAllStudent(int limit, int offset);

    Student getOneStudent(String id);

    void saveStudent(Student student);

    void updateStudent(String id, Student student);

    void deleteStudent(String id);

    // CRUD COURSE

    List<Course> getAllCourse();

    List<Course> getAllCourse(int limit, int offset);

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

    public void updateAbsence(String studentId, int courseId, boolean isJustify);

    void deleteAbsence(String studentId, int courseId);

    // CRUD JUSTIFICATION

     List<Map<String, Object>> getAllJustifications();

     Map<String, Object> getJustification(String studentId, int courseId);

    void saveJustification(Justification justification);

    void updateJustification(String studentId, int courseId, Justification justification);

    void deleteJustification(String studentId, int courseId);

}
