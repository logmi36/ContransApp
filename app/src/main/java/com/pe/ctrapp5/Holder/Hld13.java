package com.pe.ctrapp5.Holder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.R;


public class Hld13 extends RecyclerView.ViewHolder implements View.OnClickListener  {

    private RecyclerViewClickListener listener;

    public TextView ri13r01;
    public TextView ri13r02;
    public TextView ri13r03;
    public TextView ri13r04;
    public TextView ri13r05;
    public TextView ri13r06;

    public Hld13(View itemView, RecyclerViewClickListener _listener)
    {
        super(itemView);
        ri13r01 = (TextView) itemView.findViewById(R.id.ri13r01);
        ri13r02 = (TextView) itemView.findViewById(R.id.ri13r02);
        ri13r03 = (TextView) itemView.findViewById(R.id.ri13r03);
        ri13r04 = (TextView) itemView.findViewById(R.id.ri13r04);
        ri13r05 = (TextView) itemView.findViewById(R.id.ri13r05);
        ri13r06 = (TextView) itemView.findViewById(R.id.ri13r06);
        listener=_listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
    }

}
