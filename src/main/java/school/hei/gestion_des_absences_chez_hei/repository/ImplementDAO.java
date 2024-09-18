package school.hei.gestion_des_absences_chez_hei.repository;

import org.springframework.stereotype.Repository;
import school.hei.gestion_des_absences_chez_hei.entity.Admin;
import school.hei.gestion_des_absences_chez_hei.entity.Course;
import school.hei.gestion_des_absences_chez_hei.entity.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository

public class ImplementDAO implements DAO {
    ConnectionDB connection = new ConnectionDB();

    // CRUD  STUDENT

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
                        res.getString("universityYears"),
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
                        res.getString("universityYears"),
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
        String sql = "INSERT INTO student (id, firstName, lastName, email, contact, universityYears, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getId());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getContact());
            ps.setString(6, student.getUniversityYears());
            ps.setString(7, student.getStatus());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void updateStudent(String id, Student student) {
        String sql = "UPDATE student SET id=?, firstName=?, lastName=?, email=?, contact=?, universityYears=?, status=? WHERE id=?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getId());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getContact());
            ps.setString(6, student.getUniversityYears());
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

            int affectedRows = ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                        res.getObject("presenceSheet", List.class),
                        res.getObject("date", LocalDate.class)
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
                        res.getObject("presenceSheet", List.class),
                        res.getObject("date", LocalDate.class)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    @Override
    public void saveCourse(Course course) {
        String sql = "INSERT INTO course (id, name, presenceSheet) VALUES (?, ?, ?)";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, course.getId());
            ps.setString(2, course.getName());
            ps.setObject(3, course.getPresenceSheet());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void updateCourse(int id, Course course) {
        String sql = "UPDATE course SET id=?, name=?, precenseSheet=? WHERE id=?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, course.getId());
            ps.setString(2, course.getName());
            ps.setObject(3, course.getPresenceSheet());
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
                        res.getInt("id"),
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
    public Admin getOneAdmin(int id) {
        Admin admin = null;
        String sql = "SELECT * FROM admin WHERE id = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet res = ps.executeQuery();

            if (res.next()) {
                admin = new Admin(
                        res.getInt("id"),
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

            ps.setInt(1, admin.getId());
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
    public void updateAdmin(int id, Admin admin) {
        String sql = "UPDATE admin SET id=?, firstName=?, lastName=?, email=?, contact=? WHERE id=?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, admin.getId());
            ps.setString(2, admin.getFirstName());
            ps.setString(3, admin.getLastName());
            ps.setString(4, admin.getEmail());
            ps.setString(5, admin.getContact());
            ps.setInt(8, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAdmin(int id) {
        String sql = "DELETE FROM admin WHERE id = ?";

        try (Connection conn = this.connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
