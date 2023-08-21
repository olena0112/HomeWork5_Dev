package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DatabaseQueryService {
    public List<MaxProjectCountClient> findMaxProjectsClient(int clientId) {
        List<MaxProjectCountClient> result = new ArrayList<>();
        try {
            Connection connection = Database.getInstance().getConnection();
            String sql = "SELECT c.NAME, COUNT(p.ID) AS PROJECT_COUNT " +
                    "FROM client c " +
                    "LEFT JOIN project p ON c.ID = p.CLIENT_ID " +
                    "GROUP BY c.ID, c.NAME " +
                    "HAVING COUNT(p.ID) = (SELECT MAX(project_count) FROM (SELECT COUNT(ID) AS project_count FROM project WHERE CLIENT_ID = ?) AS subquery)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, clientId);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    int projectCount = resultSet.getInt("PROJECT_COUNT");
                    result.add(new MaxProjectCountClient(name, projectCount));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorkers() {
        List<MaxSalaryWorker> result = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT w.NAME, w.SALARY " +
                             "FROM worker w " +
                             "WHERE w.SALARY = (SELECT MAX(SALARY) FROM worker)")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    int salary = resultSet.getInt("SALARY");
                    result.add(new MaxSalaryWorker(name, salary));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<LongestProject> findLongestProjects() {
        List<LongestProject> result = new ArrayList<>();
        try {
            Connection connection = Database.getInstance().getConnection();
            String sql = readScript("sql/find_longest_project.sql");

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    int monthCount = resultSet.getInt("MONTH_COUNT");
                    result.add(new LongestProject(name, monthCount));
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<YoungestEldestWorker> findYoungestAndEldestWorkers() {
        List<YoungestEldestWorker> result = new ArrayList<>();
        try {
            Connection connection = Database.getInstance().getConnection();
            String sql = readScript("sql/find_youngest_eldest_workers.sql");

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String type = resultSet.getString("TYPE");
                    String name = resultSet.getString("NAME");
                    String birthday = resultSet.getString("BIRTHDAY");
                    result.add(new YoungestEldestWorker(type, name, birthday));
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<ProjectPrice> printProjectPrices() {
        List<ProjectPrice> result = new ArrayList<>();
        try {
            Connection connection = Database.getInstance().getConnection();
            String sql = readScript("sql/print_project_prices.sql");

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    int price = resultSet.getInt("PRICE");
                    result.add(new ProjectPrice(name, price));
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String readScript(String scriptPath) throws IOException {
        Path path = Paths.get(scriptPath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }
}
