package school.hei.gestion_des_absences_chez_hei.entity;

import lombok.*;

@Getter
@Setter
@ToString

public class Admin extends Person{


    public Admin(int id, String firstName, String lastName, String email, String contact) {
        super(id, firstName, lastName, email, contact);
    }
}
