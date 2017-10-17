package com.example.alder.hackathonembrapa.Model;

import android.util.Log;

import com.example.alder.hackathonembrapa.POJO.EspecieArvore;
import com.example.alder.hackathonembrapa.POJO.Local;
import com.example.alder.hackathonembrapa.POJO.Visita;
import com.example.alder.hackathonembrapa.R;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Alder on 15/10/2017.
 */

public class EspecieArvoreDao {
    Realm realm = Realm.getDefaultInstance();

    public EspecieArvore insertEspecieArvore(String nome_especie){
        realm.beginTransaction();
        EspecieArvore especieArvore  = realm.createObject(EspecieArvore.class);
        int proximoID = 1;
        if(realm.where(EspecieArvore.class).max("cod_especie_arvore") != null)
            proximoID = realm.where(EspecieArvore.class).max("cod_especie_arvore").intValue() + 1;

        especieArvore.setCod_especie_arvore(proximoID);
        especieArvore.setNome_especie_arvore(nome_especie.toUpperCase());
        realm.commitTransaction();
        realm.close();

        RealmResults<EspecieArvore> especiesArvores = realm.where(EspecieArvore.class).findAll();
        Log.i("Local:",""+especiesArvores );
        return  especieArvore;
    }

    public boolean existEspecieArvore(String nome_especie){


        RealmResults<EspecieArvore> especiesArvores = realm.where(EspecieArvore.class).findAll();
        boolean existe = true;

        if(especiesArvores.size() == 0){
            return existe = false;

        }

        for (int i = 0; i < especiesArvores.size();i++){
            Number min = realm.where(EspecieArvore.class).min("cod_especie_arvore");
            EspecieArvore especieArvore = realm.where(EspecieArvore.class).equalTo("cod_especie_arvore",min.intValue()+i).findFirst();
            if(especieArvore.getNome_especie_arvore().equals(nome_especie.toUpperCase())){
                existe = true;
                Log.i("Existe dentro:", existe+"");
                Log.i("Nome da especie:", especieArvore.getNome_especie_arvore()+"\n nome da nova especie:"+nome_especie);
                break;
            }else{
                existe = false;
                Log.i("Nome da especie:", especieArvore.getNome_especie_arvore()+"\n nome da nova especie:"+nome_especie);
                Log.i("Existe dentro:", existe+"");
            }
        }
        Log.i("Existe fora:", existe+"");
        return existe;
    }

    public EspecieArvore selectEspecieArvore(String nome_especie){
        RealmResults<EspecieArvore> especiesArvores = realm.where(EspecieArvore.class).findAll();

        EspecieArvore especieArvore = realm.where(EspecieArvore.class).equalTo("nome_especie_arvore",nome_especie.toUpperCase()).findFirst();
        Log.i("especie Arvore retorno",especieArvore.toString());
        return especieArvore;
//        for (int i = 0; i < especiesArvores.size();i++){
//            Number min = realm.where(EspecieArvore.class).min("cod_especie_arvore");
//
//            EspecieArvore especieArvore = realm.where(EspecieArvore.class).equalTo("cod_especie_arvore",min.intValue()+i).findFirst();
//
//            if(especieArvore.getNome_especie_arvore().equals(nome_especie)){
//                especieArvoreRetorno = especieArvore;
//                Log.i("especieArvoreRetorno",especieArvore.getNome_especie_arvore());
//
//            }
//        }


    }
}
