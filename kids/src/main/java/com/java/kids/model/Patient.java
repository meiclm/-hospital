package com.java.kids.model;

public class Patient {
    public String pusername;
    public String ppassword;
    public String pName;
    public String pbirthday;
    public int pgender;
    public String pphone;
    public String pparent;
    public String openId;
//    public int age;//
    public Patient(){}
    public void MyPatient(String pusername,String ppassword,String pName,String pbirthday,int pgender,
                   String pphone,String pparent){
        this.pusername=pusername;
        this.ppassword=ppassword;
        this.pName=pName;
        this.pbirthday=pbirthday;
        this.pgender=pgender;
        this.pphone=pphone;
        this.pparent=pparent;
    }

    public String getPusername() {
        return pusername;
    }

    public void setPusername(String pusername) {
        this.pusername = pusername;
    }

    public String getPpassword() {
        return ppassword;
    }

    public void setPpassword(String ppassword) {
        this.ppassword = ppassword;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getPbirthday() {
        return pbirthday;
    }

    public void setPbirthday(String pbirthday) {
        this.pbirthday = pbirthday;
    }

    public int getPgender() {
        return pgender;
    }

    public void setPgender(int pgender) {
        this.pgender = pgender;
    }

    public String getPphone() {
        return pphone;
    }

    public void setPphone(String pphone) {
        this.pphone = pphone;
    }

    public String getPparent() {
        return pparent;
    }

    public void setPparent(String pparent) {
        this.pparent = pparent;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
