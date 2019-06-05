package com.java.kids.controller;

import com.java.kids.Service.AppointmentService;
import com.java.kids.Service.CaseService;
import com.java.kids.model.Appointment;
import com.java.kids.model.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//预约打预防针
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private CaseService caseService;

    //预约普通看诊
    @PostMapping("/appointment/doctor")
    public int makeAppointmentToDoctor(@RequestParam("cNo")String cNo,@RequestParam("aTime") String aTime){
        Case aCase=caseService.getMyCases(cNo);
        if (aCase!=null){
            appointmentService.appointment(cNo,aTime);
            return 1;
        }
        return -1;//没有病例，先去建档
    }
    //预约打疫苗
    @PostMapping("/appointment/vaccine")
    public int makeAppointmentToVaccine(@RequestParam("cNo")String cNo,@RequestParam("aTime") String aTime,@RequestParam("vNo") int vNo){
        Case aCase=caseService.getMyCases(cNo);
        if (aCase!=null){
            if (aCase.lookTime!=""){//已经看过病了
                appointmentService.appointmentVaccine(cNo,aTime,vNo);
                return 1;
            }
        }
        return -1;
    }

    //更改预约时间
    @PostMapping("/modifyTime")
    public int modifyAppointment(@RequestParam("aTime") String aTime,int aId){
        Appointment appointment=appointmentService.findAppointment(aId);
        if (appointment!=null){
            appointmentService.modifiedTime(aTime,aId);
            return 1;
        }
        return -1;
    }

//    病人查找预约的所有预约时间
    @GetMapping("/patientAppointment")
    public List<Appointment> getAllAppointment(@RequestParam("pusername") String pusername){
        List<Appointment> appointments=appointmentService.findPatientAllAppointment(pusername);
        if (appointments!=null){
            return appointments;
        }
        return null;
    }

    //医生查看所有的被预约时间,大于当前系统的时间，看mybatis的语句 > now()
    @GetMapping("/doctorAppointmentTime")
    public List<Appointment> getALLDAppointment(@RequestParam("dusername") String dusername){
        List<Appointment> appointments=appointmentService.findDoctorAllAppointment(dusername);
        return appointments;
    }
    //查看当天的预约
    @GetMapping("/doctor/current")
    public List<Appointment> currentAppointment(@RequestParam("dusername") String dusername){
        List<Appointment> appointments=appointmentService.findCurrentAppointment(dusername,"","");
        if (appointments!=null){
            return appointments;
        }
        return null;
    }
    //删除该预约
    @PostMapping("/appointment/del")
    public int delThisAppointment(@RequestParam("aId") int aId){
        if (appointmentService.deleteAppointment(aId)==1){
            return 1;
        }
        return -1;
    }
}
