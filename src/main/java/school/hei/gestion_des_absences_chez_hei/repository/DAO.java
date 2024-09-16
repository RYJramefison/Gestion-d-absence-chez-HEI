package school.hei.gestion_des_absences_chez_hei.repository;

import school.hei.gestion_des_absences_chez_hei.entity.Student;

import java.util.List;

public interface DAO {

    List<Student> getAllStudent();

    void saveStudent(Student student);

    void updateStudent(String id, Student student);

    void deleteStudent(String id);

}
