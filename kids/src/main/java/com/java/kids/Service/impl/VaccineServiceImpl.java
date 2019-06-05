package com.java.kids.Service.impl;

import com.java.kids.Service.VaccineService;
import com.java.kids.dao.VaccineMapper;
import com.java.kids.model.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService {
    @Autowired
    private VaccineMapper vaccineMapper;
    @Override
    public List<Vaccine> findAllVaccine() {
        List<Vaccine> vaccines=vaccineMapper.findAllVaccine();
        if (vaccines!=null){
            return vaccines;
        }
        return null;
    }

    @Override
    public Vaccine getVaccine(int vid) {
        Vaccine vaccine=vaccineMapper.getVaccine(vid);
        if (vaccine!=null){
            return vaccine;
        }
        return null;
    }

    @Override
    public Vaccine findOneVaccine(String name) {
        return vaccineMapper.findOneVaccine(name);
    }

    @Override
    public int modifiedOneVaccine(Vaccine vaccine) {
        if (vaccineMapper.findOneVaccine(vaccine.name)!=null){
            vaccineMapper.modifiedOneVaccine(vaccine);
            return 1;
        }
        return -1;
    }

    //模糊查询
    @Override
    public List<Vaccine> findVaccine(String name) {
        return vaccineMapper.findVaccine(name);
    }

    @Override
    public int insertVaccine(Vaccine vaccine) {
        if (findOneVaccine(vaccine.name)==null){
            vaccineMapper.insertVaccine(vaccine);
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public int delVaccine(int id) {
        if (vaccineMapper.getVaccine(id)!=null){
            vaccineMapper.delVaccine(id);
            return 1;
        }
        return -1;
    }
}
