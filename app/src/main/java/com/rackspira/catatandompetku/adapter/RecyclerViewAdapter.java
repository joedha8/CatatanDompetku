package com.rackspira.catatandompetku.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rackspira.catatandompetku.R;

/**
 * Created by WIN 10 on 08/12/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.ViewHolder {
    TextView keterangan1,nominal;
    ImageView simbol;

    public RecyclerViewAdapter(View itemView){
        super(itemView);

        keterangan1 = (TextView)itemView.findViewById(R.id.keterangan1);
        nominal = (TextView)itemView.findViewById(R.id.nominal);
        simbol = (ImageView)itemView.findViewById(R.id.simbol);
    }
}
