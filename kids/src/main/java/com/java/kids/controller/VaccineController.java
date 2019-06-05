package com.java.kids.controller;

import com.java.kids.Service.VaccineService;
import com.java.kids.model.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VaccineController {
    @Autowired
    private VaccineService vaccineService;

    @GetMapping("/vaccines")
    public List<Vaccine> getVaccines(){
        List<Vaccine> vaccineList=vaccineService.findAllVaccine();
        if (vaccineList!=null){
            return vaccineList;
        }
        return null;
    }
    //搜索疫苗
    @GetMapping("/vaccine")
    public List<Vaccine> getTheseVaccine(@RequestParam("vName") String vName){
        List<Vaccine> vaccineList=vaccineService.findVaccine(vName);
        if (vaccineList.size()>0){
            return vaccineList;
        }
        return null;
    }
    //查看特定的一种疫苗
    @GetMapping("/one_vaccine")
    public Vaccine OneVaccine(@RequestParam("vName") String vName){
        Vaccine vaccine= vaccineService.findOneVaccine(vName);
        if (vaccine!=null){
            return vaccine;
        }
        return null;
    }
    //添加数据
    @PostMapping("/vaccine/insert")
    public int insertVaccine(@RequestParam("vName")String name,@RequestParam("pathogenesis")String pathogenesis,
                             @RequestParam("precaution")String precaution,@RequestParam("handling")String handling){
        Vaccine newVaccine=new Vaccine();
        newVaccine.setName(name);
        newVaccine.setPathogenesis(pathogenesis);
        newVaccine.setPrecaution(precaution);
        newVaccine.setHandling(handling);
        return vaccineService.insertVaccine(newVaccine);
    }
    //修改单个疫苗数据
    @PostMapping("/vaccine/modified")
    public int modifiedVaccine(@RequestParam("vid")int id,@RequestParam("vName")String name,@RequestParam("pathogenesis")String pathogenesis,
                               @RequestParam("precaution")String precaution,@RequestParam("handling")String handling){
        Vaccine vaccine=new Vaccine();
        vaccine.setVid(id);
        vaccine.setName(name);
        vaccine.setPathogenesis(pathogenesis);
        vaccine.setPrecaution(precaution);
        vaccine.setHandling(handling);
        return vaccineService.modifiedOneVaccine(vaccine);
    }
    //删除该条疫苗
//    @PostMapping("/vaccine/del")
//    public
}
