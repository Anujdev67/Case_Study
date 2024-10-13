package main;

import dao.CrimeAnalysisServiceImpl;
import dao.ICrimeAnalysisService;
import entity.*;
import exception.DatabaseException;
import exception.IncidentNumberNotFoundException;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class CrimeAnalysisApplication {

    public static void main(String[] args) throws DatabaseException, IncidentNumberNotFoundException {
        ICrimeAnalysisService service = new CrimeAnalysisServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCrime Analysis and Reporting System");
            System.out.println("1. Incident");
            System.out.println("2. Victim");
            System.out.println("3. Suspect");
            System.out.println("4. Officer");
            System.out.println("5. Evidence");
            System.out.println("6. Report");
            System.out.println("7. Law Enforcement Agency");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    int chk1=1;
                    while(chk1==1) {
                        System.out.println("Incident");
                        System.out.println("1. Add New Incident");
                        System.out.println("2. Update Incident Status");
                        System.out.println("3. Get A Particular Incident Details");
                        System.out.println("4. Get Incidents Within A Date Range");
                        System.out.println("5. View All Incidents");
                        System.out.println("6. Go Back");
                        System.out.println("0. Exit");

                        System.out.print("Enter your choice: ");
                        int ch1 = scanner.nextInt();
                        scanner.nextLine();
                        switch (ch1) {
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
                                scanner.nextLine();
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
                                scanner.nextLine();
                                try {
                                    service.createIncident(incident);
                                } catch (exception.DatabaseException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.println("Incident Added Successfully");

                                break;
                            case 2:
                                System.out.print("Enter incident ID to update status: ");
                                int incidentID = scanner.nextInt();
                                scanner.nextLine();  // Consume newline
                                System.out.print("Enter new status (Open, Closed, Under Investigation): ");
                                String newStatus = scanner.nextLine();
                                service.updateIncidentStatus(incidentID, newStatus);
                                System.out.println("Status Updated");

                                break;

                            case 3:
                                System.out.println("Enter Incident ID:");
                                int incID = scanner.nextInt();
                                scanner.nextLine();  // Consume newline
                                System.out.println("Incident Details:");
                                Incident inc = service.getIncident(incID);
                                System.out.println(inc);

                                break;

                            case 4:

                                System.out.println("Enter Start Date (yyyy-mm-dd):");
                                Date sDate = Date.valueOf(scanner.next());
                                System.out.println("Enter End Date (yyyy-mm-dd):");
                                Date eDate = Date.valueOf(scanner.next());

                                List<Incident> inci = service.getIncidentsInDateRange(sDate, eDate);
                                if (inci.isEmpty()) {
                                    System.out.println("No incidents found for the given date range.");
                                } else {
                                    for (Incident i: inci) {
                                        System.out.println(i);
                                    }
                                }

                                break;

                            case 5:

                                System.out.println("All Incidents:");
                                List<Incident> incidents = service.getAllIncidents();
                                for (Incident i : incidents) {
                                    System.out.println(i);
                                }
                                break;

                            case 6:
                                System.out.println("Returning to Home");
                                chk1=0;
                                break;
                            case 0:
                                System.out.println("Exiting the application...");
                                scanner.close();
                                return;
                            default:
                                System.out.println("Invalid choice. Please try again.");


                        }
                    }

                    break;


                case 2:
                    int chk2=1;
                    while(chk2 == 1){
                        System.out.println("Victim");
                        System.out.println("1. Add New Victim");
                        System.out.println("2. Get A Particular Victim Details");
                        System.out.println("3. View All Victims");
                        System.out.println("4. Go Back");
                        System.out.println("0. Exit");

                        int ch2 = scanner.nextInt();
                        scanner.nextLine();

                        switch (ch2){
                            case 1:
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
                                System.out.println("Victim Added Successfully");

                                break;

                            case 2:
                                System.out.println("Enter Victim ID:");
                                int vicID = scanner.nextInt();
                                scanner.nextLine();  // Consume newline
                                System.out.println("Victim Details:");
                                Victim vic = service.getVictim(vicID);
                                System.out.println(vic);
                                break;

                            case 3:
                                System.out.println("All Victims:");
                                List<Victim> victims = service.getAllVictims();
                                for (Victim v : victims) {
                                    System.out.println(v);
                                }
                                break;

                            case 4:
                                System.out.println("Returning to Home");
                                chk2=0;
                                break;
                            case 0:
                                System.out.println("Exiting the application...");
                                scanner.close();
                                return;
                            default:
                                System.out.println("Invalid choice. Please try again.");


                        }
                    }
                    break;

                case 3:
                    int chk3=1;
                    while(chk3 == 1) {
                        System.out.println("Suspect");
                        System.out.println("1. Add New Suspect");
                        System.out.println("2. Get A Particular Suspect Details");
                        System.out.println("3. View All Suspects");
                        System.out.println("4. Go Back");
                        System.out.println("0. Exit");

                        int ch3 = scanner.nextInt();
                        scanner.nextLine();

                        switch (ch3){
                            case 1:
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
                                System.out.println("Suspect Added Successfully");

                                break;

                            case 2:
                                System.out.println("Enter Suspect ID:");
                                int susID = scanner.nextInt();
                                scanner.nextLine();  // Consume newline
                                System.out.println("Suspect Details:");
                                Suspect sus = service.getSuspect(susID);
                                System.out.println(sus);

                                break;

                            case 3:
                                System.out.println("All Suspects:");
                                List<Suspect> suspectsList = service.getAllSuspects();
                                for (Suspect s : suspectsList) {
                                    System.out.println(s);
                                }
                                break;

                            case 4:
                                System.out.println("Returning to Home");
                                chk3=0;
                                break;
                            case 0:
                                System.out.println("Exiting the application...");
                                scanner.close();
                                return;
                            default:
                                System.out.println("Invalid choice. Please try again.");

                        }

                    }
                    break;


                case 4:
                    int chk4=1;
                    while(chk4==1){
                        System.out.println("Officer");
                        System.out.println("1. Add New Officer");
                        System.out.println("2. Get A Particular Officer Details");
                        System.out.println("3. View All Officers");
                        System.out.println("4. Go Back");
                        System.out.println("0. Exit");

                        int ch4 = scanner.nextInt();
                        scanner.nextLine();

                        switch (ch4) {
                            case 1:
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
                                System.out.println("Officer Added Successfully");

                                break;

                            case 2:
                                System.out.println("Enter Officer ID:");
                                int offID = scanner.nextInt();
                                scanner.nextLine();  // Consume newline
                                System.out.println("Officer Details:");
                                Officer off = service.getOfficer(offID);
                                System.out.println(off);

                                break;

                            case 3:
                                System.out.println("All Officers:");
                                List<Officer> officers = service.getAllOfficers();
                                for (Officer o : officers) {
                                    System.out.println(o);
                                }
                                break;

                            case 4:
                                System.out.println("Returning to Home");
                                chk4=0;
                                break;
                            case 0:
                                System.out.println("Exiting the application...");
                                scanner.close();
                                return;
                            default:
                                System.out.println("Invalid choice. Please try again.");

                        }

                    }
                    break;

                case 5:
                    int chk5=1;
                    while(chk5==1){
                        System.out.println("Evidence");
                        System.out.println("1. Add New Evidence");
                        System.out.println("2. Get A Particular Evidence Details");
                        System.out.println("3. View All Evidence");
                        System.out.println("4. Go Back");
                        System.out.println("0. Exit");

                        int ch5 = scanner.nextInt();
                        scanner.nextLine();

                        switch (ch5) {
                            case 1:
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
                                System.out.println("Evidence Added Successfully");

                                break;

                            case 2:
                                System.out.println("Enter Evidence ID:");
                                int eveID = scanner.nextInt();
                                scanner.nextLine();  // Consume newline
                                System.out.println("Evidence Details:");
                                Evidence eve = service.getEvidence(eveID);
                                System.out.println(eve);
                                break;

                            case 3:
                                System.out.println("All Evidence:");
                                List<Evidence> evidences = service.getAllEvidence();
                                for (Evidence e : evidences) {
                                    System.out.println(e);
                                }
                                break;
                            case 4:
                                System.out.println("Returning to Home");
                                chk5=0;
                                break;
                            case 0:
                                System.out.println("Exiting the application...");
                                scanner.close();
                                return;
                            default:
                                System.out.println("Invalid choice. Please try again.");

                        }
                    }
                    break;

                case 6:
                    int chk6=1;
                    while(chk6==1){
                        System.out.println("Report");
                        System.out.println("1. Add New Report");
                        System.out.println("2. Update Report Status");
                        System.out.println("3. Get A Particular Report Details");
                        System.out.println("4. View All Reports");
                        System.out.println("5. Go Back");
                        System.out.println("0. Exit");

                        int ch6 = scanner.nextInt();
                        scanner.nextLine();

                        switch(ch6) {
                            case 1:
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
                                System.out.println("Report Added Successfully");

                                break;

                            case 2:
                                System.out.print("Enter Report ID to update status: ");
                                int reportID = scanner.nextInt();
                                scanner.nextLine();  // Consume newline
                                System.out.print("Enter new status: ");
                                String newReportStatus = scanner.nextLine();
                                service.updateReportStatus(reportID, newReportStatus);
                                System.out.println("Status Updated");

                                break;

                            case 3:
                                System.out.println("Enter Report ID:");
                                int repID = scanner.nextInt();
                                scanner.nextLine();  // Consume newline
                                System.out.println("Suspect Details:");
                                Report rep = service.getReport(repID);
                                System.out.println(rep);

                                break;

                            case 4:
                                System.out.println("All Reports:");
                                List<Report> reports = service.getAllReports();
                                for (Report r : reports) {
                                    System.out.println(r);
                                }
                                break;

                            case 5:
                                System.out.println("Returning to Home");
                                chk6=0;
                                break;
                            case 0:
                                System.out.println("Exiting the application...");
                                scanner.close();
                                return;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }

                    }
                    break;

                case 7:
                    int chk7=1;
                    while(chk7==1){
                        System.out.println("Law Enforcement Agency");
                        System.out.println("1. Add New Law Enforcement Agency");
                        System.out.println("2. Get A Particular Agency Details");
                        System.out.println("3. View All Law Enforcement Agencies");
                        System.out.println("4. Go Back");
                        System.out.println("0. Exit");

                        int ch7 = scanner.nextInt();
                        scanner.nextLine();

                        switch (ch7) {
                            case 1:
                                System.out.println("Enter Law Enforcement Agencies details:");
                                LawEnforcementAgency agency = new LawEnforcementAgency();
                                System.out.print("AgencyName: ");
                                agency.setAgencyName(scanner.nextLine());
                                System.out.print("Jurisdiction: ");
                                agency.setJurisdiction(scanner.nextLine());
                                System.out.print("Contact Information: ");
                                agency.setPhoneNumber(scanner.nextLine());
                                System.out.print("Address: ");
                                agency.setAddress(scanner.nextLine());
                                scanner.nextLine();  // Consume newline
                                service.createAgency(agency);
                                System.out.println("Law Enforcement Agency Added Successfully");

                                break;
                            case 2:
                                System.out.println("Enter Agency ID:");
                                int agenID = scanner.nextInt();
                                scanner.nextLine();  // Consume newline
                                System.out.println("Agency Details:");
                                LawEnforcementAgency agen = service.getAgency(agenID);
                                System.out.println(agen);

                                break;

                            case 3:
                                System.out.println("All Law Enforcement Agencies:");

                                List<LawEnforcementAgency>  agencies = service.getAllAgencies();
                                for (LawEnforcementAgency a : agencies) {
                                    System.out.println(a);
                                }
                                break;
                            case 4:
                                System.out.println("Returning to Home");
                                chk7=0;
                                break;
                            case 0:
                                System.out.println("Exiting the application...");
                                scanner.close();
                                return;
                            default:
                                System.out.println("Invalid choice. Please try again.");

                        }

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
