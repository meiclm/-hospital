package com.java.kids.Service;

import com.java.kids.model.Doctor;

import java.util.List;

public interface DoctorService {
    //登陆
    public Doctor getDoctorByUsername(String username);
    //医生注册
    public int setDoctor(Doctor doctor);
    //更新自己的信息
    public void updateDoctor(Doctor doctor);
    //查出全部医生的信息
    public List<Doctor> getDoctors();
}
