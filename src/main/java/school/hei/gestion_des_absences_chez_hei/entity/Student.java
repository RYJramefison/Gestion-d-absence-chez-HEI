package school.hei.gestion_des_absences_chez_hei.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Student extends Person{
    private String status;

    private String universityYears;

    public Student(String id, String name, String email, String contact, String status, String universityYears) {
        super(id, name, email, contact);
        this.status = status;
        this.universityYears = universityYears;
    }

}
