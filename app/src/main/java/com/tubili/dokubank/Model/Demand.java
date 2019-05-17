package com.tubili.dokubank.Model;

public class Demand {
    private String name;
    private String surname;
    //private String username;
    private String city;
    private String hospitalName;
    private String bloodGroup;
    private String tissueType;
    private String patientAge;


    public Demand(String name,String city, String surname, String hospitalName, String bloodGroup, String tissueType, String patientAge) {
        this.name = name;
        this.surname = surname;
        //this.username = username;
        this.city = city;
        this.hospitalName = hospitalName;
        this.bloodGroup = bloodGroup;
        this.tissueType = tissueType;
        this.patientAge = patientAge;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }
}
