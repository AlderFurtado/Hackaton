package com.example.alder.hackathonembrapa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.alder.hackathonembrapa.POJO.Local;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListaComunidadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comunidade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Realm realm = Realm.getDefaultInstance();

        RealmResults<Local> locais = realm.where(Local.class).findAll();
        ArrayList<String>  locals = new ArrayList<>();

        Log.i("Local:",locais.toString());

        for(int i =0; i < locais.size(); i++){
            Local local = realm.where(Local.class).equalTo("cod_local",i).findFirst();
            locals.add(local.getNome());
        }

        ListView lista = (ListView)findViewById(R.id.lv_comunidade);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(ListaComunidadeActivity.this,
                R.layout.support_simple_spinner_dropdown_item,locals);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

               Intent intent = new Intent(ListaComunidadeActivity.this,CadastroLocalidadeActivity.class);
               startActivity(intent);
            }
        });
    }

}
