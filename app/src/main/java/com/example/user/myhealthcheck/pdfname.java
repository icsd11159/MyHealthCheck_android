package com.example.user.myhealthcheck;

/**
 * Created by user on 5/5/2018.
 */

public class pdfname {
    private String name;
    private int id_exam;

    public pdfname(String name,int id_exam) {
        this.name = name;
        this.id_exam=id_exam;
    }
    public String getname() {
        return name;
    }
    public int getId_e() {
        return  id_exam;
    }
}
