package com.example.ORMLiteOneToMany;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.ORMLiteOneToMany.access.DatabaseHelper;
import com.example.ORMLiteOneToMany.model.Client;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class MyActivity extends Activity implements ListeClientFragment.ListeClientFragmentListener{

    public static final int  REQUEST_CREER_CLIENT = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }

    @Override
    public void onListeClientItemClick(Client client, int position) {
        DetailsClientFragment dcf =
                (DetailsClientFragment) getFragmentManager().findFragmentById(R.id.detailsFragment);
        dcf.updateDetails(client);

    }

    public void clickCreerClient(View view) {
        Intent intent = new Intent(this, CreerClientActivity.class);
        startActivityForResult(intent, REQUEST_CREER_CLIENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        /*si l'activité de création de client renvoie "RESULT_OK"
        * alors on de mande au fragment qui contient la liste de se mettre à jour*/
        if(requestCode == REQUEST_CREER_CLIENT && resultCode == RESULT_OK){
            FragmentManager fm = getFragmentManager();
            ListeClientFragment lcf =
                    (ListeClientFragment) fm.findFragmentById(R.id.listeFragment);

            lcf.updateListClient();
        }
    }
}
