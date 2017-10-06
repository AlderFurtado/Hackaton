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


//        realm.executeTransaction(new Realm.Transaction() {
//
//            @Override
//            public void execute(Realm realm) {
//                Visita visita = realm.where(Visita.class).equalTo("cod_visita",1).findFirst();
//
//                Medicao medicao = realm.createObject(Medicao.class);
//                medicao.setCod_medicao(1);
//                medicao.setVisita(visita);
//
//            }
//        });

        RealmResults<Visita> visita = realm.where(Visita.class).findAll();
        Log.i("visita 1:",visita.toString());



    }
}
