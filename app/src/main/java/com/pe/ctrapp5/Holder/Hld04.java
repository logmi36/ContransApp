package com.pe.ctrapp5.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.R;


public class Hld04 extends RecyclerView.ViewHolder implements View.OnClickListener  {

    private RecyclerViewClickListener listener;


    public ImageView r5f1;
    public TextView r5f2;
    public TextView r5f3;
    public TextView r5f4;


    public Hld04(View itemView, RecyclerViewClickListener _listener)
    {
        super(itemView);
        r5f1 = (ImageView) itemView.findViewById(R.id.r5f1);
        r5f2 = (TextView) itemView.findViewById(R.id.r5f2);
        r5f3 = (TextView) itemView.findViewById(R.id.r5f3);
        r5f4 = (TextView) itemView.findViewById(R.id.r5f4);
        listener=_listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
    }

}
