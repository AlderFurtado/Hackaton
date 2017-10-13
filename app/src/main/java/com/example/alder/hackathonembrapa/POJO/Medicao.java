package com.example.alder.hackathonembrapa.POJO;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alder on 06/10/2017.
 */

public class Medicao extends RealmObject {

    @PrimaryKey
    private int cod_medicao;
    private Visita visita;

    public Medicao(){

    }

    public Medicao(int cod_medicao,Visita visita){
        this.cod_medicao = cod_medicao;
        this.visita = visita;
    }

    public int getCod_medicao() {
        return cod_medicao;
    }

    public void setCod_medicao(int cod_medicao) {
        this.cod_medicao = cod_medicao;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }
}
