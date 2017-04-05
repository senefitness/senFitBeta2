package com.seneca.android.senfitbeta;

/**
 * Created by dance on 4/4/2017.
 */

public class Muscle {
    private int id;
    private String name;

    public Muscle(){
         id =0;
        name = "";
    }
    public Muscle(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}