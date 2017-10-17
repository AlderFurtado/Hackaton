package com.example.alder.hackathonembrapa.POJO;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alder on 12/10/2017.
 */

public class ItemMedicaoPalmeira extends RealmObject {

    @PrimaryKey
    private
    int cod_medicao_palmeira;

    private int quant_jovem;
    private int quant_adulta;
    private RealmList<EspeciePalmeira> especiePalmeira;


    public ItemMedicaoPalmeira(){

    }

    public int getCod_medicao_palmeira() {
        return cod_medicao_palmeira;
    }

    public void setCod_medicao_palmeira(int cod_medicao_palmeira) {
        this.cod_medicao_palmeira = cod_medicao_palmeira;
    }

    public int getQuant_jovem() {
        return quant_jovem;
    }

    public void setQuant_jovem(int quant_jovem) {
        this.quant_jovem = quant_jovem;
    }

    public int getQuant_adulta() {
        return quant_adulta;
    }

    public void setQuant_adulta(int quant_adulta) {
        this.quant_adulta = quant_adulta;
    }


    public RealmList<EspeciePalmeira> getEspeciePalmeira() {
        return especiePalmeira;
    }

    public void setEspeciePalmeira(EspeciePalmeira especiePalmeira) {
        this.especiePalmeira.add(especiePalmeira);
    }
}
