package com.java.kids.Service.impl;

import com.java.kids.Service.PatientService;
import com.java.kids.dao.PatientMapper;
import com.java.kids.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientMapper patientMapper;

    @Override
    public Patient getPatientByUsername(String username) {
        return patientMapper.getPatientByUsername(username);
    }

    @Override
    public Patient getPatient(String username,String password){//登录
        return patientMapper.getPatient(username,password);
    }
    @Override
    public void insertPatient(Patient patient) {
        patientMapper.insertPatient(patient);
    }

    @Override
    public int updatePatient(String username, String pName, int gender, String birthday, String parent, String phone) {
        patientMapper.updatePatient(username,pName,gender,birthday,parent,phone);
        return 1;
    }

    @Override
    public List<Patient> getChildren(String openId) {
        return patientMapper.getChildren(openId);
    }
}
