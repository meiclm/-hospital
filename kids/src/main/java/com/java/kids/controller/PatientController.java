package com.java.kids.controller;

import com.java.kids.Service.PatientService;
import com.java.kids.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    //登录验证
    @GetMapping("/patient/login")
    public Patient login(@RequestParam("username") String username,@RequestParam("password") String password){
        Patient patient=patientService.getPatient(username,password);
        if (patient!=null){
            return patient;
        }
        return null;
    }
    //验证该用户是否存在
    @GetMapping("/patient/exist")
    public int isExist(@RequestParam("username") String username){
        if (patientService.getPatientByUsername(username)==null){
            return 1;
        }
        return -1;
    }
    //注册
    @PostMapping("/patient/register")
    public int register(@RequestParam("username") String username,@RequestParam("password") String password,
                        @RequestParam("pName")String pName,@RequestParam("gender")int pgender,
                        @RequestParam("birthday")String pbirthday,@RequestParam("phone")String pphone,
                        @RequestParam("parent")String pparent){
        Patient patient=new Patient();
        patient.MyPatient(username,password,pName,pbirthday,pgender, pphone,pparent);
        patientService.insertPatient(patient);
        return 1;
    }
    //找到一个病患的信息
    @GetMapping("/patient")
    public Patient getPatient(@RequestParam("username") String username){
        Patient patient=patientService.getPatientByUsername(username);
        if (patient!=null){
            return patient;
        }
        return null;
    }

    //修改用户信息
    @PostMapping("/patient/update")
    public Patient updatePatient(@RequestParam("username") String username,@RequestParam("pName") String pName,
                             @RequestParam("gender") int gender,@RequestParam("birthday") String birthday,
                             @RequestParam("parent")String parent,@RequestParam("phone") String phone){
        if (username!=""){
            if (patientService.updatePatient(username,pName,gender,birthday,parent,phone)==1){
                return patientService.getPatientByUsername(username);
            }
        }
        return null;
    }
    //微信登录
    @GetMapping("/wxLogin")
    public List<Patient> getMyChildren(@RequestParam("openid") String openId){
        if (openId!=null||openId!=""){
            List<Patient> patientList=patientService.getChildren(openId);
            if (patientList.size()>0){
                return patientList;
            }
        }
        return null;
    }
}
