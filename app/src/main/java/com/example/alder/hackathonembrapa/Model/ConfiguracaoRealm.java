package com.example.alder.hackathonembrapa.Model;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Alder on 05/10/2017.
 */

public class ConfiguracaoRealm  extends android.app.Application{
    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();

//        Realm.deleteRealm(config);
        Realm.setDefaultConfiguration(config);

    }
}
