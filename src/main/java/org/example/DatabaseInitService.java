package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.nio.file.Path;

public class DatabaseInitService {
    public static void main(String[] args) {
        String initScriptPath = "sql/init_db.sql";

        try {
            String initScript = readScript(initScriptPath);
            executeScript(initScript);
            System.out.println("Database initialized successfully.");
        } catch (IOException e) {
            System.err.println("Failed to read the init script: " + initScriptPath);
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to execute the init script.");
            e.printStackTrace();
        }
    }

    private static String readScript(String scriptPath) throws IOException {
        Path path = Paths.get(scriptPath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }

    private static void executeScript(String script) throws SQLException {
        Connection connection = Database.getInstance().getConnection();

        String[] statements = script.split(";");

        try (PreparedStatement preparedStatement = connection.prepareStatement("")) {
            for (String statement : statements) {
                String trimmedStatement = statement.trim();
                if (!trimmedStatement.isEmpty()) {

                    preparedStatement.addBatch(trimmedStatement);
                }
            }
            preparedStatement.executeBatch();
        }
    }
}



