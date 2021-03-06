package com.example.alder.hackathonembrapa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.alder.hackathonembrapa.Model.LocalDao;
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

        final Realm realm = Realm.getDefaultInstance();

        final RealmResults<Local> locais = realm.where(Local.class).findAll();
        final ArrayList<String>  locals = new ArrayList<>();



        Number min = realm.where(Local.class).min("cod_local");
        if(min == null){
            min = 1;
        }
        Log.i("minimo: ",min.intValue()+
                "\nLocais "+ locais);

        for(int i = 0; i < locais.size(); i++){
            Local local = realm.where(Local.class).equalTo("cod_local",min.intValue()+i).findFirst();
            locals.add(local.getNome());
        }

        final ListView lista = (ListView)findViewById(R.id.lv_comunidade);

        final ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(ListaComunidadeActivity.this,
                R.layout.support_simple_spinner_dropdown_item,locals);

        lista.setAdapter(stringArrayAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

               Intent intent = new Intent(ListaComunidadeActivity.this,CadastroLocalidadeActivity.class);
               startActivity(intent);
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ListaComunidadeActivity.this,PainelLocalActivity.class);
                Number min = realm.where(Local.class).min("cod_local");
                intent.putExtra("cod_local",min.intValue()+i);

                startActivity(intent);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                locals.remove(i);
                LocalDao localDao = new LocalDao();
                Number min = realm.where(Local.class).min("cod_local");
                localDao.deleteLocal(min.intValue()+i);
                stringArrayAdapter.notifyDataSetChanged();
                return true;
            }
        });

    }

}
