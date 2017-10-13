package com.example.alder.hackathonembrapa.POJO;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alder on 12/10/2017.
 */

public class ItemMedicaoArvore extends RealmObject {
    @PrimaryKey
    private
    int cod_medicao_arvore;

    private int quant_grossa;
    private int quant_media;
    private int quant_fina;
    private EspecieArvore especie;
    private Medicao medicao;

    public ItemMedicaoArvore(){

    }

    public ItemMedicaoArvore(int cod_medicao_arvore,int quant_grossa, int quant_media, int quant_fina,
            EspecieArvore especie, Medicao medicao){
        this.cod_medicao_arvore = cod_medicao_arvore;
        this.quant_grossa = quant_grossa;
        this.quant_media = quant_media;
        this.quant_fina = quant_fina;
        this.especie = especie;
        this.medicao = medicao;

    }

    public int getCod_medicao_arvore() {
        return cod_medicao_arvore;
    }

    public void setCod_medicao_arvore(int cod_medicao_arvore) {
        this.cod_medicao_arvore = cod_medicao_arvore;
    }

    public int getQuant_grossa() {
        return quant_grossa;
    }

    public void setQuant_grossa(int quant_grossa) {
        this.quant_grossa = quant_grossa;
    }

    public int getQuant_media() {
        return quant_media;
    }

    public void setQuant_media(int quant_media) {
        this.quant_media = quant_media;
    }

    public int getQuant_fina() {
        return quant_fina;
    }

    public void setQuant_fina(int quant_fina) {
        this.quant_fina = quant_fina;
    }

    public EspecieArvore getEspecie() {
        return especie;
    }

    public void setEspecie(EspecieArvore especie) {
        this.especie = especie;
    }

    public Medicao getMedicao() {
        return medicao;
    }

    public void setMedicao(Medicao medicao) {
        this.medicao = medicao;
    }
}
