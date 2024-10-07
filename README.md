# Crime Analysis and Reporting System 

## Project Overview
The Crime Analysis and Reporting System  is a comprehensive platform designed to help law enforcement agencies manage and analyze crime data effectively. This project includes functionalities for crime reporting, victim and suspect management, evidence tracking, and report generation.

## Features
- Incident creation and status updates
- Victim and suspect data management
- Law enforcement agency and officer details
- Evidence tracking related to incidents
- Reports generation for incidents

## Technologies Used
- **Programming Language**: Java
- **Database**: MySQL

## Directory Structure
```text
src/
├── entity
│   └── Incident.java
│   └── Victim.java
│   └── Suspect.java
│   └── Officer.java
│   └── LawEnforcementAgency.java
│   └── Evidence.java
│   └── Report.java
├── dao
│   └── ICrimeAnalysisService.java
│   └── CrimeAnalysisServiceImpl.java
├── util
│   └── DBConnection.java
│   └── DBPropertyUtil.java
├── exception
│   └── IncidentNumberNotFoundException.java
├── main
│   └── CrimeAnalysisApplication.java
