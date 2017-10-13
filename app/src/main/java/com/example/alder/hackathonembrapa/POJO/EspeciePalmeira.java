package com.example.alder.hackathonembrapa.POJO;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alder on 12/10/2017.
 */

public class EspeciePalmeira extends RealmObject {

    @PrimaryKey
    private int cod_especie_palmeira;
    private String nome_especie_palmeira;

    public EspeciePalmeira(){

    }

    public EspeciePalmeira(int cod_especie_palmeira, String nome_especie_palmeira){
        this.cod_especie_palmeira = cod_especie_palmeira;
        this.nome_especie_palmeira = nome_especie_palmeira;
    }

    public int getCod_especie_palmeira() {
        return cod_especie_palmeira;
    }

    public void setCod_especie_palmeira(int cod_especie_palmeira) {
        this.cod_especie_palmeira = cod_especie_palmeira;
    }

    public String getNome_especie_palmeira() {
        return nome_especie_palmeira;
    }

    public void setNome_especie_palmeira(String nome_especie_palmeira) {
        this.nome_especie_palmeira = nome_especie_palmeira;
    }
}
