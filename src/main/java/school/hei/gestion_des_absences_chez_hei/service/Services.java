package school.hei.gestion_des_absences_chez_hei.service;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Service;
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

    public List<Student> getAllTodos(){
        return DAO.getAllStudent();
    }


    public void addStudent(Student student){
        DAO.saveStudent(student);
    }
}
