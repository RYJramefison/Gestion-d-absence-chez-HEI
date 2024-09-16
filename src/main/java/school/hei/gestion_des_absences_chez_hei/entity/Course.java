package school.hei.gestion_des_absences_chez_hei.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode

public class Course {
    private int id;

    private String name;

    private Integer credit;

    private List<Student> presenceSheet;
}
