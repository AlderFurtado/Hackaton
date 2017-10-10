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


//
        Realm realm = Realm.getDefaultInstance();
//
        realm.beginTransaction();

   Local local = realm.createObject(Local.class);
        local.setCod_local(2);
        local.setNome("Ribeirinho2");
        local.setLatitude(20000);
        local.setLongitude(200000);
        Local local2 = realm.createObject(Local.class);
        local2.setCod_local(3);
       local2.setNome("Ribeirinho2");
        local2.setLatitude(20000);
        local2.setLongitude(200000);
        Local local3 = realm.createObject(Local.class);
        local3.setCod_local(4);
        local3.setNome("Ribeirinho3");
        local3.setLatitude(30000);
        local3.setLongitude(300000);
        realm.commitTransaction();


        RealmResults<Local> localx = realm.where(Local.class).findAll();
        Log.i("local:",localx.toString());



    }
}
