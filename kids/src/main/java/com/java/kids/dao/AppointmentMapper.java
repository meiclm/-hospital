package com.java.kids.dao;

import com.java.kids.model.Appointment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppointmentMapper {
    //病患查找所有的预约的时间
    public List<Appointment> findPatientAllAppointment(String pusername);
    //医生查找所有被预约的时间
    public List<Appointment> findDoctorAllAppointment(String dusername);

    //医生查找当天的预约
    public List<Appointment> findCurrentAppointment(String dusername,String startTime,String endTime);
    //查找该病例是否有预约过
    public String findCaseAppointment(String cNo);
    //预约打疫苗 ----- 注解式传入多个参数
    public void appointmentVaccine(@Param("cNo") String cNo,
                                   @Param("aTime") String aTime,@Param("vNo") int vNo);
    //普通挂号
    public void appointment(@Param("cNo") String cNo,@Param("aTime") String aTime);
    //更改打疫苗的时间, ----- 注解式传入多个参数
    public void modifiedTime(@Param("aTime") String aTime,@Param("aId")int aId);

    //删除一项记录
    public void deleteAppointment(@Param("aid")int aid);
    //查询一项记录
    public Appointment findAppointment(@Param("aid")int aid);
}
