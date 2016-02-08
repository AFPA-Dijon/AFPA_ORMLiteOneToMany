package com.example.ORMLiteOneToMany;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.ORMLiteOneToMany.access.DatabaseHelper;
import com.example.ORMLiteOneToMany.model.Client;
import com.example.ORMLiteOneToMany.model.Commercial;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by sam on 04/02/2016.
 */
public class CreerClientActivity extends Activity {


    private DatabaseHelper dbhelper;
    private Spinner spinner;
    private ArrayAdapter<Commercial> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_client_activity_layout);
        this.dbhelper = new DatabaseHelper(this);

        this.spinner = (Spinner) findViewById(R.id.spinnerCommercial);
        try {
            Dao<Commercial, Integer> daoCommercial = dbhelper.getDaoCommercial();
            this.adapter = new ArrayAdapter<Commercial>(
                    this,
                    android.R.layout.simple_spinner_item,
                    daoCommercial.queryForAll()
            );
            this.spinner.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    public void clickOK(View view) {

        EditText etNom = (EditText) findViewById(R.id.etNom);

        Client c = new Client(
                etNom.getText().toString(),
                this.adapter.getItem(this.spinner.getSelectedItemPosition())
        );
        try {
            this.dbhelper.getDaoClient().create(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setResult(Activity.RESULT_OK);
        finish();
    }














}
