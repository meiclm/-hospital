package com.java.kids.dao;

import com.java.kids.model.Vaccine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VaccineMapper {
    public List<Vaccine> findAllVaccine();
    public Vaccine findOneVaccine(@Param("vName") String vName);
    public Vaccine getVaccine(@Param("vid") int vid);
    public List<Vaccine> findVaccine(@Param("vName") String vName);
    public void modifiedOneVaccine(Vaccine vaccine);
    public void insertVaccine(Vaccine vaccine);
    public void delVaccine(int id);
}
