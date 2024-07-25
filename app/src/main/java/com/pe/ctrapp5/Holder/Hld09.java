package com.pe.ctrapp5.Holder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.R;


public class Hld09 extends RecyclerView.ViewHolder implements View.OnClickListener  {

    private RecyclerViewClickListener listener;


    public ImageView r9f1;
    public TextView r9f2;
    public TextView r9f3;
    public TextView r9f4;


    public Hld09(View itemView, RecyclerViewClickListener _listener)
    {
        super(itemView);
        r9f1 = (ImageView) itemView.findViewById(R.id.r9f1);
        r9f2 = (TextView) itemView.findViewById(R.id.r9f2);
        r9f3 = (TextView) itemView.findViewById(R.id.r9f3);
        r9f4 = (TextView) itemView.findViewById(R.id.r9f4);
        listener=_listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
    }

}
