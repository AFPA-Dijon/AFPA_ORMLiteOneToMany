package com.example.ORMLiteOneToMany;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.ORMLiteOneToMany.access.DatabaseHelper;
import com.example.ORMLiteOneToMany.model.Client;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by sam on 03/02/2016.
 */
public class ListeClientFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListeClientFragmentListener listener;
    private DatabaseHelper dbHelper;
    private ArrayAdapter<Client> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_layout, container, false);
        this.dbHelper = new DatabaseHelper(getActivity());
        try {
            Dao<Client, Integer> daoClient = dbHelper.getDaoClient();
            this.adapter = new ArrayAdapter<Client>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    daoClient.queryForAll()
            );
            ListView listView = (ListView) view.findViewById(R.id.list);
            listView.setAdapter(this.adapter);
            listView.setOnItemClickListener(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.listener.onListeClientItemClick(this.adapter.getItem(position), position);
    }

    public interface ListeClientFragmentListener{
        public void onListeClientItemClick(Client client, int position);
    }

    @Override
    public void onAttach(Activity activity) {

        if(activity instanceof ListeClientFragmentListener){
            this.listener = (ListeClientFragmentListener) activity;
        } else {
            throw new ClassCastException("L'activité parente doit implémenter ListeClientFragmentListener");
        }
        super.onAttach(activity);
    }

    public void updateListClient(){
        try {
            adapter.clear();
            adapter.addAll(dbHelper.getDaoClient().queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
