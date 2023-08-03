package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {

        private static final String DB_URL = "jdbc:h2:./data/sample";
        private static final String DB_USER = "sa";
        private static final String DB_PASSWORD = "1234567890";

        private static Database instance;
        private Connection connection;

        private Database() {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static synchronized Database getInstance() {
            if (instance == null) {
                instance = new Database();
            }
            return instance;
        }

        public Connection getConnection() {
            return connection;
        }

}
