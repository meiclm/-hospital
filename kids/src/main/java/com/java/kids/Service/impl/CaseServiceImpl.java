package com.java.kids.Service.impl;

import com.java.kids.Service.CaseService;
import com.java.kids.dao.CaseMapper;
import com.java.kids.model.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseServiceImpl implements CaseService {
    @Autowired
    private CaseMapper caseMapper;
    @Override
    public List<Case> doctorCases(String dNo) {
        return caseMapper.doctorCases(dNo);
    }

    @Override
    public Case getMyCases(String id) {
        return caseMapper.getMyCases(id);
    }

    @Override
    public void sendAdvise(String id, String advise,String lookTime) {
        caseMapper.sendAdvise(id,advise,lookTime);
    }

    @Override
    public List<Case> kidCase(String pNo) {
        return caseMapper.patientCase(pNo);
    }

    @Override
    public int setCase(String id, String pNo, String dNo,String describe) {
        if (id!=""&&pNo!=""&&dNo!=""&&describe!=""){
            caseMapper.setCase(id,pNo,dNo,describe);
            System.out.println("setCase Impl");
            return 1;
        }
        return -1;
    }

    //医生处理过的病例
    @Override
    public List<Case> handlingCase(String dNo) {
        List<Case> aCase=caseMapper.handlingCase(dNo);
        if (aCase!=null){
            return aCase;
        }
        return null;
    }
}
