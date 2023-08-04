package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DatabasePopulateService {
    public static void main(String[] args) {
        String populateScriptPath = "sql/populate_db.sql";

        try {
            String populateScript = readScript(populateScriptPath);
            executeScript(populateScript);
            System.out.println("Database populated successfully.");
        } catch (IOException e) {
            System.err.println("Failed to read the populate script: " + populateScriptPath);
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to execute the populate script.");
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

        // Split the script into separate statements
        String[] statements = script.split(";");

        try {
            for (String statement : statements) {
                statement = statement.trim();
                if (!statement.isEmpty()) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
                        preparedStatement.setInt(1, 123);
                        preparedStatement.setString(2, "Some Value");
                     preparedStatement.executeUpdate();
                    }
                }
            }
        } finally {
            connection.close();
        }
    }
}
