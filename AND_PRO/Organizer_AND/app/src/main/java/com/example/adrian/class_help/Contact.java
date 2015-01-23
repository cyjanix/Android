package com.example.adrian.class_help;

public class Contact {

    private String name;
    private String number;

    public Contact(String nazwa, String number)
    {
        this.name = nazwa;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
