package com.example.adrian.sqllite;

/**
 * Created by Adrian on 2015-01-21.
 */
public class Zadanie {

    private long id;
    private String nazwa;
    private String telefon;
    private String data;

    private String miejsce;

    public Zadanie(long id, String nazwa, String telefon, String data, String miejsce)
    {
        this.id = id;
        this.nazwa = nazwa;
        this.telefon = telefon;
        this.data = data;
        this.miejsce = miejsce;
    }

    public String getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



}
