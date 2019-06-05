package com.java.kids.dao;

import com.java.kids.model.Doctor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorMapper {
    //通过username查出一个医生，登陆
    public Doctor getDoctorByUsername(String dusername);
    //医生注册
    public void setDoctor(@Param("dusername")String dusername,@Param("dpassword")String dpassword,@Param("dName")String dName,
                          @Param("dgender")int dgender,@Param("dphone")String dphone,@Param("dpositionTime")String dpositionTime);
    //更新自己的信息
    public void updateDoctor(Doctor doctor);
    //查出全部医生的信息
    public List<Doctor> getDoctors();
}
