package com.example.alder.hackathonembrapa.POJO;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alder on 12/10/2017.
 */

public class EspecieArvore extends RealmObject {

    @PrimaryKey
    private
    int cod_especie_arvore;

    private String nome_especie_arvore;

    public EspecieArvore(){

    }

    public EspecieArvore(int cod_especie_arvore, String nome_especie_arvore){
        this.cod_especie_arvore = cod_especie_arvore;
        this.nome_especie_arvore = nome_especie_arvore;
    }

    public int getCod_especie_arvore() {
        return cod_especie_arvore;
    }

    public void setCod_especie_arvore(int cod_especie_arvore) {
        this.cod_especie_arvore = cod_especie_arvore;
    }

    public String getNome_especie_arvore() {
        return nome_especie_arvore;
    }

    public void setNome_especie_arvore(String nome_especie_arvore) {
        this.nome_especie_arvore = nome_especie_arvore;
    }
}
