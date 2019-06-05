package com.java.kids.Service.impl;

import com.java.kids.Service.DoctorService;
import com.java.kids.dao.DoctorMapper;
import com.java.kids.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现service类
 * 操作数据库
 */
@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public Doctor getDoctorByUsername(String username) {
        return doctorMapper.getDoctorByUsername(username);
    }

    @Override
    public int setDoctor(Doctor doctor) {
        if (doctor!=null){
            doctorMapper.setDoctor(doctor.dusername,doctor.dpassword,doctor.dName,doctor.dgender,doctor.dphone,doctor.dpositionTime);
            return 1;
        }
        return -1;
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        doctorMapper.updateDoctor(doctor);
    }

    @Override
    public List<Doctor> getDoctors() {
        return doctorMapper.getDoctors();
    }
}
