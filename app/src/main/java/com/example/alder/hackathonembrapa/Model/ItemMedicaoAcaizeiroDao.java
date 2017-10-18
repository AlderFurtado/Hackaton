package com.example.alder.hackathonembrapa.Model;

import android.util.Log;

import com.example.alder.hackathonembrapa.POJO.Acaizeiro;
import com.example.alder.hackathonembrapa.POJO.EspecieArvore;
import com.example.alder.hackathonembrapa.POJO.ItemMedicaoAcaizeiro;
import com.example.alder.hackathonembrapa.POJO.ItemMedicaoArvore;
import com.example.alder.hackathonembrapa.POJO.Visita;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Alder on 17/10/2017.
 */

public class ItemMedicaoAcaizeiroDao {
    Realm realm = Realm.getDefaultInstance();
    Acaizeiro acaizeiro = realm.where(Acaizeiro.class).findFirst();
    public ItemMedicaoAcaizeiro insertItemMedicao(boolean adulto,boolean jovem,boolean perfilho){

        realm.beginTransaction();
        ItemMedicaoAcaizeiro itemMedicaoAcaizeiro = realm.createObject(ItemMedicaoAcaizeiro.class);
        int proximoID = 1;
        if(realm.where(ItemMedicaoAcaizeiro.class).max("cod_medicao_acaizeiro") != null)

            
            proximoID = realm.where(ItemMedicaoAcaizeiro.class).max("cod_medicao_acaizeiro").intValue() + 1;
        if(adulto == true){
            itemMedicaoAcaizeiro.setQuant_adultos(1);
        }
        if(jovem == true){
            itemMedicaoAcaizeiro.setQuant_jovens(1);
        }
        if(perfilho == true){
            itemMedicaoAcaizeiro.setQuant_perfilhos(1);
        }
        itemMedicaoAcaizeiro.setCod_medicao_acaizeiro(proximoID);
        itemMedicaoAcaizeiro.setAcaizeiro(acaizeiro);
        realm.commitTransaction();
        realm.close();

        return  itemMedicaoAcaizeiro;

    }

//    public ItemMedicaoAcaizeiro insertItemMedicao(Acaizeiro acaizeiro
//            ,boolean adultos,boolean jovens,boolean perfilhos){
//
//        realm.beginTransaction();
//        ItemMedicaoAcaizeiro itemMedicaoAcaizeiro = realm.createObject(ItemMedicaoAcaizeiro.class);
//        int proximoID = 1;
//        if(realm.where(ItemMedicaoAcaizeiro.class).max("cod_medicao_arvore") != null)
//            proximoID = realm.where(ItemMedicaoArvore.class).max("cod_medicao_acaizeiro").intValue() + 1;
//        if(adultos == true){
//            itemMedicaoAcaizeiro.setQuant_adultos(1);
//        }
//        if(jovens == true){
//            itemMedicaoAcaizeiro.setQuant_jovens(1);
//        }
//        if(perfilhos == true){
//            itemMedicaoAcaizeiro.setQuant_perfilhos(1);
//        }
//        itemMedicaoAcaizeiro.setCod_medicao_acaizeiro(proximoID);
//        itemMedicaoAcaizeiro.setAcaizeiro(acaizeiro);
//        realm.commitTransaction();
//        realm.close();
//
//        return  itemMedicaoAcaizeiro;
//
//    }


    public ItemMedicaoAcaizeiro insertQuantidade( int cod_visita, Visita visita,
                                 boolean adulto, boolean jovem, boolean perfilho){

        ItemMedicaoArvore itemMedicaoArvore;
        //ItemMedicaoArvore itemMedicaoArvore = realm.where(ItemMedicaoArvore.class).equalTo("cod_medicao_arvore",cod_medicao_arvore).findFirst();
        ItemMedicaoAcaizeiro itemMedicaoAcaizeiroVisita = visita.getItemMedicaoAcaizeiro();

        if (itemMedicaoAcaizeiroVisita == null){
            VisitaDao visita1 = new VisitaDao();
            visita1.insertAcaizeiro(cod_visita,itemMedicaoAcaizeiroVisita = new ItemMedicaoAcaizeiroDao().insertItemMedicao(adulto,jovem,perfilho));
            Log.i("Item medicao acaiziero","nullo");
        } else {

            realm.beginTransaction();

            if (adulto == true) {
                //Number max = realm.where(ItemMedicaoArvore.class).max("quant_grossa");
                int max = itemMedicaoAcaizeiroVisita.getQuant_adultos();
                itemMedicaoAcaizeiroVisita.setQuant_adultos(max+1);
            }
            if (jovem == true) {
                //Number max = realm.where(ItemMedicaoArvore.class).max("quant_media");
                int max = itemMedicaoAcaizeiroVisita.getQuant_jovens();
                itemMedicaoAcaizeiroVisita.setQuant_jovens(max +1);
            }
            if (perfilho == true) {
                //Number max = realm.where(ItemMedicaoArvore.class).max("quant_fina");
                int max = itemMedicaoAcaizeiroVisita.getQuant_perfilhos();
                itemMedicaoAcaizeiroVisita.setQuant_perfilhos(max +1);
            }
            realm.commitTransaction();
            realm.close();
        }

        return  itemMedicaoAcaizeiroVisita;
    }

}
