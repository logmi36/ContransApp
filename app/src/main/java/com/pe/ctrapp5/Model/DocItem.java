package com.pe.ctrapp5.Model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DocItem implements Serializable {


    @SerializedName("num")
    @Expose
    private String num;

    @SerializedName("ser")
    @Expose
    private String ser;

    @SerializedName("des")
    @Expose
    private String des;

    @SerializedName("tip")
    @Expose
    private String tip;

    @SerializedName("com")
    @Expose
    private String com;

    @SerializedName("rem")
    @Expose
    private String rem;


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSer() {
        return ser;
    }

    public void setSer(String ser) {
        this.ser = ser;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    public DocItem(String num, String ser, String des, String tip, String com, String rem) {
        this.num = num;
        this.ser = ser;
        this.des = des;
        this.tip = tip;
        this.com = com;
        this.rem = rem;
    }

    @Override
    public String toString() {
        return "DocItem{" +
                "num='" + num + '\'' +
                ", ser='" + ser + '\'' +
                ", des='" + des + '\'' +
                ", tip='" + tip + '\'' +
                ", com='" + com + '\'' +
                ", rem='" + rem + '\'' +
                '}';
    }
}
