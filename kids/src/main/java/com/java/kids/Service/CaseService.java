package com.java.kids.Service;

import com.java.kids.model.Case;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CaseService {
    public List<Case> doctorCases(String dNo);//医生取得相关的病例
    public Case getMyCases(String id);//获取该病例
    public void sendAdvise(String id,String advise,String lookTime);

    public List<Case> kidCase(String pNo);//病人取得相关的病例
    //建立病例
    public int setCase(String id,String pNo,String dNo,String describe);
    //医生处理过的病例表lookTime！=""
    public List<Case> handlingCase(String dNo);
}
