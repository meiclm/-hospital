package com.java.kids.Service;

import com.java.kids.model.Patient;

import java.util.List;

public interface PatientService {
    public Patient getPatientByUsername(String username);//登录
    public Patient getPatient(String username,String password);//登录
    public void insertPatient(Patient patient);//注册
    //修改个人信息
    public int updatePatient( String username,String pName,int gender,
                              String birthday,String parent,String phone);

    //微信登录
    public List<Patient> getChildren(String openId);
}
