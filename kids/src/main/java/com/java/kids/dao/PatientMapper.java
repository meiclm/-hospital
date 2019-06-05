package com.java.kids.dao;

import com.java.kids.model.Patient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PatientMapper {
    public Patient getPatientByUsername(String username);//查看该用户是否存在
    public Patient getPatient(@Param("username") String username,@Param("password") String password);//登录
    public void insertPatient(Patient patient);//注册
    public void updatePatient(@Param("username") String username,@Param("pName")String pName,@Param("gender")int gender,
                              @Param("birthday")String birthday,@Param("parent")String parent,@Param("phone")String phone);
    //微信登录
    public List<Patient> getChildren(String openId);
}
