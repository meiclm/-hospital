package com.java.kids.Service;

import com.java.kids.model.Vaccine;

import java.util.List;

public interface VaccineService {
    public List<Vaccine> findAllVaccine();
    public Vaccine findOneVaccine(String name);
    public Vaccine getVaccine(int vid);
    public List<Vaccine> findVaccine(String name);
    public int modifiedOneVaccine(Vaccine vaccine);
    public int insertVaccine(Vaccine vaccine);
    public int delVaccine(int id);
}
