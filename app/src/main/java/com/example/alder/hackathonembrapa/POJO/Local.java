package com.example.alder.hackathonembrapa.POJO;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alder on 05/10/2017.
 */

public class Local extends RealmObject {

    @PrimaryKey
    private int cod_local;
    private String nome_local;
    private double latitude;
    private double longitude;

    public Local(){

    }

    public Local(int cod_local,String nome,int latitude,int longitude){
        this.cod_local = cod_local;
        this.nome_local = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getCod_local() {
        return cod_local;
    }

    public void setCod_local(int cod_local) {
        this.cod_local = cod_local;
    }

    public String getNome() {
        return nome_local;
    }

    public void setNome(String nome) {
        this.nome_local = nome;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
