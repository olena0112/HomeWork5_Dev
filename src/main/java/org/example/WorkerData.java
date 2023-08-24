package org.example;

import java.time.LocalDate;

public class WorkerData {

        private final int salary;
        private final String name;
        private final String level;
        private final String birthday;

    public WorkerData( String name, String birthday,String level,int salary) {
        this.salary = salary;
        this.name = name;
        this.level = level;
        this.birthday = birthday;
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getBirthday() {
        return birthday;
    }
}

