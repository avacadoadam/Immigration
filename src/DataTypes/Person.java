package DataTypes;

import DataStructure.ImmigrationQueue;

import java.time.LocalDate;

public class Person {

    private String fname;
    private String lname;
    private LocalDate dateOfArrival;
    private String passportNum;
    private Priority priorityLevel;
    private int ID;

    public Person(String fname, String lname, LocalDate dateOfArrival, String passportNum, Priority priorityLevel) {
        this.fname = fname;
        this.lname = lname;
        this.dateOfArrival = dateOfArrival;
        this.passportNum = passportNum;
        this.priorityLevel = priorityLevel;
        this.ID = ImmigrationQueue.getNextID();
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public LocalDate getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(LocalDate dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public Priority getPriorityLevel() {
        return priorityLevel;
    }

    public int getID() {
        return ID;
    }

}
