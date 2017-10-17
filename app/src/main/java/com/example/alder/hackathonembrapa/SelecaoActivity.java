package com.example.alder.hackathonembrapa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.alder.hackathonembrapa.Model.EspecieArvoreDao;
import com.example.alder.hackathonembrapa.Model.ItemMedicaoArvoreDao;
import com.example.alder.hackathonembrapa.Model.VisitaDao;
import com.example.alder.hackathonembrapa.POJO.EspecieArvore;
import com.example.alder.hackathonembrapa.POJO.ItemMedicaoArvore;
import com.example.alder.hackathonembrapa.POJO.Visita;

import io.realm.Realm;
import io.realm.RealmResults;

public class SelecaoActivity extends AppCompatActivity {

    EditText etNovaEspecie;
    RadioGroup rgArvore;
    RadioGroup rgPalmeira;
    Spinner spEspecie;
    RadioButton rbArvore;
    RadioButton rbPalmeira;
    RadioButton rbGrossa;
    RadioButton rbMedia;
    RadioButton rbFina;
    RadioButton rbAdulto;
    RadioButton rbJovem;
    Button btnGravarItem;
    String novaEspecie;
    Realm realm = Realm.getDefaultInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao);

        final int cod_visita = this.getIntent().getIntExtra("cod_visita",0);
        Log.i("cod_visita",cod_visita+"");

        rbArvore = (RadioButton)findViewById(R.id.rbArvore);
        rbPalmeira = (RadioButton)findViewById(R.id.rbPalmeira);
        rbGrossa = (RadioButton)findViewById(R.id.rbGrossa);
        rbMedia = (RadioButton)findViewById(R.id.rbMedia);
        rbFina = (RadioButton)findViewById(R.id.rbFina);
        rbJovem = (RadioButton)findViewById(R.id.rbJovem);
        rbAdulto = (RadioButton)findViewById(R.id.rbAdulto);
        spEspecie = (Spinner)findViewById(R.id.spEspecie);
        etNovaEspecie = (EditText)findViewById(R.id.etNovaEspecie);
        rgArvore = (RadioGroup)findViewById(R.id.rgArvore);
        rgPalmeira = (RadioGroup)findViewById(R.id.rgPalmeira);
        btnGravarItem = (Button)findViewById(R.id.btnGravaItem);

        rbArvore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbArvore.setVisibility(View.INVISIBLE);
                rbPalmeira.setVisibility(View.INVISIBLE);
                spEspecie.setVisibility(View.VISIBLE);
                etNovaEspecie.setVisibility(View.VISIBLE);
                rgArvore.setVisibility(View.VISIBLE);

                btnGravarItem.setVisibility(View.VISIBLE);
                btnGravarItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        novaEspecie = etNovaEspecie.getText().toString();
                        final EspecieArvoreDao especieArvoreDao = new EspecieArvoreDao();
                        EspecieArvore especieArvore;
                        RealmResults<EspecieArvore> especieArvores = realm.where(EspecieArvore.class).findAll();
                        Log.i("Especie Arvore:",especieArvores.toString());
                        boolean grossa = rbGrossa.isChecked();
                        boolean media = rbMedia.isChecked();
                        boolean fina = rbFina.isChecked();

                        if(especieArvoreDao.existEspecieArvore(novaEspecie) == false){


                            especieArvore = especieArvoreDao.insertEspecieArvore(novaEspecie);
                            VisitaDao visitadao = new VisitaDao();
                            ItemMedicaoArvoreDao itemMedicaoArvoreDao = new ItemMedicaoArvoreDao();
                            ItemMedicaoArvore itemMedicaoArvore = new ItemMedicaoArvore();
                            itemMedicaoArvore = itemMedicaoArvoreDao.insertItemMedicao(especieArvore,grossa,media,fina);
                            visitadao.insertItemEspecieArvore(cod_visita,itemMedicaoArvore);
                            Visita visita = realm.where(Visita.class).equalTo("cod_visita",cod_visita).findFirst();
                            RealmResults<EspecieArvore> especieArvore1 = realm.where(EspecieArvore.class).findAll();
                            Log.i("Visita",visita.toString());
                            for(int i = 0;i< visita.getItemMedicaoArvores().size();i++){
                                Log.i("VisitaitemmedicaoEXISTE",visita.getItemMedicaoArvores().get(i).toString());
                                Log.i("VisitaEspecies EXISTE",visita.getItemMedicaoArvores().get(i).getEspecieArvores().toString());
                            }
                            Log.i("Especies Arvores",especieArvore1.toString());
                            Log.i("Existe:","não");
                        }else{

                            especieArvore = especieArvoreDao.selectEspecieArvore(novaEspecie);
                            VisitaDao visitadao = new VisitaDao();

                            ItemMedicaoArvoreDao itemMedicaoArvoreDao = new ItemMedicaoArvoreDao();
                            Visita visita = realm.where(Visita.class).equalTo("cod_visita",cod_visita).findFirst();
                            int cod_medicao_arvore = itemMedicaoArvoreDao.selectItemMedicaoArvore(especieArvore,visita);
                            Log.i("cod_medicao_arvore",cod_medicao_arvore+"");

                            itemMedicaoArvoreDao.insertQuantidade(cod_medicao_arvore,cod_visita,visita,especieArvore,grossa,media,fina);

                            RealmResults<EspecieArvore> especieArvore1 = realm.where(EspecieArvore.class).findAll();
                            Log.i("Visita",visita.toString());

                            for(int i = 0;i< visita.getItemMedicaoArvores().size();i++){
                                Log.i("Visita item medicao",visita.getItemMedicaoArvores().get(i).toString());
                            }
                            Log.i("Especies Arvores",especieArvore1.toString());
                            Log.i("Existe:","sim");
                        }
                    }
                });
            }
        });

        rbPalmeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbArvore.setVisibility(View.INVISIBLE);
                rbPalmeira.setVisibility(View.INVISIBLE);
                spEspecie.setVisibility(View.VISIBLE);
                etNovaEspecie.setVisibility(View.VISIBLE);

                rgPalmeira.setVisibility(View.VISIBLE);
                btnGravarItem.setVisibility(View.VISIBLE);


            }
        });

        btnGravarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Botão:","funcionando");
                if(rbArvore.isChecked()){




                }else {

                }
            }
        });

    }

//    public void atualizarSpinner(){
//        int cod_visita = this.getIntent().getIntExtra("cod_visita",0);
//        Visita visita = realm.where(Visita.class).equalTo("cod_visita",cod_visita).findFirst();
//
//        ArrayAdapter<String> stringArrayAdapter =
//                fot(int i = 0; i < )
//                new ArrayAdapter<String>(SelecaoActivity.this,R.layout.support_simple_spinner_dropdown_item,especieArvores);
//    }
}
