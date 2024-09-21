package school.hei.gestion_des_absences_chez_hei.repository;

import org.springframework.stereotype.Repository;
import school.hei.gestion_des_absences_chez_hei.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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




    @Override
    public List<Map<String, Object>> getAllAbsences() {
        List<Map<String, Object>> absences = new ArrayList<>();
        String sql = "SELECT a.student_id, c.name AS course_name, c.startCourse, c.endCourse " +
                "FROM absence a " +
                "JOIN course c ON a.course_id = c.id";

        try (Statement stm = this.connection.getConnection().createStatement();
             ResultSet res = stm.executeQuery(sql)) {

            while (res.next()) {
                Map<String, Object> absence = new HashMap<>();
                absence.put("student_id", res.getString("student_id"));
                absence.put("course_name", res.getString("course_name"));
                absence.put("startCourse", res.getTimestamp("startCourse").toLocalDateTime());
                absence.put("endCourse", res.getTimestamp("endCourse").toLocalDateTime());
                absences.add(absence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return absences;
    }


    @Override
    public void addAbsence(String studentId, int courseId) {
        String sql = "INSERT INTO absence (student_id, course_id) VALUES (?, ?)";

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
        String sql = "SELECT a.student_id, c.name AS course_name, c.startCourse, c.endCourse " +
                "FROM absence a " +
                "JOIN course c ON a.course_id = c.id WHERE a.student_id = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentId);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                Map<String, Object> absence = new HashMap<>();
                absence.put("student_id", res.getString("student_id"));
                absence.put("course_name", res.getString("course_name"));
                absence.put("startCourse", res.getTimestamp("startCourse").toLocalDateTime());
                absence.put("endCourse", res.getTimestamp("endCourse").toLocalDateTime());
                absences.add(absence);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return absences;
    }

    @Override
    public void deleteAbsence(String studentId, int courseId) {
        String sql = "DELETE FROM absence WHERE student_id = ? AND course_id = ?";

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
