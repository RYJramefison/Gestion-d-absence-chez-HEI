package school.hei.gestion_des_absences_chez_hei.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Setter
@AllArgsConstructor
@EqualsAndHashCode

public class COR {
    private int id;

    private String etat;

    private LocalDateTime date;

    private String studentID;
}
