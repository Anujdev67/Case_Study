package dao;

import entity.*;
import exception.DatabaseException;
import exception.IncidentNumberNotFoundException;

import java.sql.Date;
import java.util.List;

public interface ICrimeAnalysisService {
    void createVictim(Victim victim) throws DatabaseException;
    void createSuspect(Suspect suspect) throws DatabaseException;
    void createIncident(Incident incident) throws DatabaseException;
    void createOfficer(Officer officer) throws DatabaseException;
    void createAgency(LawEnforcementAgency agency) throws DatabaseException;
    void createReport(Report report) throws DatabaseException;
    void createEvidence(Evidence evidence) throws DatabaseException;

    Victim getVictim(int victimID) throws IncidentNumberNotFoundException,DatabaseException;
    Suspect getSuspect(int suspectID) throws IncidentNumberNotFoundException, DatabaseException;
    Incident getIncident(int incidentID) throws IncidentNumberNotFoundException, DatabaseException;
    Officer getOfficer(int officerID) throws IncidentNumberNotFoundException, DatabaseException;
    LawEnforcementAgency getAgency(int agencyID) throws IncidentNumberNotFoundException,DatabaseException;
    Report getReport(int reportID) throws IncidentNumberNotFoundException,DatabaseException;
    Evidence getEvidence(int evidenceID) throws IncidentNumberNotFoundException,DatabaseException;

    List<Incident> getAllIncidents() throws DatabaseException;
    List<Victim> getAllVictims() throws DatabaseException;
    List<Suspect> getAllSuspects() throws DatabaseException;
    List<Officer> getAllOfficers() throws DatabaseException;
    List<LawEnforcementAgency> getAllAgencies() throws DatabaseException;
    List<Report> getAllReports() throws DatabaseException;
    List<Evidence> getAllEvidence() throws DatabaseException;

    void updateIncidentStatus(int incidentID, String status) throws DatabaseException;
    void updateReportStatus(int reportID, String status) throws DatabaseException;

    List<Incident> getIncidentsInDateRange(Date startDate, Date endDate) throws DatabaseException;
}
