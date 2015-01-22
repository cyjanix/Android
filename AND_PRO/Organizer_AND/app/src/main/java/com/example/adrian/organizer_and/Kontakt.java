package com.example.adrian.organizer_and;

/**
 * Created by Adrian on 2015-01-22.
 */
public class Kontakt {

    private String nazwa;
    private String telefon;

    public Kontakt(String nazwa, String telefon)
    {
        this.nazwa = nazwa;
        this.telefon = telefon;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

}
