package com.example.alder.hackathonembrapa.Model;

import com.example.alder.hackathonembrapa.POJO.ItemMedicaoArvore;
import com.example.alder.hackathonembrapa.POJO.Local;
import com.example.alder.hackathonembrapa.POJO.Visita;

import io.realm.Realm;

/**
 * Created by Alder on 14/10/2017.
 */

public class  VisitaDao {
    Visita visita = new Visita();
    Realm realm = Realm.getDefaultInstance();

    public Visita insertVisita(String data){
        realm.beginTransaction();
        Visita visita = realm.createObject(Visita.class);
        int proximoID = 1;
        if(realm.where(Visita.class).max("cod_visita") != null)
            proximoID = realm.where(Visita.class).max("cod_visita").intValue() + 1;

        visita.setCod_visita(proximoID);
        visita.setData(data);
        realm.commitTransaction();
        realm.close();

        return  visita;
    }

    public void insertItemEspecieArvore(int cod_visita, ItemMedicaoArvore itemMedicaoArvore){

        Visita visita = realm.where(Visita.class).equalTo("cod_visita",cod_visita).findFirst();
        realm.beginTransaction();
        visita.setItemMedicaoArvores(itemMedicaoArvore);
        realm.commitTransaction();
        realm.close();
    }





}
