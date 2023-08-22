package org.example;

class ProjectWorkerData {
    private final int projectId;
    private final int workerId;

    public ProjectWorkerData(int projectId, int workerId) {
        this.projectId = projectId;
        this.workerId = workerId;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getWorkerId() {
        return workerId;
    }
}
