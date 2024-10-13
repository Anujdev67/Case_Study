package dao;

import entity.*;
import exception.DatabaseException;
import exception.IncidentNumberNotFoundException;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService {
    private Connection connection;

    public CrimeAnalysisServiceImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void createVictim(Victim victim) throws  DatabaseException {
        String sql = "INSERT INTO Victims (FirstName, LastName, DateOfBirth, Gender, Address, PhoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, victim.getFirstName());
            ps.setString(2, victim.getLastName());
            ps.setString(3, victim.getDateOfBirth());
            ps.setString(4, victim.getGender());
            ps.setString(5, victim.getAddress());
            ps.setString(6, victim.getPhoneNumber());
            ps.executeUpdate();
        }   catch (SQLException e) {
                throw new DatabaseException("Error inserting victim into database", e);
            }
    }

    @Override
    public void createSuspect(Suspect suspect) throws DatabaseException {
        String sql = "INSERT INTO Suspects (FirstName, LastName, DateOfBirth, Gender, Address, PhoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, suspect.getFirstName());
            ps.setString(2, suspect.getLastName());
            ps.setString(3, suspect.getDateOfBirth());
            ps.setString(4, suspect.getGender());
            ps.setString(5, suspect.getAddress());
            ps.setString(6, suspect.getPhoneNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error inserting suspect into database", e);
        }
    }

    @Override
    public void createIncident(Incident incident) throws DatabaseException {
        String sql = "INSERT INTO Incidents (IncidentType, IncidentDate, Latitude, Longitude, Description, Status, VictimID, SuspectID, AgencyID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, incident.getIncidentType());
            ps.setString(2, incident.getIncidentDate());
            ps.setDouble(3, incident.getLatitude());
            ps.setDouble(4, incident.getLongitude());
            ps.setString(5, incident.getDescription());
            ps.setString(6, incident.getStatus());
            ps.setInt(7, incident.getVictimID());
            ps.setInt(8, incident.getSuspectID());
            ps.setInt(9, incident.getAgencyID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error inserting incident into database", e);
        }
    }

    @Override
    public void createOfficer(Officer officer) throws DatabaseException {
        String sql = "INSERT INTO Officers (FirstName, LastName, BadgeNumber, OfficerRank, Address, PhoneNumber, AgencyID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, officer.getFirstName());
            ps.setString(2, officer.getLastName());
            ps.setString(3, officer.getBadgeNumber());
            ps.setString(4, officer.getOfficerRank());
            ps.setString(5, officer.getAddress());
            ps.setString(6, officer.getPhoneNumber());
            ps.setInt(7, officer.getAgencyID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error inserting officer into database", e);
        }
    }

    @Override
    public void createAgency(LawEnforcementAgency agency) throws DatabaseException {
        String sql = "INSERT INTO LawEnforcementAgencies (AgencyName, Jurisdiction, Address, PhoneNumber) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, agency.getAgencyName());
            ps.setString(2, agency.getJurisdiction());
            ps.setString(3, agency.getAddress());
            ps.setString(4, agency.getPhoneNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error inserting agency into database", e);
        }
    }

    @Override
    public void createReport(Report report) throws DatabaseException {
        String sql = "INSERT INTO Reports (ReportDate, ReportDetails, Status, IncidentID, ReportingOfficer) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, report.getReportDate());
            ps.setString(2, report.getReportDetails());
            ps.setString(3, report.getStatus());
            ps.setInt(4, report.getIncidentID());
            ps.setInt(5, report.getReportingOfficer());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error inserting report into database", e);
        }

    }

    @Override
    public void createEvidence(Evidence evidence) throws DatabaseException {
        String sql = "INSERT INTO Evidence (Description, LocationFound, IncidentID) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, evidence.getDescription());
            ps.setString(2, evidence.getLocationFound());
            ps.setInt(3, evidence.getIncidentID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error inserting evidence into database", e);
        }
    }

    @Override
    public Victim getVictim(int victimID) throws IncidentNumberNotFoundException, DatabaseException {
        Victim victim = null;
        String sql = "SELECT * FROM Victims WHERE VictimID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, victimID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                victim = new Victim();
                victim.setVictimID(rs.getInt("VictimID"));
                victim.setFirstName(rs.getString("FirstName"));
                victim.setLastName(rs.getString("LastName"));
                victim.setDateOfBirth(rs.getString("DateOfBirth"));
                victim.setGender(rs.getString("Gender"));
                victim.setAddress(rs.getString("Address"));
                victim.setPhoneNumber(rs.getString("PhoneNumber"));
            }else {
                throw new IncidentNumberNotFoundException("Victim with ID " + victimID + " not found.");
            }
        } catch (SQLException | IncidentNumberNotFoundException e) {
            throw new DatabaseException("Error retrieving victim from database", e);
        }
        return victim;
    }

    @Override
    public Suspect getSuspect(int suspectID) throws IncidentNumberNotFoundException, DatabaseException {
        Suspect suspect = null;
        String sql = "SELECT * FROM Suspects WHERE SuspectID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, suspectID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                suspect = new Suspect();
                suspect.setSuspectID(rs.getInt("SuspectID"));
                suspect.setFirstName(rs.getString("FirstName"));
                suspect.setLastName(rs.getString("LastName"));
                suspect.setDateOfBirth(rs.getString("DateOfBirth"));
                suspect.setGender(rs.getString("Gender"));
                suspect.setAddress(rs.getString("Address"));
                suspect.setPhoneNumber(rs.getString("PhoneNumber"));
            }else {
                throw new IncidentNumberNotFoundException("Suspect with ID " + suspectID + " not found.");
            }
        } catch (SQLException | IncidentNumberNotFoundException e) {
            throw new DatabaseException("Error retrieving suspect from database", e);
        }

        return suspect;
    }

    @Override
    public Incident getIncident(int incidentID) throws IncidentNumberNotFoundException,DatabaseException {
        Incident incident = null;
        String sql = "SELECT * FROM Incidents WHERE IncidentID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, incidentID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                incident = new Incident();
                incident.setIncidentID(rs.getInt("IncidentID"));
                incident.setIncidentType(rs.getString("IncidentType"));
                incident.setIncidentDate(rs.getString("IncidentDate"));
                incident.setLatitude(rs.getDouble("Latitude"));
                incident.setLongitude(rs.getDouble("Longitude"));
                incident.setDescription(rs.getString("Description"));
                incident.setStatus(rs.getString("Status"));
                incident.setVictimID(rs.getInt("VictimID"));
                incident.setSuspectID(rs.getInt("SuspectID"));
                incident.setAgencyID(rs.getInt("AgencyID"));
            }else {
                throw new IncidentNumberNotFoundException("Incident with ID " + incidentID + " not found.");
            }
        } catch (SQLException | IncidentNumberNotFoundException e) {
            throw new DatabaseException("Error retrieving incident from database", e);
        }
        return incident;
    }

    @Override
    public Officer getOfficer(int officerID) throws IncidentNumberNotFoundException,DatabaseException {
        Officer officer = null;
        String sql = "SELECT * FROM Officers WHERE OfficerID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, officerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                officer = new Officer();
                officer.setOfficerID(rs.getInt("OfficerID"));
                officer.setFirstName(rs.getString("FirstName"));
                officer.setLastName(rs.getString("LastName"));
                officer.setBadgeNumber(rs.getString("BadgeNumber"));
                officer.setOfficerRank(rs.getString("OfficerRank"));
                officer.setAddress(rs.getString("Address"));
                officer.setPhoneNumber(rs.getString("PhoneNumber"));
                officer.setAgencyID(rs.getInt("AgencyID"));
            }else {
                throw new IncidentNumberNotFoundException("Officer with ID " + officerID + " not found.");
            }
        } catch (SQLException | IncidentNumberNotFoundException e) {
            throw new DatabaseException("Error retrieving incident from database", e);
        }
        return officer;
    }

    @Override
    public LawEnforcementAgency getAgency(int agencyID) throws IncidentNumberNotFoundException,DatabaseException {
        LawEnforcementAgency agency = null;
        String sql = "SELECT * FROM LawEnforcementAgencies WHERE AgencyID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, agencyID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                agency = new LawEnforcementAgency();
                agency.setAgencyID(rs.getInt("AgencyID"));
                agency.setAgencyName(rs.getString("AgencyName"));
                agency.setJurisdiction(rs.getString("Jurisdiction"));
                agency.setAddress(rs.getString("Address"));
                agency.setPhoneNumber(rs.getString("PhoneNumber"));
            }else {
                throw new IncidentNumberNotFoundException("Agency with ID " + agencyID + " not found.");
            }
        } catch (SQLException | IncidentNumberNotFoundException e) {
            throw new DatabaseException("Error retrieving incident from database", e);
        }
        return agency;
    }

    @Override
    public Report getReport(int reportID) throws IncidentNumberNotFoundException,DatabaseException {
        Report report = null;
        String sql = "SELECT * FROM Reports WHERE ReportID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, reportID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                report = new Report();
                report.setReportID(rs.getInt("ReportID"));
                report.setReportDate(rs.getString("ReportDate"));
                report.setReportDetails(rs.getString("ReportDetails"));
                report.setStatus(rs.getString("Status"));
                report.setIncidentID(rs.getInt("IncidentID"));
                report.setReportingOfficer(rs.getInt("ReportingOfficer"));
            }else {
                throw new IncidentNumberNotFoundException("Report with ID " + reportID + " not found.");
            }
        } catch (SQLException | IncidentNumberNotFoundException e) {
            throw new DatabaseException("Error retrieving incident from database", e);
        }
        return report;
    }

    @Override
    public Evidence getEvidence(int evidenceID) throws IncidentNumberNotFoundException, DatabaseException {
        Evidence evidence = null;
        String sql = "SELECT * FROM Evidence WHERE EvidenceID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, evidenceID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                evidence = new Evidence();
                evidence.setEvidenceID(rs.getInt("EvidenceID"));
                evidence.setDescription(rs.getString("Description"));
                evidence.setLocationFound(rs.getString("LocationFound"));
                evidence.setIncidentID(rs.getInt("IncidentID"));
            }else {
                throw new IncidentNumberNotFoundException("Evidence with ID " + evidenceID + " not found.");
            }
        } catch (SQLException | IncidentNumberNotFoundException e) {
            throw new DatabaseException("Error retrieving incident from database", e);
        }
        return evidence;
    }

    @Override
    public List<Incident> getAllIncidents() throws DatabaseException {
        List<Incident> incidents = new ArrayList<>();
        String sql = "SELECT * FROM Incidents";
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Incident incident = new Incident();
                incident.setIncidentID(rs.getInt("IncidentID"));
                incident.setIncidentType(rs.getString("IncidentType"));
                incident.setIncidentDate(rs.getString("IncidentDate"));
                incident.setLatitude(rs.getDouble("Latitude"));
                incident.setLongitude(rs.getDouble("Longitude"));
                incident.setDescription(rs.getString("Description"));
                incident.setStatus(rs.getString("Status"));
                incident.setVictimID(rs.getInt("VictimID"));
                incident.setSuspectID(rs.getInt("SuspectID"));
                incident.setAgencyID(rs.getInt("AgencyID"));
                incidents.add(incident);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch all incidents", e);
        }
        return incidents;
    }

    @Override
    public List<Victim> getAllVictims() throws DatabaseException {
        List<Victim> victims = new ArrayList<>();
        String sql = "SELECT * FROM Victims";
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Victim victim = new Victim();
                victim.setVictimID(rs.getInt("VictimID"));
                victim.setFirstName(rs.getString("FirstName"));
                victim.setLastName(rs.getString("LastName"));
                victim.setDateOfBirth(rs.getString("DateOfBirth"));
                victim.setGender(rs.getString("Gender"));
                victim.setAddress(rs.getString("Address"));
                victim.setPhoneNumber(rs.getString("PhoneNumber"));
                victims.add(victim);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch all victims", e);
        }
        return victims;
    }

    @Override
    public List<Suspect> getAllSuspects() throws DatabaseException {
        List<Suspect> suspects = new ArrayList<>();
        String sql = "SELECT * FROM Suspects";
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Suspect suspect = new Suspect();
                suspect.setSuspectID(rs.getInt("SuspectID"));
                suspect.setFirstName(rs.getString("FirstName"));
                suspect.setLastName(rs.getString("LastName"));
                suspect.setDateOfBirth(rs.getString("DateOfBirth"));
                suspect.setGender(rs.getString("Gender"));
                suspect.setAddress(rs.getString("Address"));
                suspect.setPhoneNumber(rs.getString("PhoneNumber"));
                suspects.add(suspect);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch all suspects", e);
        }
        return suspects;
    }

    @Override
    public List<Officer> getAllOfficers() throws DatabaseException {
        List<Officer> officers = new ArrayList<>();
        String sql = "SELECT * FROM Officers";
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Officer officer = new Officer();
                officer.setOfficerID(rs.getInt("OfficerID"));
                officer.setFirstName(rs.getString("FirstName"));
                officer.setLastName(rs.getString("LastName"));
                officer.setBadgeNumber(rs.getString("BadgeNumber"));
                officer.setOfficerRank(rs.getString("OfficerRank"));
                officer.setAddress(rs.getString("Address"));
                officer.setPhoneNumber(rs.getString("PhoneNumber"));
                officer.setAgencyID(rs.getInt("AgencyID"));
                officers.add(officer);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch all officers", e);
        }
        return officers;
    }

    @Override
    public List<LawEnforcementAgency> getAllAgencies() throws DatabaseException {
        List<LawEnforcementAgency> agencies = new ArrayList<>();
        String sql = "SELECT * FROM LawEnforcementAgencies";
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                LawEnforcementAgency agency = new LawEnforcementAgency();
                agency.setAgencyID(rs.getInt("AgencyID"));
                agency.setAgencyName(rs.getString("AgencyName"));
                agency.setJurisdiction(rs.getString("Jurisdiction"));
                agency.setAddress(rs.getString("Address"));
                agency.setPhoneNumber(rs.getString("PhoneNumber"));
                agencies.add(agency);
            }
        }catch (SQLException e) {
            throw new DatabaseException("Failed to fetch all agencies", e);
        }
        return agencies;
    }

    @Override
    public List<Report> getAllReports() throws DatabaseException {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM Reports";
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Report report = new Report();
                report.setReportID(rs.getInt("ReportID"));
                report.setReportDate(rs.getString("ReportDate"));
                report.setReportDetails(rs.getString("ReportDetails"));
                report.setStatus(rs.getString("Status"));
                report.setIncidentID(rs.getInt("IncidentID"));
                report.setReportingOfficer(rs.getInt("ReportingOfficer"));
                reports.add(report);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch all reports", e);
        }
        return reports;
    }

    @Override
    public List<Evidence> getAllEvidence() throws DatabaseException {
        List<Evidence> evidenceList = new ArrayList<>();
        String sql = "SELECT * FROM Evidence";
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Evidence evidence = new Evidence();
                evidence.setEvidenceID(rs.getInt("EvidenceID"));
                evidence.setDescription(rs.getString("Description"));
                evidence.setLocationFound(rs.getString("LocationFound"));
                evidence.setIncidentID(rs.getInt("IncidentID"));
                evidenceList.add(evidence);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch all evidence", e);
        }

        return evidenceList;
    }

    @Override
    public void updateIncidentStatus(int incidentID, String status) throws DatabaseException {
        String sql = "UPDATE Incidents SET Status = ? WHERE IncidentID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, incidentID);
            ps.executeUpdate();
        }  catch (SQLException e) {
            throw new DatabaseException("Failed to update incident status", e);
        }
    }

    @Override
    public void updateReportStatus(int reportID, String status) throws DatabaseException {
        String sql = "UPDATE Reports SET Status = ? WHERE ReportID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, reportID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update report status", e);
        }
    }


    @Override
    public List<Incident> getIncidentsInDateRange(Date startDate, Date endDate) throws DatabaseException {
        List<Incident> incidents = new ArrayList<>();
        String query = "SELECT * FROM Incidents WHERE IncidentDate BETWEEN ? AND ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setDate(1, startDate);
            ps.setDate(2, endDate);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Incident incident = new Incident();
                incident.setIncidentID(rs.getInt("IncidentID"));
                incident.setIncidentType(rs.getString("IncidentType"));
                incident.setIncidentDate(rs.getString("IncidentDate"));
                incident.setLatitude(rs.getDouble("Latitude"));
                incident.setLongitude(rs.getDouble("Longitude"));
                incident.setDescription(rs.getString("Description"));
                incident.setStatus(rs.getString("Status"));
                incident.setVictimID(rs.getInt("VictimID"));
                incident.setSuspectID(rs.getInt("SuspectID"));
                incident.setAgencyID(rs.getInt("AgencyID"));

                incidents.add(incident);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch incidents in the specified date range", e);
        }
        return incidents;
    }
}

