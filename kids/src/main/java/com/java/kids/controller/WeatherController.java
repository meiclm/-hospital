package com.java.kids.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class WeatherController {
    //获取当地的日气温

    //判断是否是在季节变化的时间
    /**
     * 3月-5月，春
     * 6-8，夏
     * 9-11秋
     * 12-2冬
     */
    @GetMapping("/patient/season")
    public List<String> seasonChang(@RequestParam("sign")String sign){
        List<String> note;
        String filePath="F:\\myDoctor\\data\\";
        //春分，夏至，秋分，冬至
        if (sign.equals("spring")){
            note=txt2String(filePath+"spring.txt");
        }else if (sign.equals("summer")){
            note=txt2String(filePath+"summer.txt");
        }else if (sign.equals("autumn")){
            note=txt2String(filePath+"autumn.txt");
        }else {
            note=txt2String(filePath+"winter.txt");
        }
        return note;
    }

    //获取txt文件的内容
    public List<String> txt2String(String filePath){
        List<String> listTxt=new ArrayList<>();
        String lineTxt="";
        File file=new File(filePath);
        try {
            InputStreamReader reader=new InputStreamReader(new FileInputStream(file),"utf-8");
            BufferedReader bReader=new BufferedReader(reader);
            while ((lineTxt=bReader.readLine())!=null){
                listTxt.add(lineTxt);
//                System.out.println(lineTxt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTxt;
    }
}
