package school.hei.gestion_des_absences_chez_hei.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private Connection connection = null;
    public ConnectionDB() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(System.getenv("db_url"), System.getenv("db_user"), System.getenv("db_password"));
            System.out.println("bienvenue sur le projet de gestion d'abscence shez HEI");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}