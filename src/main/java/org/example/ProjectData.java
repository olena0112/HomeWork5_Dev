package org.example;
class ProjectData {
    private final int clientId;
    private final String startDate;
    private final String finishDate;

    public ProjectData(int clientId, String startDate, String finishDate) {
        this.clientId = clientId;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public int getClientId() {
        return clientId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }
}
