package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;


public class DatabasePopulateService {
    private  PreparedStatement insertSt1;
    private  PreparedStatement insertSt2;
    private  PreparedStatement insertSt3;
    private  PreparedStatement insertSt4;

    public DatabasePopulateService(Database database) throws SQLException {
        Connection conn=database.getConnection();
        insertSt1=conn.prepareStatement("INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES (?, ?, ?, ?)");
        insertSt2=conn.prepareStatement("INSERT INTO client (NAME) VALUES (?)");
        insertSt3=conn.prepareStatement("INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES (?, ?, ?)");
        insertSt4=conn.prepareStatement("INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES (?, ?)");
    }
    private  void insertWorkers() throws SQLException {
        List<WorkerData> workers = List.of(
                new WorkerData("John Doe", "1990-01-01", "Trainee", 800),
                new WorkerData("Jane Smith", "1985-05-15", "Junior", 1200),
                new WorkerData("Mike Johnson", "1982-09-30", "Middle", 3000),
                new WorkerData("Emily Davis", "1978-03-10", "Senior", 6000),
                new WorkerData("Robert Wilson", "1992-07-20", "Middle", 2500),
                new WorkerData("Sarah Brown", "1995-12-05", "Junior", 1500),
                new WorkerData("David Taylor", "1989-06-18", "Middle", 2800),
                new WorkerData("Laura Anderson", "1987-09-22", "Senior", 5500),
                new WorkerData("Mark Thompson", "1991-02-14", "Junior", 1400),
                new WorkerData("Amy Roberts", "1993-11-08", "Trainee", 900)
        );
            for (WorkerData worker : workers) {
                insertSt1.setString(1, worker.getName());
                insertSt1.setDate(2, java.sql.Date.valueOf(worker.getBirthday()));
                insertSt1.setString(3, worker.getLevel());
                insertSt1.setInt(4, worker.getSalary());

                int rowsInserted = insertSt1.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Worker inserted successfully: " + worker.getName());
                }
            }
        }
    private  void insertClients() throws SQLException {
        List<String> clientNames = List.of(
                "ABC Company",
                "XYZ Corporation",
                "123 Industries",
                "Tech Solutions",
                "Global Enterprises"
        );
            for (String name : clientNames) {
                insertSt2.setString(1, name);
                int rowsInserted = insertSt2.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Client inserted successfully: " + name);
                }
            }
    }
    private  void insertProjects() throws SQLException {
        List<ProjectData> projects = List.of(
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
            for (ProjectData project : projects) {
                insertSt3.setInt(1, project.getClientId());
                insertSt3.setDate(2, java.sql.Date.valueOf(project.getStartDate()));
                insertSt3.setDate(3, java.sql.Date.valueOf(project.getFinishDate()));
                int rowsInserted = insertSt3.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Project inserted successfully: Client ID = " + project.getClientId() + ", Start Date = " + project.getStartDate() + ", Finish Date = " + project.getFinishDate());
                }
            }
    }
    private  void insertProjectWorkers() throws SQLException {
        List<ProjectWorkerData> projectWorkers = List.of(
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
            for (ProjectWorkerData projectWorker : projectWorkers) {
                    insertSt4.setInt(1, projectWorker.getProjectId());
                    insertSt4.setInt(2, projectWorker.getWorkerId());
                int rowsInserted = insertSt4.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Project Worker inserted successfully: Project ID = " + projectWorker.getProjectId() + ", Worker ID = " + projectWorker.getWorkerId());
                }
            }
        }
    public static void main(String[] args) throws SQLException {
        Database database = Database.getInstance();
        DatabasePopulateService populateService = new DatabasePopulateService(database);
        populateService.insertWorkers();
        populateService.insertClients();
        populateService.insertProjects();
        populateService.insertProjectWorkers();
    }

}