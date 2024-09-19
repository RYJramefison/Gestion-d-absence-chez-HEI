package school.hei.gestion_des_absences_chez_hei.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Student extends Person{
    private String reference;

    private String universityYears;

    private String status;

    public Student(int id, String firstName, String lastName, String email, String contact, String reference, String universityYears, String status) {
        super(id, firstName, lastName, email, contact);
        this.reference = reference;
        this.universityYears = universityYears;
        this.status = status;
    }
}
