package school.hei.gestion_des_absences_chez_hei.entity;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Admin extends Person{
    private int id;

    public Admin( int id, String firstName, String lastName, String email, String contact) {
        super(firstName, lastName, email, contact);
        this.id = id;
    }

}
