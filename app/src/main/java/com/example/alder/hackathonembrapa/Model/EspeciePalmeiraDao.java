package com.example.alder.hackathonembrapa.Model;

import android.util.Log;

import com.example.alder.hackathonembrapa.POJO.EspecieArvore;
import com.example.alder.hackathonembrapa.POJO.EspeciePalmeira;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Alder on 16/10/2017.
 */

public class EspeciePalmeiraDao {
    Realm realm = Realm.getDefaultInstance();

    public EspeciePalmeira insertEspeciePalmeira(String nome_especie){
        realm.beginTransaction();
        EspeciePalmeira especiePalmeira  = realm.createObject(EspeciePalmeira.class);
        int proximoID = 1;
        if(realm.where(EspeciePalmeira.class).max("cod_especie_palmeira") != null)
            proximoID = realm.where(EspeciePalmeira.class).max("cod_especie_palmeira").intValue() + 1;

        especiePalmeira.setCod_especie_palmeira(proximoID);
        especiePalmeira.setNome_especie_palmeira(nome_especie.toUpperCase());

        realm.commitTransaction();
        realm.close();

        RealmResults<EspecieArvore> especiesArvores = realm.where(EspecieArvore.class).findAll();
        Log.i("Local:",""+especiesArvores );
        return  especiePalmeira;
    }

    public boolean existEspeciePalmeira(String nome_especie){


        RealmResults<EspeciePalmeira> especiesPalmeiras = realm.where(EspeciePalmeira.class).findAll();
        boolean existe = true;

        if(especiesPalmeiras.size() == 0){
            return existe = false;

        }

        for (int i = 0; i < especiesPalmeiras.size();i++){
            Number min = realm.where(EspeciePalmeira.class).min("cod_especie_palmeira");
            EspeciePalmeira especiePalmeira = realm.where(EspeciePalmeira.class).equalTo("cod_especie_palmeira",min.intValue()+i).findFirst();
            if(especiePalmeira.getNome_especie_palmeira().equals(nome_especie.toUpperCase())){
                existe = true;
                Log.i("Existe dentro:", existe+"");
                Log.i("Nome da especie:", especiePalmeira.getNome_especie_palmeira()+"\n nome da nova especie:"+nome_especie);
                break;
            }else{
                existe = false;
                Log.i("Nome da especie:", especiePalmeira.getNome_especie_palmeira()+"\n nome da nova especie:"+nome_especie);
                Log.i("Existe dentro:", existe+"");
            }
        }
        Log.i("Existe fora:", existe+"");
        return existe;
    }

    public EspeciePalmeira selectEspeciePalmeira(String nome_especie){
        RealmResults<EspeciePalmeira> especiesPalmeiras = realm.where(EspeciePalmeira.class).findAll();

        EspeciePalmeira especiePalmeira = realm.where(EspeciePalmeira.class).equalTo("nome_especie_palmeira",nome_especie.toUpperCase()).findFirst();
        Log.i("especie Arvore retorno",especiePalmeira.toString());
        return especiePalmeira;
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
