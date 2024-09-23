package school.hei.gestion_des_absences_chez_hei.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Setter
@AllArgsConstructor
@EqualsAndHashCode

public class COR {
    private int id;

    private String status;

    private LocalDate date;

    private String studentID;
}
