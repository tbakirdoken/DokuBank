package com.tubili.dokubank.Model;

public class Demand {
    private String name;
    private String surname;
    private String userId;
    private String phone;
    private String city;
    private String hospitalName;
    private String bloodGroup;


    private String demandId;
    @Override
    public String toString() {
        return "Demand{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", tissueType='" + tissueType + '\'' +
                ", patientAge='" + patientAge + '\'' +
                ", phone='" + phone + '\''+
                '}';
    }

    private String tissueType;
    private String patientAge;

    public Demand(){}

    public Demand(String userId, String name,String city, String surname, String phone, String hospitalName, String bloodGroup, String tissueType, String patientAge) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.city = city;
        this.hospitalName = hospitalName;
        this.bloodGroup = bloodGroup;
        this.tissueType = tissueType;
        this.patientAge = patientAge;
        this.demandId = "";

    }
    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
