package school.hei.gestion_des_absences_chez_hei.repository;

import org.springframework.stereotype.Repository;
import school.hei.gestion_des_absences_chez_hei.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository

public class ImplementDAO implements DAO {
    ConnectionDB connection = new ConnectionDB();

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

    }

    @Override
    public void deleteStudent(String id) {

    }
}
