package school.hei.gestion_des_absences_chez_hei.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString

public class Person {
    private String id;

    private String name;

    private String email;

    private String contact;

}
