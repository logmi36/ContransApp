package com.pe.ctrapp5.Holder;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.R;


public class Hld19 extends RecyclerView.ViewHolder implements View.OnClickListener  {

    //notificaciones
    private RecyclerViewClickListener listener;

    public TextView ri15r01;
    public TextView ri15r02;
    public TextView ri15r03;

    public Hld19(View itemView, RecyclerViewClickListener _listener)
    {
        super(itemView);
        ri15r01 = (TextView) itemView.findViewById(R.id.ri15r01);
        ri15r02 = (TextView) itemView.findViewById(R.id.ri15r02);
        ri15r03 = (TextView) itemView.findViewById(R.id.ri15r03);
        listener=_listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
    }

}
