package com.example.adrian.class_help;

/**
 * Created by Adrian on 2015-01-21.
 */
public class Task {

    private long l_Id;
    private String s_Name;
    private String s_Number;
    private String s_Date;
    private String s_Place;

    public Task(long id, String name, String number, String date, String place)
    {
        this.l_Id = id;
        this.s_Name = name;
        this.s_Number = number;
        this.s_Date = date;
        this.s_Place = place;
    }

    public String getPlace() { return s_Place; }

    public void setPlace(String place) {
        this.s_Place = place;
    }

    public String getDate() {
        return s_Date;
    }

    public void setDate(String date) {
        this.s_Date = date;
    }

    public String getNumber() {
        return s_Number;
    }

    public void setNumber(String number) {
        this.s_Number = number;
    }

    public String getName() {
        return s_Name;
    }

    public void setName(String name) {
        this.s_Name = name;
    }

    public long getId() {
        return l_Id;
    }

    public void setId(long id) {
        this.l_Id = id;
    }



}
