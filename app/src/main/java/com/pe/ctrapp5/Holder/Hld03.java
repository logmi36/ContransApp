package com.pe.ctrapp5.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.R;

public class Hld03 extends RecyclerView.ViewHolder implements View.OnClickListener  {

    private RecyclerViewClickListener listener;


    public ImageView r4f1;
    public TextView r4f2;
    public TextView r4f3;
    public TextView r4f4;


    public Hld03(View itemView, RecyclerViewClickListener _listener)
    {
        super(itemView);
        r4f1 = (ImageView) itemView.findViewById(R.id.r4f1);
        r4f2 = (TextView) itemView.findViewById(R.id.r4f2);
        r4f3 = (TextView) itemView.findViewById(R.id.r4f3);
        r4f4 = (TextView) itemView.findViewById(R.id.r4f4);
        listener=_listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
    }

}
