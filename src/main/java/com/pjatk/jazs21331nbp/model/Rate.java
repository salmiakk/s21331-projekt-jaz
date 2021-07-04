package com.pjatk.jazs21331nbp.model;

public class Rate {
    private String data;
    private Double cena;

    public Rate(String data, Double cena) {
        this.data = data;
        this.cena = cena;
    }

    public Rate() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }
}
