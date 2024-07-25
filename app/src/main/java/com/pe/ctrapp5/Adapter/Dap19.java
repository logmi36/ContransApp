package com.pe.ctrapp5.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pe.ctrapp5.Holder.Hld19;
import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.Model.Obj19;
import com.pe.ctrapp5.R;

import java.util.List;


public class Dap19 extends RecyclerView.Adapter<Hld19> {


    private RecyclerViewClickListener listener;

    private Context context;
    private List<Obj19> items;

    onLongItemClickListener mOnLongItemClickListener;

    public Dap19(Context _context, List<Obj19> _items, RecyclerViewClickListener _listener)
    {
        this.context=_context;
        this.items=_items;
        this.listener=_listener;
    }

    @NonNull
    @Override
    public Hld19 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.ri15, parent, false);
        return new Hld19(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Hld19 holder, int position) {

        if(items!=null  )
        {

            String p1=(items.get(position).getF01()==null)? "":items.get(position).getF01();
            String p2=(items.get(position).getF03()==null)? "":items.get(position).getF03();
            String p3=(items.get(position).getF04()==null)? "":items.get(position).getF04();
            String p4=(items.get(position).getF05()==null)? "":items.get(position).getF05();

            holder.ri15r01.setText(p2);
            holder.ri15r02.setText(p1);
            holder.ri15r03.setText(p3);

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnLongItemClickListener != null) {
                        mOnLongItemClickListener.ItemLongClicked(v, position);
                    }
                    return true;
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return items.size();
    }



    public interface onLongItemClickListener {
        void ItemLongClicked(View v, int position);
    }

    public void setOnLongItemClickListener(onLongItemClickListener onLongItemClickListener) {
        mOnLongItemClickListener = onLongItemClickListener;
    }





}
