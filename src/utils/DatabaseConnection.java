package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {

    }

    public void connectToDatabase() throws SQLException {

        String server = "localhost";
        String port = "1433";
        String database = "QLCF";
        String userName = "sa";
        String password = "quangtuan1201";
        connection = DriverManager.getConnection("jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";encrypt=true;trustServerCertificate=true", userName, password);

    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
