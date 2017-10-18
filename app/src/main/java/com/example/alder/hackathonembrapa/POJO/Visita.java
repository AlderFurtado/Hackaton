package com.example.alder.hackathonembrapa.POJO;

import com.example.alder.hackathonembrapa.Model.ItemMedicaoAcaizeiroDao;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alder on 06/10/2017.
 */

public class Visita extends RealmObject {


    @PrimaryKey
    private int cod_visita;
    private String data;
    private RealmList<ItemMedicaoArvore> itemMedicaoArvores;
    private RealmList<ItemMedicaoPalmeira> itemMedicaoPalmeiras;
    private ItemMedicaoAcaizeiro itemMedicaoAcaizeiro;

    public  Visita(){

    }

    public int getCod_visita() {
        return cod_visita;
    }

    public void setCod_visita(int cod_visita) {
        this.cod_visita = cod_visita;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public RealmList<ItemMedicaoArvore> getItemMedicaoArvores() {
        return itemMedicaoArvores;
    }

    public void setItemMedicaoArvores(ItemMedicaoArvore itemMedicaoArvores) {
        this.itemMedicaoArvores.add(itemMedicaoArvores);
    }

    public RealmList<ItemMedicaoPalmeira> getItemMedicaoPalmeiras() {
        return itemMedicaoPalmeiras;
    }

    public void setItemMedicaoPalmeiras(ItemMedicaoPalmeira itemMedicaoPalmeiras) {
        this.itemMedicaoPalmeiras.add(itemMedicaoPalmeiras);
    }


    public ItemMedicaoAcaizeiro getItemMedicaoAcaizeiro() {
        return itemMedicaoAcaizeiro;
    }

    public void setItemMedicaoAcaizeiro(ItemMedicaoAcaizeiro itemMedicaoAcaizeiro) {
        this.itemMedicaoAcaizeiro = itemMedicaoAcaizeiro;
    }
}