package school.hei.gestion_des_absences_chez_hei.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class Justification {
    private String studentId;

    private int courseId;

    private String type;

    private String description;

    private LocalDate date;
}
