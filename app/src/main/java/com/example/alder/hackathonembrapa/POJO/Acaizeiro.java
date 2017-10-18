package com.example.alder.hackathonembrapa.POJO;

import io.realm.RealmObject;

/**
 * Created by Alder on 17/10/2017.
 */

public class Acaizeiro extends RealmObject {

    private int cod_acaizeiro = 1;
    private String nome = "acaizeiro";

    public int getCod_acaizeiro() {
        return cod_acaizeiro;
    }

    public void setCod_acaizeiro(int cod_acaizeiro) {
        cod_acaizeiro = 1;
        this.cod_acaizeiro = cod_acaizeiro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        nome = "acaizeiro";
        this.nome = nome;
    }
}
