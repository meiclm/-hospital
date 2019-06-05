package com.java.kids.model;

public class Vaccine {
    public int vid;
    public String name;
    public String pathogenesis;//症状
    public String precaution;//预防
    public String handling;//处理

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathogenesis() {
        return pathogenesis;
    }

    public void setPathogenesis(String pathogenesis) {
        this.pathogenesis = pathogenesis;
    }

    public String getPrecaution() {
        return precaution;
    }

    public void setPrecaution(String precaution) {
        this.precaution = precaution;
    }

    public String getHandling() {
        return handling;
    }

    public void setHandling(String handling) {
        this.handling = handling;
    }
}
