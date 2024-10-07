package dao;

import entity.*;
import java.util.List;

public interface ICrimeAnalysisService {
    void createVictim(Victim victim);
    void createSuspect(Suspect suspect);
    void createIncident(Incident incident);
    void createOfficer(Officer officer);
    void createAgency(LawEnforcementAgency agency);
    void createReport(Report report);
    void createEvidence(Evidence evidence);

    Victim getVictim(int victimID);
    Suspect getSuspect(int suspectID);
    Incident getIncident(int incidentID);
    Officer getOfficer(int officerID);
    LawEnforcementAgency getAgency(int agencyID);
    Report getReport(int reportID);
    Evidence getEvidence(int evidenceID);

    List<Incident> getAllIncidents();
    List<Victim> getAllVictims();
    List<Suspect> getAllSuspects();
    List<Officer> getAllOfficers();
    List<LawEnforcementAgency> getAllAgencies();
    List<Report> getAllReports();
    List<Evidence> getAllEvidence();

    void updateIncidentStatus(int incidentID, String status);
    void updateReportStatus(int reportID, String status);
}
