package school.hei.gestion_des_absences_chez_hei.service;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Service;
import school.hei.gestion_des_absences_chez_hei.entity.Admin;
import school.hei.gestion_des_absences_chez_hei.entity.Course;
import school.hei.gestion_des_absences_chez_hei.entity.Student;
import school.hei.gestion_des_absences_chez_hei.repository.ImplementDAO;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Service

public class Services {
    private ImplementDAO DAO;

    // service STUDENT

    public List<Student> getAllStudent(){
        return DAO.getAllStudent();
    }


    public void addStudent(Student student){
        DAO.saveStudent(student);
    }

    public void removeStudent(String id){
        DAO.deleteStudent(id);
    }

    public void updateStudent(String id, Student toUpdate){
        DAO.updateStudent(id, toUpdate);
    }

    public Student getOneStudent(String id){
        return DAO.getOneStudent(id);
    }

    // SERVICE COURSE

    public List<Course> getAllCourse(){
        return DAO.getAllCourse();
    }

    public void addCourse(Course course){
        DAO.saveCourse(course);
    }

    public void removeCourse(int id){
        DAO.deleteCourse(id);
    }

    public void updateCourse(int id, Course toUpdate){
        DAO.updateCourse(id, toUpdate);
    }

    public Course getOneCourse(int id){
        return DAO.getOneCourse(id);
    }

    // SERVICE ADMIN

    public List<Admin> getAllAdmin(){
        return DAO.getAllAdmin();
    }

    public void addAdmin(Admin admin){
        DAO.saveAdmin(admin);
    }

    public void removeAdmin(String id){
        DAO.deleteAdmin(id);
    }

    public void updateAdmin(String id, Admin toUpdate){
        DAO.updateAdmin(id, toUpdate);
    }

    public Admin getOneAdmin(String id){
        return DAO.getOneAdmin(id);
    }
}
