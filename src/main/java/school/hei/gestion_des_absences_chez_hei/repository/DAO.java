package school.hei.gestion_des_absences_chez_hei.repository;

import school.hei.gestion_des_absences_chez_hei.entity.Admin;
import school.hei.gestion_des_absences_chez_hei.entity.Course;
import school.hei.gestion_des_absences_chez_hei.entity.Student;

import java.util.List;

public interface DAO {

    // CRUD STUDENT

    List<Student> getAllStudent();

    Student getOneStudent(int id);

    void saveStudent(Student student);

    void updateStudent(int id, Student student);

    void deleteStudent(int id);

    // CRUD COURSE

    List<Course> getAllCourse();

    Course getOneCourse(int id);

    void saveCourse(Course course);

    void updateCourse(int id, Course course);

    void deleteCourse(int id);

    // CRUD ADMIN

    List<Admin> getAllAdmin();

    Admin getOneAdmin(int id);

    void saveAdmin(Admin admin);

    void updateAdmin(int id, Admin admin);

    void deleteAdmin(int id);

    // CRUD ABSENCE


}
