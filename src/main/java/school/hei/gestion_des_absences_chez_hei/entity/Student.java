package school.hei.gestion_des_absences_chez_hei.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Student extends Person{
    private String id;

    private String universityYears;

    private String status;

    public Student( String id, String firstName, String lastName, String email, String contact, String universityYears, String status) {
        super(firstName, lastName, email, contact);
        this.id = id;
        this.universityYears = universityYears;
        this.status = status;
    }
}
