package com.example.alder.hackathonembrapa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.util.Log;

import com.example.alder.hackathonembrapa.Models.Local;
import com.example.alder.hackathonembrapa.Models.Medicao;
import com.example.alder.hackathonembrapa.Models.Visita;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Realm realm = Realm.getDefaultInstance();

//        realm.beginTransaction();
//
//        Local local = realm.where(Local.class).equalTo("cod_local",1).findFirst();
//        Visita visita = realm.createObject(Visita.class);
//        visita.setCod_visita(1);
//        visita.setLocal(local);
//
//        realm.commitTransaction();

        Visita local2 = realm.where(Visita.class).equalTo("cod_visita",1).findFirst();
        Log.i("visita 1:","local da visita"+local2.getLocal().getNome()+" visita:"+local2.getCod_visita());



    }
}
