package com.example.ORMLiteOneToMany;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ORMLiteOneToMany.model.Client;

/**
 * Created by sam on 03/02/2016.
 */
public class DetailsClientFragment extends Fragment{

    private TextView tvNom, tvCommercial;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.details_fragment_layout, container, false);
        this.tvNom = (TextView) view.findViewById(R.id.tvNom);
        this.tvCommercial = (TextView) view.findViewById(R.id.tvCommercial);
        return view;
    }

    public void updateDetails(Client c){
        if(c != null){
            tvNom.setText(c.getNom());
            tvCommercial.setText(c.getCommercial().toString());
        }
    }
}
