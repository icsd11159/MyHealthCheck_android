package com.example.user.myhealthcheck;

import java.sql.Date;

/**
 * Created by (by Belal)user on 28/12/2017.
 */

public class Exam {
    private int amka;
    private int id_doctor;
    private String type_ex;
    private String text;
    private String date_e;
    private String comments;
  //  private String name;
    private int id_e;

    public Exam(int amka, int id_doctor,int id_e, String type_ex, String text ,String date_e, String comments) {//,String name
        this.amka = amka;
        this.id_doctor = id_doctor;
        this.id_e = id_e;
        this.type_ex = type_ex;
        this.text = text;
        this.date_e = date_e;
        this.comments = comments;
      //  this.name = name;
    }

    public int getAmka() {
        return amka;
    }

    public int getid_doctor() {
        return  id_doctor;
    }

    public int getId_e() {
        return  id_e;
    }

    public String getType() {
        return type_ex;
    }

    public String getName_exam() {
        return text;
    }



    public String getDate() {
        return date_e;
    }
    public String getComments() {
        return comments;
    }
   // public String getname() {
      //  return name;
   // }
}