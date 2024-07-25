package com.pe.ctrapp5.Holder;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.R;

public class Hld02 extends RecyclerView.ViewHolder implements View.OnClickListener  {

    private RecyclerViewClickListener listener;

    public TextView ri03r01;
    public TextView ri03r02;
    public TextView ri03r03;
    public TextView ri03r04;
    public TextView ri03r05;
    public TextView ri03r06;

    public Hld02(View itemView, RecyclerViewClickListener _listener)
    {
        super(itemView);
        ri03r01 = (TextView) itemView.findViewById(R.id.ri03r01);
        ri03r02 = (TextView) itemView.findViewById(R.id.ri03r02);
        ri03r03 = (TextView) itemView.findViewById(R.id.ri03r03);
        ri03r04 = (TextView) itemView.findViewById(R.id.ri03r04);
        ri03r05 = (TextView) itemView.findViewById(R.id.ri03r05);
        ri03r06 = (TextView) itemView.findViewById(R.id.ri03r06);
        listener=_listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
    }

}
