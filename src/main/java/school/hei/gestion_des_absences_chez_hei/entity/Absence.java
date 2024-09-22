package school.hei.gestion_des_absences_chez_hei.entity;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class Absence {
    private int id;

    private int courseId;

    private String studentId;

    private Boolean isJustify;
}
