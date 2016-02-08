package com.example.ORMLiteOneToMany.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by sam on 03/02/2016.
 */
public class Client {

    public static final String FIELD_NOM = "nom";
    public static final String FIELD_COMMERCIAL = "commercial_id";

    public Client(){}

    public Client(String nom) {
        this.nom = nom;
    }

    public Client(String nom, Commercial commercial) {
        this.nom = nom;
        this.commercial = commercial;
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false, columnName = FIELD_NOM)
    private String nom;

    @DatabaseField (
            canBeNull = false,
            foreign = true,
            foreignAutoRefresh = true,
            columnName = FIELD_COMMERCIAL
    )
     private Commercial commercial;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Commercial getCommercial() {
        return commercial;
    }

    public void setCommercial(Commercial commercial) {
        this.commercial = commercial;
    }

    @Override
    public String toString() {
        return " Client : "+ this.nom +" / "+ this.commercial.toString();
    }
}
