package com.tubili.dokubank.Model;

public class Demand {
    private String name;
    private String surname;
    private String hospitalName;
    private String bloodGroup;
    private String tissueType;
    private int patientAge;


    public Demand(String name, String surname, String hospitalName, String bloodGroup, String tissueType, int patientAge) {
        this.name = name;
        this.surname = surname;
        this.hospitalName = hospitalName;
        this.bloodGroup = bloodGroup;
        this.tissueType = tissueType;
        this.patientAge = patientAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getTissueType() {
        return tissueType;
    }

    public void setTissueType(String tissueType) {
        this.tissueType = tissueType;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }
}
