package test;

import dao.CrimeAnalysisServiceImpl;
import entity.Evidence;
import entity.Incident;
import entity.Victim;
import exception.DatabaseException;
import exception.IncidentNumberNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CrimeAnalysisServiceImplTest {
    private CrimeAnalysisServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new CrimeAnalysisServiceImpl();
    }

    @Test
    public void testCreateIncident() throws DatabaseException, IncidentNumberNotFoundException {
        Incident incident = new Incident();
        incident.setIncidentType("Robbery");
        incident.setIncidentDate("2024-10-13");
        incident.setLatitude(40.7128);
        incident.setLongitude(-74.0060);
        incident.setDescription("Robbery at downtown.");
        incident.setStatus("Open");
        incident.setVictimID(7);
        incident.setSuspectID(1);
        incident.setAgencyID(1);

        service.createIncident(incident);


        Incident createdIncident = service.getIncident(30);

        assertNotNull(createdIncident);
        assertEquals(incident.getIncidentType(), createdIncident.getIncidentType());
        assertEquals(incident.getIncidentDate(), createdIncident.getIncidentDate());
        assertEquals(incident.getDescription(), createdIncident.getDescription());
    }

// valid
    @Test
    public void testUpdateIncidentStatus() throws DatabaseException, IncidentNumberNotFoundException {
        int incidentID = 20;
        String newStatus = "Under Investigation";

        service.updateIncidentStatus(incidentID, newStatus);

        Incident updatedIncident = service.getIncident(incidentID);
        assertEquals(newStatus, updatedIncident.getStatus());
    }
    // invalid

    @Test
    public void testUpdateIncidentStatusWithInvalidID() throws DatabaseException, IncidentNumberNotFoundException {

        int invalidID = -1;
        String newStatus = "Resolved";
        service.updateIncidentStatus(invalidID, newStatus);

        Incident updatedIncident = service.getIncident(invalidID);
        assertNull(updatedIncident);
    }

    @Test
    public void testGetIncidentsInDateRange() throws DatabaseException {
        service = new CrimeAnalysisServiceImpl();
        Date startDate = Date.valueOf("2024-01-01");
        Date endDate = Date.valueOf("2024-10-10");

        List<Incident> incidents = service.getIncidentsInDateRange(startDate, endDate);

        assertNotNull(incidents);
        assertFalse(incidents.isEmpty());

        for (Incident incident : incidents) {
            Date incidentDate = Date.valueOf(incident.getIncidentDate());
            assertTrue(incidentDate.compareTo(startDate) >= 0);
            assertTrue(incidentDate.compareTo(endDate) <= 0);
        }
    }

    @Test
    public void testCreateVictim() throws DatabaseException, IncidentNumberNotFoundException {

        service = new CrimeAnalysisServiceImpl();

        Victim victim = new Victim();
        victim.setFirstName("John");
        victim.setLastName("Doe");
        victim.setDateOfBirth("1990-05-15");
        victim.setGender("Male");
        victim.setAddress("123 Main St");
        victim.setPhoneNumber("555-1234");

        service.createVictim(victim);

        Victim createdVictim = service.getVictim(7);

        assertNotNull(createdVictim);
        assertEquals("John", createdVictim.getFirstName());
        assertEquals("Doe", createdVictim.getLastName());
    }


    @Test
    public void testGetAllIncidents() throws DatabaseException {
        service = new CrimeAnalysisServiceImpl();

        List<Incident> incidents = service.getAllIncidents();

        assertNotNull(incidents);
        assertFalse(incidents.isEmpty());

        for (Incident incident : incidents) {
            assertNotNull(incident.getIncidentID());
            assertNotNull(incident.getIncidentType());
            assertNotNull(incident.getIncidentDate());
        }
    }

    @Test
    public void testCreateIncidentWithNullValues() {
        service = new CrimeAnalysisServiceImpl();

        Incident incident = new Incident();
        incident.setIncidentType(null);  // Null type should trigger validation error

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.createIncident(incident);
        });

        String expectedMessage = "Incident type cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }





}
