package com.java.kids.controller;

import com.java.kids.Service.DoctorService;
import com.java.kids.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.*;
import java.util.List;

@RestController//返回数据，不返回页面
@CrossOrigin//解决跨域问题
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    //登陆请求,找到该对象就返回该对象，找不到就会返回null,即doctor=null;
    @GetMapping("/doctor/login")
    public int login(@RequestParam("username") String username,@RequestParam("password")String password){
        Doctor doctor=doctorService.getDoctorByUsername(username);
        if(doctor!=null){
             if (password.equals(doctor.getDpassword())){
                 if(username.equals("admin")){
                     return 9;//后台管理账号
                 }
                 return 1;//普通用户
             }
             return 0;//密码错误
         }
         return -1;//不存在该用户

    }
    //注册请求
    @PostMapping("/doctor/register")
    public int register(@RequestParam("username")String username,@RequestParam("password") String password,
                        @RequestParam("gender")int gender,@RequestParam("dName") String dName,
                        @RequestParam("phone")String phone,@RequestParam("positionTime")String positionTime){
        int k=0;
        Doctor doctor=doctorService.getDoctorByUsername(username);
        Doctor myDoctor=new Doctor();
        if (doctor==null){
            System.out.println("没有该对象！");
            myDoctor.setDusername(username);
            myDoctor.setDpassword(password);
            myDoctor.setdName(dName);
            myDoctor.setDgender(gender);
            myDoctor.setDphone(phone);
            myDoctor.setDpositionTime(positionTime);
            if (doctorService.setDoctor(myDoctor)==1){
                k=1;//成功注册
            }
        }
        return k;
    }
    //获取个人信息
    @GetMapping("/doctor/information")
    public Doctor getInformation(@RequestParam("username")String username){
       Doctor doctor= doctorService.getDoctorByUsername(username);
       if (doctor!=null){
           return doctor;
       }else {
           return null;
       }
    }
    //更新个人信息请求
    @PostMapping("/doctor/update")
    public int update(@RequestParam("username")String username,@RequestParam("password") String password,
                      @RequestParam("birthday") String birthday,
                      @RequestParam("dName") String dName,@RequestParam("phone")String phone){
       int k=0;
        Doctor doctor= doctorService.getDoctorByUsername(username);
        if (doctor!=null){
            System.out.println(doctor);
            doctor.setDpassword(password);
            doctor.setdName(dName);
            doctor.setDbirthday(birthday);
            doctor.setDphone(phone);
//            doctor.setDurl(url);
            doctorService.updateDoctor(doctor);
            k=1;//更新成功
        }
        return k;
    }
    //上传头像
    @PostMapping("/doctor/uploadImg")
    public int uploadIMG(@RequestParam("username")String username,@RequestParam("file") MultipartFile file){
        System.out.println("传输文件！");
        int k=0;//没有该用户
        Doctor doctor= doctorService.getDoctorByUsername(username);
        if (doctor!=null){
            if (!file.isEmpty()){//头像不为空
                String originName=file.getOriginalFilename();
                System.out.println("原始名："+originName+",获取的后缀名；.NPG="+originName.endsWith(".NPG")+",.jpg="+originName.endsWith(".jpg"));
                if (originName.endsWith(".NPG")||originName.endsWith(".jpg")){//后缀名为.NPG或者.jpg
                    doctor.setDurl(originName);
                    uploadFile(file,doctor.getDurl());
                    doctorService.updateDoctor(doctor);
                    k=1;//成功
                    return k;
                }
                k=-1;//文件格式不对，返回，不更新
                System.out.println("文件格式不对！");

            }
        }
        return k;
    }

    @GetMapping("/doctor/list")
    public List<Doctor> getALLDoctor(){
        List<Doctor> doctorList=doctorService.getDoctors();
        if (doctorList.size()>0){
            return doctorList;
        }
        return null;
    }

    //    文件上传，数据流的方式
    public void uploadFile(MultipartFile file, String relativeUrl) {
        try {
            OutputStream outputStream = new FileOutputStream(new File("F:/myDoctor/" + relativeUrl));
            System.out.println("上传文件！");
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
