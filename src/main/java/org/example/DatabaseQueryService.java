package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseQueryService {
    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();

        try {
            Connection connection = Database.getInstance().getConnection();
            String sql = readScript("sql/find_max_projects_client.sql");

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    int projectCount = resultSet.getInt("PROJECT_COUNT");
                    result.add(new MaxProjectCountClient(name, projectCount));
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorkers() {
        List<MaxSalaryWorker> result = new ArrayList<>();

        try {
            Connection connection = Database.getInstance().getConnection();
            String sql = readScript("sql/find_max_salary_worker.sql");

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    int salary = resultSet.getInt("SALARY");
                    result.add(new MaxSalaryWorker(name, salary));
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<LongestProject> findLongestProjects() {
        List<LongestProject> result = new ArrayList<>();

        try {
            Connection connection = Database.getInstance().getConnection();
            String sql = readScript("sql/find_longest_project.sql");

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
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

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
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

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
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