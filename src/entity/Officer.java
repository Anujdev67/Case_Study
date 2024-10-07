package entity;

public class Officer {

    private int officerID ;
    private String firstName ;
    private String lastName ;
    private String badgeNumber ;
    private String officerRank ;
    private String address ;
    private String phoneNumber;
    private int agencyID ;

    public Officer() { }
    public Officer(int officerID, String firstName, String lastName, String badgeNumber, String officerRank, String address, String phoneNumber, int agencyID) {
        this.officerID = officerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.badgeNumber = badgeNumber;
        this.officerRank = officerRank;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.agencyID = agencyID;
    }

    public int getOfficerID() {
        return officerID;
    }

    public void setOfficerID(int officerID) {
        this.officerID = officerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getOfficerRank() {
        return officerRank;
    }

    public void setOfficerRank(String officerRank) {
        this.officerRank = officerRank;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(int agencyID) {
        this.agencyID = agencyID;
    }

    @Override
    public String toString() {
        return "Officer{" +
                "officerID=" + officerID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", badgeNumber='" + badgeNumber + '\'' +
                ", officerRank='" + officerRank + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", agencyID=" + agencyID +
                '}';
    }
}
