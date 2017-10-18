package com.example.alder.hackathonembrapa.Model;

import com.example.alder.hackathonembrapa.POJO.EspecieArvore;
import com.example.alder.hackathonembrapa.POJO.EspeciePalmeira;
import com.example.alder.hackathonembrapa.POJO.ItemMedicaoArvore;
import com.example.alder.hackathonembrapa.POJO.ItemMedicaoPalmeira;
import com.example.alder.hackathonembrapa.POJO.Visita;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Alder on 16/10/2017.
 */

public class ItemMedicaoPalmeiraDao {
    Realm realm = Realm.getDefaultInstance();

    public ItemMedicaoPalmeira insertItemMedicao(EspeciePalmeira especiePalmeira
            , boolean jovem ,boolean adulta){

        realm.beginTransaction();
        ItemMedicaoPalmeira itemMedicaoPalmeira = realm.createObject(ItemMedicaoPalmeira.class);
        int proximoID = 1;
        if(realm.where(ItemMedicaoPalmeira.class).max("cod_medicao_palmeira") != null)
            proximoID = realm.where(ItemMedicaoPalmeira.class).max("cod_medicao_palmeira").intValue() + 1;
        if(jovem == true){
            itemMedicaoPalmeira.setQuant_jovem(1);
        }
        if(adulta == true){
            itemMedicaoPalmeira.setQuant_adulta(1);
        }

        itemMedicaoPalmeira.setCod_medicao_palmeira(proximoID);
        itemMedicaoPalmeira.setEspeciePalmeira(especiePalmeira);
        realm.commitTransaction();
        realm.close();

        return  itemMedicaoPalmeira;

    }

    public void insertQuantidade(int cod_medicao_palmeira, int cod_visita, Visita visita, EspeciePalmeira especiePalmeira,
                                 boolean jovem, boolean adulto){
        ItemMedicaoPalmeira itemMedicaoPalmeira;
        //ItemMedicaoArvore itemMedicaoArvore = realm.where(ItemMedicaoArvore.class).equalTo("cod_medicao_arvore",cod_medicao_arvore).findFirst();
        RealmList<ItemMedicaoPalmeira> itemMedicaoPalmeirasVisita = visita.getItemMedicaoPalmeiras();
        itemMedicaoPalmeira = itemMedicaoPalmeirasVisita.where().equalTo("cod_medicao_palmeira",cod_medicao_palmeira).findFirst();
        if (itemMedicaoPalmeira == null){
            VisitaDao visita1 = new VisitaDao();
            visita1.insertItemEspeciePalmeira(cod_visita,new ItemMedicaoPalmeiraDao().insertItemMedicao(especiePalmeira,jovem,adulto));

        } else {

            realm.beginTransaction();

            if (jovem == true) {
                //Number max = realm.where(ItemMedicaoArvore.class).max("quant_grossa");
                int max = itemMedicaoPalmeira.getQuant_jovem();
                itemMedicaoPalmeira.setQuant_jovem(max + 1);
            }
            if (adulto == true) {
                //Number max = realm.where(ItemMedicaoArvore.class).max("quant_media");
                int max = itemMedicaoPalmeira.getQuant_adulta();
                itemMedicaoPalmeira.setQuant_adulta(max + 1);
            }

            realm.commitTransaction();
            realm.close();
        }


    }

    public int selectItemMedicaoPalmeira(EspeciePalmeira especiePalmeira, Visita visita){
        int cod_medicao_palmeira = 0;

        RealmResults<ItemMedicaoPalmeira> itemMedicaoPalmeiras = realm.where(ItemMedicaoPalmeira.class).findAll();

        for (int i = 0; i < visita.getItemMedicaoPalmeiras().size();i++){


            if (visita.getItemMedicaoPalmeiras().get(i).getEspeciePalmeira().getNome_especie_palmeira().toString().equals(especiePalmeira.getNome_especie_palmeira())){
                cod_medicao_palmeira = visita.getItemMedicaoArvores().get(i).getCod_medicao_arvore();
                break;
            }else{

            }
        }

        return cod_medicao_palmeira;
    }
}
