package entity;

public class Report {
    private int reportID;
    private String reportDate;
    private String reportDetails;
    private String status;
    private int incidentID;
    private int reportingOfficer;

    public Report() {}

    public Report(int reportID, String reportDate, String reportDetails, String status, int incidentID, int reportingOfficer) {
        this.reportID = reportID;
        this.reportDate = reportDate;
        this.reportDetails = reportDetails;
        this.status = status;
        this.incidentID = incidentID;
        this.reportingOfficer = reportingOfficer;
    }

    // Getters and Setters


    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public int getReportingOfficer() {
        return reportingOfficer;
    }

    public void setReportingOfficer(int reportingOfficer) {
        this.reportingOfficer = reportingOfficer;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportID=" + reportID +
                ", reportDate='" + reportDate + '\'' +
                ", reportDetails='" + reportDetails + '\'' +
                ", status='" + status + '\'' +
                ", incidentID=" + incidentID +
                ", reportingOfficer=" + reportingOfficer +
                '}';
    }
}
