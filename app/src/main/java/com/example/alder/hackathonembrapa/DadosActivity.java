package com.example.alder.hackathonembrapa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.alder.hackathonembrapa.POJO.ItemMedicaoAcaizeiro;
import com.example.alder.hackathonembrapa.POJO.ItemMedicaoArvore;
import com.example.alder.hackathonembrapa.POJO.ItemMedicaoPalmeira;
import com.example.alder.hackathonembrapa.POJO.Visita;

import io.realm.Realm;
import io.realm.RealmList;

public class DadosActivity extends AppCompatActivity {


    Realm realm = Realm.getDefaultInstance();
    int totalMedicaoArvore;
    int totalMedicaoPalmeira;
    int totalMedicaoAcaizeiro;
    TextView tvQuantArvore;
    TextView tvQuantPalmeira;
    TextView tvQuantAcaizeiro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);

        int cod_visita = this.getIntent().getIntExtra("cod_visita",0);

        tvQuantArvore = (TextView)findViewById(R.id.tvQuantArvore);
        tvQuantPalmeira = (TextView)findViewById(R.id.tvQuantPalmeiras);
        tvQuantAcaizeiro = (TextView)findViewById(R.id.tvQuantAcaizeiro);

        Visita visita = realm.where(Visita.class).equalTo("cod_visita",cod_visita).findFirst();

        RealmList<ItemMedicaoArvore> itemMedicaoArvores = visita.getItemMedicaoArvores();

        for(int i = 0;i < itemMedicaoArvores.size();i++){
            totalMedicaoArvore += itemMedicaoArvores.get(i).getQuant_fina();
            totalMedicaoArvore += itemMedicaoArvores.get(i).getQuant_media();
            totalMedicaoArvore += itemMedicaoArvores.get(i).getQuant_grossa();
        }

        RealmList<ItemMedicaoPalmeira> itemMedicaoPalmeiras = visita.getItemMedicaoPalmeiras();

        for (int i = 0; i < itemMedicaoPalmeiras.size(); i++){
            totalMedicaoPalmeira += itemMedicaoPalmeiras.get(i).getQuant_jovem();
            totalMedicaoPalmeira += itemMedicaoPalmeiras.get(i).getQuant_adulta();
        }

        ItemMedicaoAcaizeiro itemMedicaoAcaizeiros = visita.getItemMedicaoAcaizeiro();
        totalMedicaoAcaizeiro += itemMedicaoAcaizeiros.getQuant_jovens();
        totalMedicaoAcaizeiro += itemMedicaoAcaizeiros.getQuant_adultos();
        totalMedicaoAcaizeiro += itemMedicaoAcaizeiros.getQuant_perfilhos();

        tvQuantArvore.setText(totalMedicaoArvore+"");
        tvQuantPalmeira.setText(totalMedicaoPalmeira+"");
        tvQuantAcaizeiro.setText(totalMedicaoAcaizeiro+"");






    }
}
