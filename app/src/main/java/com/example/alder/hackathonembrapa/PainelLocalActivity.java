package com.example.alder.hackathonembrapa;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alder.hackathonembrapa.Model.LocalDao;
import com.example.alder.hackathonembrapa.Model.VisitaDao;
import com.example.alder.hackathonembrapa.POJO.Local;
import com.example.alder.hackathonembrapa.POJO.Visita;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class PainelLocalActivity extends AppCompatActivity {

    ArrayList<String> visistasS;
    TextView tvLatitude;
    TextView tvLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel_local);

        TextView tvNomeLocal = (TextView)findViewById(R.id.tvNomeLocal);
        tvLatitude = (TextView)findViewById(R.id.tvLatitude);
        tvLongitude = (TextView)findViewById(R.id.tvLongitude);
        tvLatitude = (TextView)findViewById(R.id.tvLatitude);

        final ListView lvVisistas = (ListView)findViewById(R.id.lvVisitas);

        final int cod_local = this.getIntent().getIntExtra("cod_local",0);
        Log.i("cod_local: ",cod_local+"");

        final Realm realm = Realm.getDefaultInstance();

        final Local local = realm.where(Local.class).equalTo("cod_local",cod_local).findFirst();

        tvNomeLocal.setText(local.getNome().toString());
        tvLatitude.setText(local.getLatitude()+"");
        tvLongitude.setText(local.getLongitude()+"");


        visistasS = new ArrayList<>();
        RealmList<Visita> visitas = local.getVisitas();

        for(int i = 0; i < visitas.size(); i++){

            visistasS.add(visitas.get(i).getData());
        }

        atualizarLista(lvVisistas,local);
        Button btnNovaMedicao = (Button)findViewById(R.id.btnNovaMeidicao);

        btnNovaMedicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(PainelLocalActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.nova_medicao_popup,null);

                final DatePicker datePicker = (DatePicker)mView.findViewById(R.id.datePicker);
                Button btnData = (Button) mView.findViewById(R.id.btnData);

                Log.i("Visitas",local.getVisitas().toString());

                final int day = datePicker.getDayOfMonth();
                final int mounth = datePicker.getMonth();
                final int year = datePicker.getYear();
                mBuilder.setView(mView);
                final AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();
                btnData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        VisitaDao visitaDao = new VisitaDao();
                        Visita visita = visitaDao.insertVisita(day+"-"+mounth+"-"+year);
                        LocalDao localDao = new LocalDao();
                        localDao.insertVisita(visita,cod_local);
                        atualizarLista(lvVisistas,local);
                        alertDialog.dismiss();

                    }
                });



            }
        });



        lvVisistas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(PainelLocalActivity.this,SelecaoActivity.class);
                RealmList<Visita> visita = local.getVisitas();

                Log.i("Visita escolhida",visita.get(i).toString());
                intent.putExtra("cod_visita",visita.get(i).getCod_visita());

                startActivity(intent);
            }
        });

       lvVisistas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               RealmList<Visita> visita = local.getVisitas();
               realm.beginTransaction();
               visita.remove(i);
               realm.commitTransaction();
               realm.close();
               atualizarLista(lvVisistas,local);

               return true;
           }
       });
    }

    public void atualizarLista(ListView lvVisistas,Local local){

        ArrayAdapter<String> dataArrayAdapter = new ArrayAdapter<String>(PainelLocalActivity.this,
                R.layout.support_simple_spinner_dropdown_item,visistasS);

        lvVisistas.setAdapter(dataArrayAdapter);

        dataArrayAdapter.notifyDataSetChanged();
    }
}
