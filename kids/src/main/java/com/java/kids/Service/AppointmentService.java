package com.java.kids.Service;

import com.java.kids.model.Appointment;

import java.util.List;

public interface AppointmentService {
    //查找所有的预约疫苗的时间
    public List<Appointment> findPatientAllAppointment(String pusername);
    public List<Appointment> findDoctorAllAppointment(String dusername);

    //医生查找当天的预约
    public List<Appointment> findCurrentAppointment(String dusername,String startTime,String endTime);

    public String findCaseAppointment(String cNo);
    //预约打疫苗
    public void appointmentVaccine(String cNo,String aTime,int vNo);
    //普通挂号
    public void appointment(String cNo,String aTime);
    //更改打疫苗的时间
    public void modifiedTime(String aTime,int aId);
    //删除一项记录
    public int deleteAppointment(int aid);
    //查询一项记录
    public Appointment findAppointment(int aid);
}
