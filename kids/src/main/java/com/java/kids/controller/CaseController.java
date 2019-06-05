package com.java.kids.controller;

import com.java.kids.Service.CaseService;
import com.java.kids.model.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.lang.model.util.SimpleAnnotationValueVisitor6;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController//返回数据，不返回页面
public class CaseController {
    @Autowired
    private CaseService caseService;

    //医生获取病例
    @GetMapping("/doctor/advise")
    public List<Case> getDoctorCase(@RequestParam("dNo") String dNo){
        List<Case> doctorCase=caseService.doctorCases(dNo);
        if (doctorCase!=null){
            return doctorCase;
        }
        return null;
    }

    //医生回复病例的请求
    @PostMapping("/doctor/send_advise")
    public int sendAdvise(@RequestParam("advise")String advise,@RequestParam("id")String id){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        caseService.sendAdvise(id,advise,df.format(new Date()));
        return 1;
    }
    //病人获取自己的病例
    @GetMapping("/patient/advise")
    public List<Case> getPatientCase(@RequestParam("pNo") String pNo){
        List<Case> patientCase=caseService.kidCase(pNo);
        if (patientCase!=null){
            return patientCase;
        }
        return null;
    }

    //创建病例
    @PostMapping("/patient/creat_case")
    public String createPCase(@RequestParam("pNo")String pNo,
                           @RequestParam("dNo") String dNo,@RequestParam("describe") String describe){
        String id=createCase(pNo,dNo,describe);
        if (id!=""){
            return id;
        }
        return "";
    }

    /**
     * 病人创建自己的病例，，
     * @param pNo 病人username，
     * @param dNo 医生username
     * @param describe 病情描述
     * @return
     * 预约打疫苗的时候，医生username为空，病情描述为空，只有病人的username
     */
    public String createCase(String pNo,String dNo,String describe){
        int k=0;
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=df.format(new Date());
        String id=time.replace(" ","");
        id=id.replace("-","");
        id=id.replace(":","");
        System.out.println("当前系统的时间："+id);
        if (describe==""){
            describe="no describe";
        }
        System.out.println("id="+id+",pNo="+pNo+",dNo="+dNo+",describe="+describe);
        if (id!=null&&pNo!=null&&dNo!=null&&describe!=null){
            k=caseService.setCase(id,pNo,dNo,describe);
            if (id!=""&&k==1){
                return id;
            }
        }
        return "";
    }
    //医生处理过的病例
    @GetMapping("/doctor/handling")
    public List<Case> handling(@RequestParam("dusername") String dNo){
       return caseService.handlingCase(dNo);
    }

    public Case getCaseByCno(String id){
        Case aCase=caseService.getMyCases(id);
        if (aCase!=null){
            return aCase;
        }
        return null;
    }
}
