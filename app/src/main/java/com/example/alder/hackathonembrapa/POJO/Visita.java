package com.example.alder.hackathonembrapa.POJO;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alder on 06/10/2017.
 */

public class Visita extends RealmObject {

    private Local local;
    @PrimaryKey
    private int cod_visita;

    public Visita(){

    }

    public Visita(Local local, int cod_visita) {
        this.setLocal(local);
        this.setCod_visita(cod_visita);

    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }


    public int getCod_visita() {
        return cod_visita;
    }

    public void setCod_visita(int cod_visita) {
        this.cod_visita = cod_visita;
    }
}