package com.java.kids.Service.impl;

import com.java.kids.Service.AppointmentService;
import com.java.kids.dao.AppointmentMapper;
import com.java.kids.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public List<Appointment> findPatientAllAppointment(String pusername) {
        List<Appointment> appointments= appointmentMapper.findPatientAllAppointment(pusername);
        if (appointments.size()>0){
            return appointments;
        }
        return null;
    }

    @Override
    public List<Appointment> findDoctorAllAppointment(String dusername) {
        List<Appointment> appointments=appointmentMapper.findDoctorAllAppointment(dusername);
        if (appointments.size()>0){
            return appointments;
        }
        return null;
    }
    //医生查找当天的预约
    @Override
    public List<Appointment> findCurrentAppointment(String dusername,String startTime,String endTime) {
        SimpleDateFormat sdp=new SimpleDateFormat("yyyy-MM-dd");
        String current=sdp.format(new Date());
        String timeArr[];
        timeArr=(current+"").split("-");
        if (timeArr[1].startsWith("0")){
            timeArr[1]=timeArr[1].substring(1);
        }
        String myTime=timeArr[0]+"-"+timeArr[1]+"-"+timeArr[2];
//        if ()
        String start=myTime+" 08:00";
        String end=myTime+" 18:00";
        System.out.println("开始时间："+start+"  ,  结束时间："+end);
        return appointmentMapper.findCurrentAppointment(dusername,start,end);
//        return appointmentMapper.findCurrentAppointment(dusername);
    }

    @Override
    public String findCaseAppointment(String cNo) {
        String caseNO=appointmentMapper.findCaseAppointment(cNo);
        if (caseNO!=null){
            return caseNO;
        }
        return null;
    }

    @Override
    public void appointmentVaccine(String cNo, String aTime, int vNo) {
        appointmentMapper.appointmentVaccine(cNo,aTime,vNo);
    }

    @Override
    public void appointment(String cNo, String aTime) {
        appointmentMapper.appointment(cNo,aTime);
    }

    @Override
    public void modifiedTime(String aTime, int aId) {
        appointmentMapper.modifiedTime(aTime,aId);
    }

    //删除一项记录
    @Override
    public int deleteAppointment(int aid) {
        Appointment appointment=appointmentMapper.findAppointment(aid);
        if (appointment!=null){
            appointmentMapper.deleteAppointment(aid);
            return 1;
        }
        return -1;
    }

    @Override
    public Appointment findAppointment(int aid) {
        return appointmentMapper.findAppointment(aid);
    }
}
