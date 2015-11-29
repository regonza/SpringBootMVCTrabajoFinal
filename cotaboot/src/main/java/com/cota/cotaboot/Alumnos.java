package com.cota.cotaboot;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by cota on 22/11/15.
 */
public class Alumnos {

    private  String name;
    private  String surname;
    private  String file;
    private  String subjects;
    private String career;
    private  int id=0;


    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alumnos(String name, String surname, String file, String subjects,String career) {
        this.name = name;
        this.surname = surname;
        this.file = file;
        this.subjects = subjects;
        this.career = career;
        this.id+=1;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFile() {
        return file;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }


    @Override
    public String toString(){
        return "Name:"+this.getName()+
               "  Surname:"+this.getSurname()+
               "  File:"+this.getFile()+
               "  Subjects:"+this.getSubjects()+
                "  Career:"+this.getCareer()+
               "  Nº de elemento:";
    }

}
