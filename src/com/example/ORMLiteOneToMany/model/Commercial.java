package com.example.ORMLiteOneToMany.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by sam on 03/02/2016.
 */
@DatabaseTable
public class Commercial {
    public static final String FIELD_NOM = "nom";

    public Commercial(){}

    public Commercial(String nom) {
        this.nom = nom;
    }

    @DatabaseField (generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false, columnName = FIELD_NOM)
    private String nom;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return " Commercial :"+ this.nom;
    }
}
