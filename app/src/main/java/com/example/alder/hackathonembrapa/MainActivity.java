package com.example.alder.hackathonembrapa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.alder.hackathonembrapa.POJO.Local;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//
//        Realm realm = Realm.getDefaultInstance();
//
//        realm.beginTransaction();
//
//
//        RealmResults<Local> localx = realm.where(Local.class).findAll();
//        Log.i("local:",localx.toString());
//        realm.commitTransaction();






    }
}
