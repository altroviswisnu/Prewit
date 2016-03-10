package com.altrovis.prewit.Entities;

import java.util.Date;

/**
 * Created by Wisnu on 10/03/2016.
 */
public class Project {

    private int ID;
    private String Nama;
    private Date Created;

    public Project() {
    }

    public Project(int ID, String nama, Date created) {
        this.ID = ID;
        Nama = nama;
        Created = created;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public Date getCreated() {
        return Created;
    }

    public void setCreated(Date created) {
        Created = created;
    }

    @Override
    public String toString(){
        return Nama;
    }
}
