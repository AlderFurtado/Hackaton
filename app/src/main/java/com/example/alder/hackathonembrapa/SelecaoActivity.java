package com.example.alder.hackathonembrapa;

import android.content.Intent;
import android.support.design.widget.Snackbar;
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
import com.example.alder.hackathonembrapa.Model.EspeciePalmeiraDao;
import com.example.alder.hackathonembrapa.Model.ItemMedicaoAcaizeiroDao;
import com.example.alder.hackathonembrapa.Model.ItemMedicaoArvoreDao;
import com.example.alder.hackathonembrapa.Model.ItemMedicaoPalmeiraDao;
import com.example.alder.hackathonembrapa.Model.VisitaDao;
import com.example.alder.hackathonembrapa.POJO.Acaizeiro;
import com.example.alder.hackathonembrapa.POJO.EspecieArvore;
import com.example.alder.hackathonembrapa.POJO.EspeciePalmeira;
import com.example.alder.hackathonembrapa.POJO.ItemMedicaoAcaizeiro;
import com.example.alder.hackathonembrapa.POJO.ItemMedicaoArvore;
import com.example.alder.hackathonembrapa.POJO.ItemMedicaoPalmeira;
import com.example.alder.hackathonembrapa.POJO.Visita;

import io.realm.Realm;
import io.realm.RealmResults;

public class SelecaoActivity extends AppCompatActivity {

    EditText etNovaEspecie;
    RadioGroup rgArvore;
    RadioGroup rgPalmeira;
    RadioGroup rgAcaizeiro;
    Spinner spEspecie;
    RadioButton rbArvore;
    RadioButton rbPalmeira;
    RadioButton rbAcaizeiro;
    RadioButton rbGrossa;
    RadioButton rbMedia;
    RadioButton rbFina;
    RadioButton rbAdulto;
    RadioButton rbJovem;
    RadioButton rbAdultoA;
    RadioButton rbJovemA;
    RadioButton rbPerfilhoA;

    Button btnGravarItem;
    Button btnVerDados;
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
        rbAcaizeiro = (RadioButton)findViewById(R.id.rbAcaizeiro);
        rbGrossa = (RadioButton)findViewById(R.id.rbGrossa);
        rbMedia = (RadioButton)findViewById(R.id.rbMedia);
        rbFina = (RadioButton)findViewById(R.id.rbFina);
        rbJovem = (RadioButton)findViewById(R.id.rbJovem);
        rbAdulto = (RadioButton)findViewById(R.id.rbAdulto);
        rbAdultoA  =(RadioButton)findViewById(R.id.rbAdultoA);
        rbJovemA  =(RadioButton)findViewById(R.id.rbJovemA);
        rbPerfilhoA = (RadioButton)findViewById(R.id.rbPerfilho);

        etNovaEspecie = (EditText)findViewById(R.id.etNovaEspecie);
        rgAcaizeiro = (RadioGroup)findViewById(R.id.rgAcaizeiro);
        rgArvore = (RadioGroup)findViewById(R.id.rgArvore);
        rgPalmeira = (RadioGroup)findViewById(R.id.rgPalmeira);
        btnGravarItem = (Button)findViewById(R.id.btnGravaItem);
        btnVerDados = (Button)findViewById(R.id.btnVerDados);

        rbArvore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                rbArvore.setVisibility(View.INVISIBLE);
//                rbPalmeira.setVisibility(View.INVISIBLE);

                etNovaEspecie.setVisibility(View.VISIBLE);
                rgArvore.setVisibility(View.VISIBLE);
                rgPalmeira.setVisibility(View.INVISIBLE);
                rgAcaizeiro.setVisibility(View.INVISIBLE);

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

                        Snackbar.make(view, "Dicotiledônaeas adicionada.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
            }
        });

        rbPalmeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                rbArvore.setVisibility(View.INVISIBLE);
//                rbPalmeira.setVisibility(View.INVISIBLE);

                etNovaEspecie.setVisibility(View.VISIBLE);

                rgPalmeira.setVisibility(View.VISIBLE);
                rgArvore.setVisibility(View.INVISIBLE);
                rgAcaizeiro.setVisibility(View.INVISIBLE);
                btnGravarItem.setVisibility(View.VISIBLE);
                btnGravarItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        novaEspecie = etNovaEspecie.getText().toString();
                        final EspeciePalmeiraDao especiePalmeiraDao = new EspeciePalmeiraDao();
                        EspeciePalmeira especiePalmeira;
                        RealmResults<EspeciePalmeira> especiePalmeiras = realm.where(EspeciePalmeira.class).findAll();
                        Log.i("Especie Arvore:",especiePalmeiras.toString());
                        boolean jovem = rbJovem.isChecked();
                        boolean adulto = rbAdulto.isChecked();


                        if(especiePalmeiraDao.existEspeciePalmeira(novaEspecie) == false){


                            especiePalmeira = especiePalmeiraDao.insertEspeciePalmeira(novaEspecie);
                            VisitaDao visitadao = new VisitaDao();
                            ItemMedicaoPalmeiraDao itemMedicaoPalmeiraDao = new ItemMedicaoPalmeiraDao();
                            ItemMedicaoPalmeira itemMedicaoPalmeira = new ItemMedicaoPalmeira();
                            itemMedicaoPalmeira = itemMedicaoPalmeiraDao.insertItemMedicao(especiePalmeira,jovem,adulto);
                            visitadao.insertItemEspeciePalmeira(cod_visita,itemMedicaoPalmeira);
                            Visita visita = realm.where(Visita.class).equalTo("cod_visita",cod_visita).findFirst();
                            RealmResults<EspeciePalmeira> especiePalmeiras1 = realm.where(EspeciePalmeira.class).findAll();
                            Log.i("Visita",visita.toString());
                            for(int i = 0;i< visita.getItemMedicaoPalmeiras().size();i++){
                                Log.i("VisitaitemmedicaoEXISTE",visita.getItemMedicaoPalmeiras().get(i).toString());
                                Log.i("VisitaEspecies EXISTE",visita.getItemMedicaoPalmeiras().get(i).getEspeciePalmeira().toString());
                            }
                            Log.i("Especies Arvores",especiePalmeiras1.toString());
                            Log.i("Existe:","não");
                        }else{

                            especiePalmeira = especiePalmeiraDao.selectEspeciePalmeira(novaEspecie);
                            VisitaDao visitadao = new VisitaDao();

                            ItemMedicaoPalmeiraDao itemMedicaoPalmeiraDao = new ItemMedicaoPalmeiraDao();
                            Visita visita = realm.where(Visita.class).equalTo("cod_visita",cod_visita).findFirst();
                            int cod_medicao_palmeira = itemMedicaoPalmeiraDao.selectItemMedicaoPalmeira(especiePalmeira,visita);
                            Log.i("cod_medicao_arvore",cod_medicao_palmeira+"");

                            itemMedicaoPalmeiraDao.insertQuantidade(cod_medicao_palmeira,cod_visita,visita,especiePalmeira,jovem,adulto );

                            RealmResults<EspeciePalmeira> especiePalmeiras1 = realm.where(EspeciePalmeira.class).findAll();
                            Log.i("Visita",visita.toString());

                            for(int i = 0;i< visita.getItemMedicaoPalmeiras().size();i++){
                                Log.i("Visita item medicao",visita.getItemMedicaoPalmeiras().get(i).toString());
                            }
                            Log.i("Especies Arvores",especiePalmeiras1.toString());
                            Log.i("Existe:","sim");
                        }

                        Snackbar.make(view, "Palmeira adicionada.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                });


            }
        });

        rbAcaizeiro.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                       etNovaEspecie.setVisibility(View.VISIBLE);

                       rgPalmeira.setVisibility(View.INVISIBLE);
                       rgArvore.setVisibility(View.INVISIBLE);
                       rgAcaizeiro.setVisibility(View.VISIBLE);
                       btnGravarItem.setVisibility(View.VISIBLE);
                       btnGravarItem.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               boolean adulto = rbAdultoA.isChecked();
                               boolean jovem = rbJovemA.isChecked();
                               RealmResults<Acaizeiro> acaizeiros = realm.where(Acaizeiro.class).findAll();
                            Log.i("Acaizeiros",acaizeiros.toString());
                               boolean perfilho = rbPerfilhoA.isChecked();
                               VisitaDao visitaDao = new VisitaDao();
                               Visita visita = realm.where(Visita.class).equalTo("cod_visita",cod_visita).findFirst();

                               ItemMedicaoAcaizeiroDao itemMedicaoAcaizeiroDao = new ItemMedicaoAcaizeiroDao();

                               visitaDao.insertAcaizeiro(cod_visita,itemMedicaoAcaizeiroDao.insertQuantidade(cod_visita,visita,adulto,jovem,perfilho));


                               Log.i("Visita",visita.toString());


                               Log.i("Visita acaizeiro",visita.getItemMedicaoAcaizeiro().toString());

                               Snackbar.make(view, "Açaizeiro adicionado.", Snackbar.LENGTH_LONG)
                                       .setAction("Action", null).show();

                           }
                       });

                   }
               });

        btnVerDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelecaoActivity.this,DadosActivity.class);
                intent.putExtra("cod_visita",cod_visita);
                startActivity(intent);
            }
        });

//    public void atualizarSpinner(){
//        int cod_visita = this.getIntent().getIntExtra("cod_visita",0);
//        Visita visita = realm.where(Visita.class).equalTo("cod_visita",cod_visita).findFirst();
//
//        ArrayAdapter<String> stringArrayAdapter =
//                fot(int i = 0; i < )
//                new ArrayAdapter<String>(SelecaoActivity.this,R.layout.support_simple_spinner_dropdown_item,especieArvores);
//    }

    }
    }