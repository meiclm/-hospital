package com.java.kids.dao;

import com.java.kids.model.Case;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CaseMapper {
    public List<Case> doctorCases(String dNo);//医生取得相关的病例
    public Case getMyCases(String id);//获取该病例

    /**
     * @Param是MyBatis所提供的(org.apache.ibatis.annotations.Param)，
     * 作为Dao层的注解，作用是用于传递参数，从而可以与SQL中的的字段名相对应，
     * 一般在2=<参数数<=5时使用最佳。
     */
    public void sendAdvise(@Param("id") String id,@Param("advise") String advise,@Param("lookTime") String lookTime);


    public List<Case> patientCase(String pNo);//病人取得相关的病例
    public void setCase(@Param("id")String id,@Param("pNo")String pNo,@Param("dNo")String dNo,@Param("describe")String describe);

    //医生处理过的病例表lookTime！=""
    public List<Case> handlingCase(String dNo);

}
