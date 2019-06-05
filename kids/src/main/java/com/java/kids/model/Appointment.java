package com.java.kids.model;

public class Appointment {
    public int aid;
    public String aTime;
    public Case aCase;
    //疫苗
//    public Vaccine vaccine;
    public int vNo;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getaTime() {
        return aTime;
    }

    public void setaTime(String aTime) {
        this.aTime = aTime;
    }

    public Case getaCase() {
        return aCase;
    }

    public void setaCase(Case aCase) {
        this.aCase = aCase;
    }

//    public Vaccine getVaccine() {
//        return vaccine;
//    }
//
//    public void setVaccine(Vaccine vaccine) {
//        this.vaccine = vaccine;
//    }

    public int getvNo() {
        return vNo;
    }

    public void setvNo(int vNo) {
        this.vNo = vNo;
    }
}
