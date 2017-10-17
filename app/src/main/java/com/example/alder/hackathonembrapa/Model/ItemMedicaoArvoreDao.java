package com.example.alder.hackathonembrapa.Model;

import android.util.Log;

import com.example.alder.hackathonembrapa.POJO.EspecieArvore;
import com.example.alder.hackathonembrapa.POJO.ItemMedicaoArvore;
import com.example.alder.hackathonembrapa.POJO.Local;
import com.example.alder.hackathonembrapa.POJO.Visita;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Alder on 15/10/2017.
 */

public class ItemMedicaoArvoreDao {
    Realm realm = Realm.getDefaultInstance();

    public ItemMedicaoArvore insertItemMedicao(EspecieArvore especieArvore
            ,boolean grossa,boolean media,boolean fina){

        realm.beginTransaction();
        ItemMedicaoArvore itemMedicaoArvore = realm.createObject(ItemMedicaoArvore.class);
        int proximoID = 1;
        if(realm.where(ItemMedicaoArvore.class).max("cod_medicao_arvore") != null)
            proximoID = realm.where(ItemMedicaoArvore.class).max("cod_medicao_arvore").intValue() + 1;
        if(grossa == true){
            itemMedicaoArvore.setQuant_grossa(1);
        }
        if(media == true){
            itemMedicaoArvore.setQuant_media(1);
        }
        if(fina == true){
            itemMedicaoArvore.setQuant_fina(1);
        }
        itemMedicaoArvore.setCod_medicao_arvore(proximoID);
        itemMedicaoArvore.setEspecieArvores(especieArvore);
        realm.commitTransaction();
        realm.close();

        return  itemMedicaoArvore;

    }

    public void insertQuantidade(int cod_medicao_arvore,int cod_visita,Visita visita,EspecieArvore especieArvore,
                                 boolean grossa,boolean media,boolean fina){
        ItemMedicaoArvore itemMedicaoArvore;
        //ItemMedicaoArvore itemMedicaoArvore = realm.where(ItemMedicaoArvore.class).equalTo("cod_medicao_arvore",cod_medicao_arvore).findFirst();
        RealmList<ItemMedicaoArvore> itemMedicaoArvoresVisita = visita.getItemMedicaoArvores();
        itemMedicaoArvore = itemMedicaoArvoresVisita.where().equalTo("cod_medicao_arvore",cod_medicao_arvore).findFirst();
        if (itemMedicaoArvore == null){
            VisitaDao visita1 = new VisitaDao();
            visita1.insertItemEspecieArvore(cod_visita,new ItemMedicaoArvoreDao().insertItemMedicao(especieArvore,grossa,media,fina));

        } else {

            realm.beginTransaction();

            if (grossa == true) {
                //Number max = realm.where(ItemMedicaoArvore.class).max("quant_grossa");
                int max = itemMedicaoArvore.getQuant_grossa();
                itemMedicaoArvore.setQuant_grossa(max + 1);
            }
            if (media == true) {
                //Number max = realm.where(ItemMedicaoArvore.class).max("quant_media");
                int max = itemMedicaoArvore.getQuant_media();
                itemMedicaoArvore.setQuant_media(max + 1);
            }
            if (fina == true) {
                //Number max = realm.where(ItemMedicaoArvore.class).max("quant_fina");
                int max = itemMedicaoArvore.getQuant_fina();
                itemMedicaoArvore.setQuant_fina(max + 1);
            }
            realm.commitTransaction();
            realm.close();
        }


}

    public int selectItemMedicaoArvore(EspecieArvore especieArvore, Visita visita){
        int cod_medicao_arvore = 0;

        RealmResults<ItemMedicaoArvore> itemMedicaoArvores = realm.where(ItemMedicaoArvore.class).findAll();

        for (int i = 0; i < visita.getItemMedicaoArvores().size();i++){


            if (visita.getItemMedicaoArvores().get(i).getEspecieArvores().getNome_especie_arvore().toString().equals(especieArvore.getNome_especie_arvore())){
                cod_medicao_arvore = visita.getItemMedicaoArvores().get(i).getCod_medicao_arvore();

            }else{

            }
        }

        return cod_medicao_arvore;
    }

}
