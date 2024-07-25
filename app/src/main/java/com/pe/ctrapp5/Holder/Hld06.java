package com.pe.ctrapp5.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.R;

public class Hld06 extends RecyclerView.ViewHolder implements View.OnClickListener  {

    private RecyclerViewClickListener listener;


    public ImageView r6f1;
    public TextView r6f2;


    public Hld06(View itemView, RecyclerViewClickListener _listener)
    {
        super(itemView);
        r6f1 = (ImageView) itemView.findViewById(R.id.r6f1);
        r6f2 = (TextView) itemView.findViewById(R.id.r6f2);
        listener=_listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
    }

}
