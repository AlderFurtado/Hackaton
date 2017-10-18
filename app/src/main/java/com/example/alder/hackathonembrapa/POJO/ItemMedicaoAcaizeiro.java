package com.example.alder.hackathonembrapa.POJO;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alder on 17/10/2017.
 */

public class ItemMedicaoAcaizeiro extends RealmObject {

    @PrimaryKey
    private
    int cod_medicao_acaizeiro;

    private int quant_adultos;
    private int quant_jovens;
    private int quant_perfilhos;

    private Acaizeiro acaizeiro;

    public int getCod_medicao_acaizeiro() {
        return cod_medicao_acaizeiro;
    }

    public void setCod_medicao_acaizeiro(int cod_medicao_acaizeiro) {
        this.cod_medicao_acaizeiro = cod_medicao_acaizeiro;
    }

    public int getQuant_adultos() {
        return quant_adultos;
    }

    public void setQuant_adultos(int quant_adultos) {
        this.quant_adultos = quant_adultos;
    }

    public int getQuant_jovens() {
        return quant_jovens;
    }

    public void setQuant_jovens(int quant_jovens) {
        this.quant_jovens = quant_jovens;
    }

    public int getQuant_perfilhos() {
        return quant_perfilhos;
    }

    public void setQuant_perfilhos(int quant_perfilhos) {
        this.quant_perfilhos = quant_perfilhos;
    }

    public Acaizeiro getAcaizeiro() {
        return acaizeiro;
    }

    public void setAcaizeiro(Acaizeiro acaizeiro) {
        this.acaizeiro = acaizeiro;
    }
}
