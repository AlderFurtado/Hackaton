package com.example.alder.hackathonembrapa;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alder.hackathonembrapa.Model.LocalDao;
import com.example.alder.hackathonembrapa.POJO.Local;

import io.realm.Realm;

public class CadastroLocalidadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_localidade);

        Button btnCadastraComunidade = (Button) findViewById(R.id.btnCadastrarComunidade);

        final EditText etNome = (EditText)findViewById(R.id.etNome);
        final EditText etLatitude = (EditText)findViewById(R.id.etLatitude);
        final EditText etLongitude = (EditText)findViewById(R.id.etLongitude);

        btnCadastraComunidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalDao localDao = new LocalDao();
                Local local = new Local();
                local.setNome(etNome.getText().toString());
                local.setLatitude(Double.parseDouble(etLatitude.getText().toString()));
                local.setLongitude(Double.parseDouble(etLongitude.getText().toString()));
                localDao.InsertLocal(local.getNome(),local.getLatitude(),local.getLongitude());
                Intent intent = new Intent(CadastroLocalidadeActivity.this,ListaComunidadeActivity.class);
                startActivity(intent);
                finish();
            }

        });


    }
}
