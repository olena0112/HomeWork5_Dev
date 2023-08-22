package org.example;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;


public class DatabasePopulateService {
    public static void main(String[] args) {
                try (Connection connection = Database.getInstance().getConnection()) {
                    String sql = "INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setString(1, "John Doe");
                    preparedStatement.setDate(2, java.sql.Date.valueOf("1990-01-01"));
                    preparedStatement.setString(3, "Trainee");
                    preparedStatement.setInt(4, 800);
                    int rowsInserted1 = preparedStatement.executeUpdate();

                    preparedStatement.setString(1, "Jane Smith");
                    preparedStatement.setDate(2, java.sql.Date.valueOf("1985-05-15"));
                    preparedStatement.setString(3, "Junior");
                    preparedStatement.setInt(4, 1200);
                    int rowsInserted2 = preparedStatement.executeUpdate();

                    preparedStatement.setString(1, "Mike Johnson");
                    preparedStatement.setDate(2, java.sql.Date.valueOf("1982-09-30"));
                    preparedStatement.setString(3, "Middle");
                    preparedStatement.setInt(4, 3000);
                    int rowsInserted3 = preparedStatement.executeUpdate();

                    preparedStatement.setString(1, "Emily Davis");
                    preparedStatement.setDate(2, java.sql.Date.valueOf("1978-03-10"));
                    preparedStatement.setString(3, "Senior");
                    preparedStatement.setInt(4, 6000);
                    int rowsInserted4 = preparedStatement.executeUpdate();

                    preparedStatement.setString(1, "Robert Wilson");
                    preparedStatement.setDate(2, java.sql.Date.valueOf("1992-07-20"));
                    preparedStatement.setString(3, "Middle");
                    preparedStatement.setInt(4, 2500);
                    int rowsInserted5 = preparedStatement.executeUpdate();

                    preparedStatement.setString(1, "Sarah Brown");
                    preparedStatement.setDate(2, java.sql.Date.valueOf("1995-12-05"));
                    preparedStatement.setString(3, "Junior");
                    preparedStatement.setInt(4, 1500);
                    int rowsInserted6 = preparedStatement.executeUpdate();

                    preparedStatement.setString(1, "David Taylor");
                    preparedStatement.setDate(2, java.sql.Date.valueOf("1989-06-18"));
                    preparedStatement.setString(3, "Middle");
                    preparedStatement.setInt(4, 2800);
                    int rowsInserted7 = preparedStatement.executeUpdate();

                    preparedStatement.setString(1, "Laura Anderson");
                    preparedStatement.setDate(2, java.sql.Date.valueOf("1987-09-22"));
                    preparedStatement.setString(3, "Senior");
                    preparedStatement.setInt(4, 5500);
                    int rowsInserted8 = preparedStatement.executeUpdate();

                    preparedStatement.setString(1, "Mark Thompson");
                    preparedStatement.setDate(2, java.sql.Date.valueOf("1991-02-14"));
                    preparedStatement.setString(3, "Junior");
                    preparedStatement.setInt(4, 1400);
                    int rowsInserted9 = preparedStatement.executeUpdate();

                    preparedStatement.setString(1, "Amy Roberts");
                    preparedStatement.setDate(2, java.sql.Date.valueOf("1993-11-08"));
                    preparedStatement.setString(3, "Trainee");
                    preparedStatement.setInt(4, 900);
                    int rowsInserted10 = preparedStatement.executeUpdate();

                    if (rowsInserted1 > 0 && rowsInserted2 > 0 && rowsInserted3 > 0 && rowsInserted4 > 0 && rowsInserted5 > 0
                            && rowsInserted6 > 0 && rowsInserted7 > 0 && rowsInserted8 > 0 && rowsInserted9 > 0
                            && rowsInserted10 > 0) {
                        System.out.println("10 new workers were inserted successfully!");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
        try (Connection connection = Database.getInstance().getConnection()) {
            String sql = "INSERT INTO client (NAME) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "ABC Company");
            int rowsInserted1 = preparedStatement.executeUpdate();
            preparedStatement.setString(1, "XYZ Corporation");
            int rowsInserted2 = preparedStatement.executeUpdate();
            preparedStatement.setString(1, "123 Industries");
            int rowsInserted3 = preparedStatement.executeUpdate();
            preparedStatement.setString(1, "Tech Solutions");
            int rowsInserted4 = preparedStatement.executeUpdate();
            preparedStatement.setString(1, "Global Enterprises");
            int rowsInserted5 = preparedStatement.executeUpdate();
            if (rowsInserted1 > 0 && rowsInserted2 > 0 && rowsInserted3 > 0 && rowsInserted4 > 0 && rowsInserted5 > 0) {
                System.out.println("5 new clients were inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = Database.getInstance().getConnection()) {
            String sql = "INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            List<ProjectData> projects = Arrays.asList(
                    new ProjectData(1, "2022-01-01", "2022-05-31"),
                    new ProjectData(1, "2022-03-15", "2023-06-30"),
                    new ProjectData(2, "2022-06-01", "2023-02-28"),
                    new ProjectData(3, "2022-09-01", "2023-04-30"),
                    new ProjectData(4, "2022-12-01", "2023-12-31"),
                    new ProjectData(2, "2023-03-01", "2023-05-31"),
                    new ProjectData(3, "2023-06-15", "2024-03-31"),
                    new ProjectData(4, "2023-09-01", "2024-08-31"),
                    new ProjectData(5, "2024-01-01", "2024-04-30"),
                    new ProjectData(5, "2024-03-15", "2024-07-31")
            );
            int rowsInserted = 0;
            for (ProjectData project : projects) {
                preparedStatement.setInt(1, project.getClientId());
                preparedStatement.setDate(2, java.sql.Date.valueOf(project.getStartDate()));
                preparedStatement.setDate(3, java.sql.Date.valueOf(project.getFinishDate()));

                rowsInserted += preparedStatement.executeUpdate();
            }
            if (rowsInserted == projects.size()) {
                System.out.println(projects.size() + " new projects were inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = Database.getInstance().getConnection()) {
            String sql = "INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            List<ProjectWorkerData> projectWorkers = Arrays.asList(
                    new ProjectWorkerData(1, 1),
                    new ProjectWorkerData(1, 2),
                    new ProjectWorkerData(1, 3),
                    new ProjectWorkerData(2, 2),
                    new ProjectWorkerData(2, 3),
                    new ProjectWorkerData(2, 4),
                    new ProjectWorkerData(2, 5),
                    new ProjectWorkerData(3, 3),
                    new ProjectWorkerData(3, 4),
                    new ProjectWorkerData(3, 5),
                    new ProjectWorkerData(4, 4),
                    new ProjectWorkerData(4, 5),
                    new ProjectWorkerData(5, 5),
                    new ProjectWorkerData(6, 1),
                    new ProjectWorkerData(6, 2),
                    new ProjectWorkerData(7, 2),
                    new ProjectWorkerData(7, 3),
                    new ProjectWorkerData(7, 4),
                    new ProjectWorkerData(8, 3),
                    new ProjectWorkerData(8, 4),
                    new ProjectWorkerData(9, 4),
                    new ProjectWorkerData(9, 5),
                    new ProjectWorkerData(10, 5)
            );
            int rowsInserted = 0;
            for (ProjectWorkerData projectWorker : projectWorkers) {
                preparedStatement.setInt(1, projectWorker.getProjectId());
                preparedStatement.setInt(2, projectWorker.getWorkerId());

                rowsInserted += preparedStatement.executeUpdate();
            }
            if (rowsInserted == projectWorkers.size()) {
                System.out.println(projectWorkers.size() + " new project workers were inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}