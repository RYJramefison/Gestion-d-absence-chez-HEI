package school.hei.gestion_des_absences_chez_hei.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter

public class CourseStudent {
    private int courseId;

    private int studentId;
}
