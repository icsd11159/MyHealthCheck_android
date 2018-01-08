package com.example.user.myhealthcheck;

import java.sql.Date;

/**
 * Created by (by Belal)user on 28/12/2017.
 */

public class Exam {
    private int amka;
    private int id_d;
    private String type;
    private String name_exam;
    private String result;
   private String date;
    private String comments;
    private String file;

    public Exam(int amka, int id_d, String type, String name_exam, String result ,String date, String comments,String file) {
        this.amka = amka;
        this.id_d = id_d;
        this.type = type;
        this.name_exam = name_exam;
        this.result = result;
        this.date = date;
        this.comments = comments;
        this.file = file;
    }

    public int getAmka() {
        return amka;
    }

    public int getId_d() {
        return  id_d;
    }

    public String getType() {
        return type;
    }

    public String getName_exam() {
        return name_exam;
    }

    public String getResult() {
        return result;
    }

    public String getDate() {
       return date;
    }
    public String getComments() {
        return comments;
    }
    public String getfile() {
        return file;
    }
}