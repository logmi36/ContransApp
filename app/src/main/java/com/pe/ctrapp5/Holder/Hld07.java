package com.pe.ctrapp5.Holder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.R;


public class Hld07 extends RecyclerView.ViewHolder implements View.OnClickListener  {

    private RecyclerViewClickListener listener;


    public ImageView r7f1;
    public TextView r7f2;
    public TextView r7f3;
    public TextView r7f4;


    public Hld07(View itemView, RecyclerViewClickListener _listener)
    {
        super(itemView);
        r7f1 = (ImageView) itemView.findViewById(R.id.r7f1);
        r7f2 = (TextView) itemView.findViewById(R.id.r7f2);
        r7f3 = (TextView) itemView.findViewById(R.id.r7f3);
        r7f4 = (TextView) itemView.findViewById(R.id.r7f4);
        listener=_listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
    }

}
