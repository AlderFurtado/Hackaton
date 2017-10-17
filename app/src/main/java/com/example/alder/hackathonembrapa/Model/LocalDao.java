package com.example.alder.hackathonembrapa.Model;

import android.util.Log;

import com.example.alder.hackathonembrapa.POJO.Local;
import com.example.alder.hackathonembrapa.POJO.Visita;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Alder on 14/10/2017.
 */

public class LocalDao {
    Realm realm = Realm.getDefaultInstance();
    public void InsertLocal(String nome, double latitude , double longitude ){




            realm.beginTransaction();
                Local local = realm.createObject(Local.class);
                int proximoID = 1;
                if(realm.where(Local.class).max("cod_local") != null)
                    proximoID = realm.where(Local.class).max("cod_local").intValue() + 1;

                local.setCod_local(proximoID);
                local.setNome(nome);
                local.setLatitude(latitude);
                local.setLongitude(longitude);
            realm.commitTransaction();
            realm.close();

        RealmResults<Local> local1 = realm.where(Local.class).findAll();
        Log.i("Local:",""+local1 );
    }

    public void deleteLocal(int cod_local){


        realm.beginTransaction();

        Local local = realm.where(Local.class).equalTo("cod_local",cod_local).findFirst();

        local.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    public void insertVisita(Visita visita,int cod_local){

        realm.beginTransaction();

        Local local = realm.where(Local.class).equalTo("cod_local",cod_local).findFirst();
        local.setVisitas(visita);
        realm.commitTransaction();
        realm.close();
    }
}
