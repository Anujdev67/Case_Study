package main;

import dao.CrimeAnalysisServiceImpl;
import dao.ICrimeAnalysisService;
import entity.*;

import java.util.List;
import java.util.Scanner;

public class CrimeAnalysisApplication {

    public static void main(String[] args) {
        ICrimeAnalysisService service = new CrimeAnalysisServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCrime Analysis and Reporting System (C.A.R.S.)");
            System.out.println("1. Add New Incident");
            System.out.println("2. Add New Victim");
            System.out.println("3. Add New Suspect");
            System.out.println("4. Add New Officer");
            System.out.println("5. Add New Evidence");
            System.out.println("6. Add New Report");
            System.out.println("7. View All Incidents");
            System.out.println("8. Update Incident Status");
            System.out.println("9. View All Victims");
            System.out.println("10. View All Suspects");
            System.out.println("11. View All Officers");
            System.out.println("12. View All Reports");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter incident details:");
                    Incident incident = new Incident();
                    System.out.print("Incident Type: ");
                    incident.setIncidentType(scanner.nextLine());
                    System.out.print("Incident Date (yyyy-mm-dd): ");
                    incident.setIncidentDate(scanner.nextLine());
                    System.out.print("Latitude: ");
                    incident.setLatitude(scanner.nextDouble());
                    System.out.print("Longitude: ");
                    incident.setLongitude(scanner.nextDouble());
                    scanner.nextLine();  // Consume newline
                    System.out.print("Description: ");
                    incident.setDescription(scanner.nextLine());
                    System.out.print("Status (Open, Closed, Under Investigation): ");
                    incident.setStatus(scanner.nextLine());
                    System.out.print("Victim ID: ");
                    incident.setVictimID(scanner.nextInt());
                    System.out.print("Suspect ID: ");
                    incident.setSuspectID(scanner.nextInt());
                    System.out.print("Agency ID: ");
                    incident.setAgencyID(scanner.nextInt());
                    scanner.nextLine();  // Consume newline
                    service.createIncident(incident);
                    break;

                case 2:
                    System.out.println("Enter victim details:");
                    Victim victim = new Victim();
                    System.out.print("First Name: ");
                    victim.setFirstName(scanner.nextLine());
                    System.out.print("Last Name: ");
                    victim.setLastName(scanner.nextLine());
                    System.out.print("Date of Birth (yyyy-mm-dd): ");
                    victim.setDateOfBirth(scanner.nextLine());
                    System.out.print("Gender (Male, Female, Other): ");
                    victim.setGender(scanner.nextLine());
                    System.out.print("Address: ");
                    victim.setAddress(scanner.nextLine());
                    System.out.print("Phone Number: ");
                    victim.setPhoneNumber(scanner.nextLine());
                    service.createVictim(victim);
                    break;

                case 3:
                    System.out.println("Enter suspect details:");
                    Suspect suspect = new Suspect();
                    System.out.print("First Name: ");
                    suspect.setFirstName(scanner.nextLine());
                    System.out.print("Last Name: ");
                    suspect.setLastName(scanner.nextLine());
                    System.out.print("Date of Birth (yyyy-mm-dd): ");
                    suspect.setDateOfBirth(scanner.nextLine());
                    System.out.print("Gender (Male, Female, Other): ");
                    suspect.setGender(scanner.nextLine());
                    System.out.print("Address: ");
                    suspect.setAddress(scanner.nextLine());
                    System.out.print("Phone Number: ");
                    suspect.setPhoneNumber(scanner.nextLine());
                    service.createSuspect(suspect);
                    break;

                case 4:
                    System.out.println("Enter officer details:");
                    Officer officer = new Officer();
                    System.out.print("First Name: ");
                    officer.setFirstName(scanner.nextLine());
                    System.out.print("Last Name: ");
                    officer.setLastName(scanner.nextLine());
                    System.out.print("Badge Number: ");
                    officer.setBadgeNumber(scanner.nextLine());
                    System.out.print("Officer Rank: ");
                    officer.setOfficerRank(scanner.nextLine());
                    System.out.print("Address: ");
                    officer.setAddress(scanner.nextLine());
                    System.out.print("Phone Number: ");
                    officer.setPhoneNumber(scanner.nextLine());
                    System.out.print("Agency ID: ");
                    officer.setAgencyID(scanner.nextInt());
                    scanner.nextLine();  // Consume newline
                    service.createOfficer(officer);
                    break;

                case 5:
                    System.out.println("Enter evidence details:");
                    Evidence evidence = new Evidence();
                    System.out.print("Description: ");
                    evidence.setDescription(scanner.nextLine());
                    System.out.print("Location Found: ");
                    evidence.setLocationFound(scanner.nextLine());
                    System.out.print("Incident ID: ");
                    evidence.setIncidentID(scanner.nextInt());
                    scanner.nextLine();  // Consume newline
                    service.createEvidence(evidence);
                    break;

                case 6:
                    System.out.println("Enter report details:");
                    Report report = new Report();
                    System.out.print("Report Date (yyyy-mm-dd): ");
                    report.setReportDate(scanner.nextLine());
                    System.out.print("Report Details: ");
                    report.setReportDetails(scanner.nextLine());
                    System.out.print("Status (Draft, Finalized): ");
                    report.setStatus(scanner.nextLine());
                    System.out.print("Incident ID: ");
                    report.setIncidentID(scanner.nextInt());
                    System.out.print("Reporting Officer ID: ");
                    report.setReportingOfficer(scanner.nextInt());
                    scanner.nextLine();  // Consume newline
                    service.createReport(report);
                    break;

                case 7:
                    System.out.println("All Incidents:");
                    List<Incident> incidents = service.getAllIncidents();
                    for (Incident i : incidents) {
                        System.out.println(i);
                    }
                    break;

                case 8:
                    System.out.print("Enter incident ID to update status: ");
                    int incidentID = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new status (Open, Closed, Under Investigation): ");
                    String newStatus = scanner.nextLine();
                    service.updateIncidentStatus(incidentID, newStatus);
                    break;

                case 9:
                    System.out.println("All Victims:");
                    List<Victim> victims = service.getAllVictims();
                    for (Victim v : victims) {
                        System.out.println(v);
                    }
                    break;

                case 10:
                    System.out.println("All Suspects:");
                    List<Suspect> suspectsList = service.getAllSuspects();
                    for (Suspect s : suspectsList) {
                        System.out.println(s);
                    }
                    break;

                case 11:
                    System.out.println("All Officers:");
                    List<Officer> officers = service.getAllOfficers();
                    for (Officer o : officers) {
                        System.out.println(o);
                    }
                    break;

                case 12:
                    System.out.println("All Reports:");
                    List<Report> reports = service.getAllReports();
                    for (Report r : reports) {
                        System.out.println(r);
                    }
                    break;

                case 0:
                    System.out.println("Exiting the application...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
