package school.hei.gestion_des_absences_chez_hei.repository;

import org.springframework.stereotype.Repository;
import school.hei.gestion_des_absences_chez_hei.entity.*;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Repository

public class ImplementDAO implements DAO {
    ConnectionDB connection = new ConnectionDB();

    // CRUD STUDENT


    @Override
    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try (Statement stm = this.connection.getConnection().createStatement();
             ResultSet res = stm.executeQuery(sql)) {

            while (res.next()) {
                Student student = new Student(
                        res.getString("id"),
                        res.getString("firstName"),
                        res.getString("lastName"),
                        res.getString("email"),
                        res.getString("contact"),
                        UniversityYears.valueOf(res.getString("universityYears")),
                        Genre.valueOf(res.getString("genre")),
                        res.getString("status")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public List<Student> getAllStudent(int limit, int offset) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student LIMIT ? OFFSET ?";

        try (PreparedStatement stm = this.connection.getConnection().prepareStatement(sql)) {
            stm.setInt(1, limit);
            stm.setInt(2, offset);

            try (ResultSet res = stm.executeQuery()) {
                while (res.next()) {
                    Student student = new Student(
                            res.getString("id"),
                            res.getString("firstName"),
                            res.getString("lastName"),
                            res.getString("email"),
                            res.getString("contact"),
                            UniversityYears.valueOf(res.getString("universityYears")),
                            Genre.valueOf(res.getString("genre")),
                            res.getString("status")
                    );
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }


    @Override
    public Student getOneStudent(String id) {
        Student student = null;
        String sql = "SELECT * FROM student WHERE id = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet res = ps.executeQuery();

            if (res.next()) {
                student = new Student(
                        res.getString("id"),
                        res.getString("firstName"),
                        res.getString("lastName"),
                        res.getString("email"),
                        res.getString("contact"),
                        UniversityYears.valueOf(res.getString("universityYears")),
                        Genre.valueOf(res.getString("genre")),
                        res.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public void saveStudent(Student student) {
        String sql = "INSERT INTO student (id, firstName, lastName, email, contact, universityYears, genre, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getId());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getContact());
            ps.setString(6, student.getUniversityYears().name());
            ps.setString(7, student.getGenre().name());
            ps.setString(8, student.getStatus());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(String id, Student student) {
        String sql = "UPDATE student SET firstName=?, lastName=?, email=?, contact=?, universityYears=?, genre=?, status=? WHERE id=?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getContact());
            ps.setString(5, student.getUniversityYears().name());
            ps.setString(6, student.getGenre().name());
            ps.setString(7, student.getStatus());
            ps.setString(8, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(String id) {
        String sql = "DELETE FROM student WHERE id = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private List<Student> getPresenceSheet(int courseId) {
        List<Student> absentStudents = new ArrayList<>();
        String sql = "SELECT s.* FROM student s " +
                "JOIN absence a ON s.id = a.student_id " +
                "WHERE a.course_id = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, courseId);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                Student student = new Student(
                        res.getString("id"),
                        res.getString("firstName"),
                        res.getString("lastName"),
                        res.getString("email"),
                        res.getString("contact"),
                        UniversityYears.valueOf(res.getString("universityYears")),
                        Genre.valueOf(res.getString("genre")),
                        res.getString("status")
                );
                absentStudents.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return absentStudents;
    }


    // CRUD COURSE


    @Override
    public List<Course> getAllCourse() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course";

        try (Statement stm = this.connection.getConnection().createStatement();
             ResultSet res = stm.executeQuery(sql)) {

            while (res.next()) {
                Course course = new Course(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getTimestamp("startCourse").toLocalDateTime(),
                        res.getTimestamp("endCourse").toLocalDateTime(),
                        getPresenceSheet(res.getInt("id")) // Récupère la liste des étudiants absents
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    @Override
    public List<Course> getAllCourse(int limit, int offset) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course LIMIT ? OFFSET ?";

        try (PreparedStatement stm = this.connection.getConnection().prepareStatement(sql)) {
            stm.setInt(1, limit);  // Spécifier combien de résultats récupérer
            stm.setInt(2, offset); // Spécifier l'offset pour la pagination

            try (ResultSet res = stm.executeQuery()) {
                while (res.next()) {
                    Course course = new Course(
                            res.getInt("id"),
                            res.getString("name"),
                            res.getTimestamp("startCourse").toLocalDateTime(),
                            res.getTimestamp("endCourse").toLocalDateTime(),
                            getPresenceSheet(res.getInt("id")) // Récupère la liste des étudiants absents
                    );
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    @Override
    public Course getOneCourse(int id) {
        Course course = null;
        String sql = "SELECT * FROM course WHERE id = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();

            if (res.next()) {
                course = new Course(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getTimestamp("startCourse").toLocalDateTime(),
                        res.getTimestamp("endCourse").toLocalDateTime(),
                        getPresenceSheet(id) // Récupère la liste des étudiants absents
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    @Override
    public void saveCourse(Course course) {
        String sql = "INSERT INTO course (name, startCourse, endCourse) VALUES (?, ?, ?)";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, course.getName());
            ps.setObject(2, course.getStarCourse());
            ps.setObject(3, course.getEndCourse());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCourse(int id, Course course) {
        String sql = "UPDATE course SET name = ?, startCourse = ?, endCourse = ? WHERE id = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, course.getName());
            ps.setObject(2, course.getStarCourse());
            ps.setObject(3, course.getEndCourse());
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCourse(int id) {
        String sql = "DELETE FROM course WHERE id = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // CRUD ADMIN

    @Override
    public List<Admin> getAllAdmin() {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM admin";

        try (Statement stm = this.connection.getConnection().createStatement();
             ResultSet res = stm.executeQuery(sql)) {

            while (res.next()) {
                Admin admin = new Admin(
                        res.getString("id"),
                        res.getString("firstName"),
                        res.getString("lastName"),
                        res.getString("email"),
                        res.getString("contact")
                );
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

    @Override
    public List<Admin> getAllAdmin(int limit, int offset) {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM admin LIMIT ? OFFSET ?";

        try (PreparedStatement stm = this.connection.getConnection().prepareStatement(sql)) {
            stm.setInt(1, limit);  // Limite du nombre d'administrateurs à récupérer
            stm.setInt(2, offset); // Définir l'offset pour la pagination

            try (ResultSet res = stm.executeQuery()) {
                while (res.next()) {
                    Admin admin = new Admin(
                            res.getString("id"),
                            res.getString("firstName"),
                            res.getString("lastName"),
                            res.getString("email"),
                            res.getString("contact")
                    );
                    admins.add(admin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }


    @Override
    public Admin getOneAdmin(String id) {
        Admin admin = null;
        String sql = "SELECT * FROM admin WHERE id = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            ResultSet res = ps.executeQuery();

            if (res.next()) {
                admin = new Admin(
                        res.getString("id"),
                        res.getString("firstName"),
                        res.getString("lastName"),
                        res.getString("email"),
                        res.getString("contact")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    @Override
    public void saveAdmin(Admin admin) {
        String sql = "INSERT INTO admin (id, firstName, lastName, email, contact) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, admin.getId());
            ps.setString(2, admin.getFirstName());
            ps.setString(3, admin.getLastName());
            ps.setString(4, admin.getEmail());
            ps.setString(5, admin.getContact());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdmin(String id, Admin admin) {
        String sql = "UPDATE admin SET firstName=?, lastName=?, email=?, contact=? WHERE id=?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, admin.getFirstName());
            ps.setString(2, admin.getLastName());
            ps.setString(3, admin.getEmail());
            ps.setString(4, admin.getContact());
            ps.setString(5, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAdmin(String id) {
        String sql = "DELETE FROM admin WHERE id = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CRUD ABSENCE

    @Override
    public List<Map<String, Object>> getAllAbsences() {
        List<Map<String, Object>> absences = new ArrayList<>();
        String sql = "SELECT a.studentId, c.name AS courseName, c.startCourse, c.endCourse, a.isJustify " +
                "FROM absence a JOIN course c ON a.courseId = c.id";

        try (Statement stm = this.connection.getConnection().createStatement();
             ResultSet res = stm.executeQuery(sql)) {

            while (res.next()) {
                Map<String, Object> absence = new LinkedHashMap<>(); // Utiliser LinkedHashMap pour maintenir l'ordre
                absence.put("studentId", res.getString("studentId"));
                absence.put("courseName", res.getString("courseName"));
                absence.put("startCourse", res.getTimestamp("startCourse").toLocalDateTime());
                absence.put("endCourse", res.getTimestamp("endCourse").toLocalDateTime());
                absence.put("isJustify", res.getBoolean("isJustify"));
                absences.add(absence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return absences;
    }

    @Override
    public List<Map<String, Object>> getAllAbsences(int limit, int offset) {
        List<Map<String, Object>> absences = new ArrayList<>();
        String sql = "SELECT a.studentId, c.name AS courseName, c.startCourse, c.endCourse, a.isJustify " +
                "FROM absence a JOIN course c ON a.courseId = c.id LIMIT ? OFFSET ?";

        try (PreparedStatement stm = this.connection.getConnection().prepareStatement(sql)) {
            stm.setInt(1, limit);
            stm.setInt(2, offset);

            try (ResultSet res = stm.executeQuery()) {
                while (res.next()) {
                    Map<String, Object> absence = new LinkedHashMap<>();
                    absence.put("studentId", res.getString("studentId"));
                    absence.put("courseName", res.getString("courseName"));
                    absence.put("startCourse", res.getTimestamp("startCourse").toLocalDateTime());
                    absence.put("endCourse", res.getTimestamp("endCourse").toLocalDateTime());
                    absence.put("isJustify", res.getBoolean("isJustify"));
                    absences.add(absence);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return absences;
    }


    @Override
    public void addAbsence(String studentId, int courseId) {
        String sql = "INSERT INTO absence (studentId, courseId, isJustify) VALUES (?, ?, false)";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Map<String, Object>> getAbsencesByStudentId(String studentId) {
        List<Map<String, Object>> absences = new ArrayList<>();
        String sql = "SELECT a.studentId, c.name AS courseName, c.startCourse, c.endCourse, a.isJustify " +
                "FROM absence a " +
                "JOIN course c ON a.courseId = c.id WHERE a.studentId = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentId);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                Map<String, Object> absence = new LinkedHashMap<>(); // Utiliser LinkedHashMap pour maintenir l'ordre
                absence.put("studentId", res.getString("studentId"));
                absence.put("courseName", res.getString("courseName"));
                absence.put("startCourse", res.getTimestamp("startCourse").toLocalDateTime());
                absence.put("endCourse", res.getTimestamp("endCourse").toLocalDateTime());
                absence.put("isJustify", res.getBoolean("isJustify"));
                absences.add(absence);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return absences;
    }

    @Override
    public void updateAbsence(String studentId, int courseId, boolean isJustify) {
        String sql = "UPDATE absence SET isJustify = ? WHERE studentId = ? AND courseId = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, isJustify);
            ps.setString(2, studentId);
            ps.setInt(3, courseId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteAbsence(String studentId, int courseId) {
        String sql = "DELETE FROM absence WHERE studentId = ? AND courseId = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // CRUD JUSTIFICATION

    @Override
    public List<Map<String, Object>> getAllJustifications() {
        List<Map<String, Object>> justifications = new ArrayList<>();
        String sql = "SELECT j.studentId, s.firstName AS studentFirstName, s.lastName AS studentLastName, " +
                "c.name AS courseName, j.type, j.description, j.date " +
                "FROM justification j " +
                "JOIN student s ON j.studentId = s.id " +
                "JOIN course c ON j.courseId = c.id";

        try (Statement stm = this.connection.getConnection().createStatement();
             ResultSet res = stm.executeQuery(sql)) {

            while (res.next()) {
                Map<String, Object> justification = new LinkedHashMap<>();
                justification.put("studentId", res.getString("studentId"));
                justification.put("studentFirstName", res.getString("studentFirstName"));
                justification.put("studentLastName", res.getString("studentLastName"));
                justification.put("courseName", res.getString("courseName"));
                justification.put("type", res.getString("type"));
                justification.put("description", res.getString("description"));
                justification.put("date", res.getDate("date").toLocalDate());
                justifications.add(justification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return justifications;
    }


    @Override
    public Map<String, Object> getJustification(String studentId, int courseId) {
        Map<String, Object> justification = null;
        String sql = "SELECT j.studentId, s.firstName AS studentFirstName, s.lastName AS studentLastName, " +
                "c.name AS courseName, j.type, j.description, j.date " +
                "FROM justification j " +
                "JOIN student s ON j.studentId = s.id " +
                "JOIN course c ON j.courseId = c.id " +
                "WHERE j.studentId = ? AND j.courseId = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentId);
            ps.setInt(2, courseId);

            ResultSet res = ps.executeQuery();

            if (res.next()) {
                justification = new LinkedHashMap<>();
                justification.put("studentId", res.getString("studentId"));
                justification.put("studentFirstName", res.getString("studentFirstName"));
                justification.put("studentLastName", res.getString("studentLastName"));
                justification.put("courseName", res.getString("courseName"));
                justification.put("type", res.getString("type"));
                justification.put("description", res.getString("description"));
                justification.put("date", res.getDate("date").toLocalDate());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return justification;
    }


    @Override
    public void saveJustification(Justification justification) {
        String sql = "INSERT INTO justification (studentId, courseId, type, description, date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, justification.getStudentId());
            ps.setInt(2, justification.getCourseId());
            ps.setString(3, justification.getType());
            ps.setString(4, justification.getDescription());
            ps.setDate(5, java.sql.Date.valueOf(justification.getDate()));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateJustification(String studentId, int courseId, Justification justification) {
        String sql = "UPDATE justification SET type=?, description=?, date=? WHERE studentId=? AND courseId=?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, justification.getType());
            ps.setString(2, justification.getDescription());
            ps.setDate(3, java.sql.Date.valueOf(justification.getDate()));
            ps.setString(4, studentId);
            ps.setInt(5, courseId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteJustification(String studentId, int courseId) {
        String sql = "DELETE FROM justification WHERE studentId = ? AND courseId = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentId);
            ps.setInt(2, courseId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
