package com.java.web_ecommerce_spring.models;

public class ChangeNumber {
    int giaCu;
    int giaMoi;
    int total;

    public int getGiaCu() {
        return giaCu;
    }

    public void setGiaCu(int giaCu) {
        this.giaCu = giaCu;
    }

    public int getGiaMoi() {
        return giaMoi;
    }

    public void setGiaMoi(int giaMoi) {
        this.giaMoi = giaMoi;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ChangeNumber() {
    }

    public ChangeNumber(int giaCu, int giaMoi, int total) {
        this.giaCu = giaCu;
        this.giaMoi = giaMoi;
        this.total = total;
    }
}
