package com.pe.ctrapp5.Holder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.R;

public class Hld11 extends RecyclerView.ViewHolder implements View.OnClickListener  {

    private RecyclerViewClickListener listener;


    public ImageView r11f1;
    public TextView r11f2;


    public Hld11(View itemView, RecyclerViewClickListener _listener)
    {
        super(itemView);
        r11f1 = (ImageView) itemView.findViewById(R.id.r11f1);
        r11f2 = (TextView) itemView.findViewById(R.id.r11f2);
        listener=_listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
    }

}
