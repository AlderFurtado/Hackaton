package com.example.alder.hackathonembrapa;

import android.Manifest;
import android.content.Context;
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
import android.widget.Toast;

public class CadastroLocalidadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_localidade);

        Button btnMinhaLocalizacao = (Button) findViewById(R.id.btn_minhaLocalizacao);



        btnMinhaLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GPS gps = new GPS(CadastroLocalidadeActivity.this);
                double lat = gps.getLatitude();
                double log = gps.getLongitude();
                Toast.makeText(CadastroLocalidadeActivity.this,""+lat+log,Toast.LENGTH_SHORT).show();
            }
        });


    }
}
