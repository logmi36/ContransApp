package com.pe.ctrapp5.Adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pe.ctrapp5.Holder.Hld13;
import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.Model.Obj13;
import com.pe.ctrapp5.R;

import java.util.List;



public class Dap13 extends RecyclerView.Adapter<Hld13> {


    private RecyclerViewClickListener listener;

    private Context context;
    private List<Obj13> items;

    onLongItemClickListener mOnLongItemClickListener;

    public Dap13(Context _context, List<Obj13> _items, RecyclerViewClickListener _listener)
    {
        this.context=_context;
        this.items=_items;
        this.listener=_listener;
    }

    @NonNull
    @Override
    public Hld13 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.ri13, parent, false);
        return new Hld13(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Hld13 holder, int position) {

        if(items!=null  )
        {
            String p1=items.get(position).getF04() + "-" + items.get(position).getF05();
            String p2=items.get(position).getF01();
            String p3=items.get(position).getF03();
            String p4=items.get(position).getF11();
            String p5=items.get(position).getF10();
            String p6=items.get(position).getF02();


            holder.ri13r01.setText(p1);
            holder.ri13r02.setText(p2);
            holder.ri13r03.setText(p3);
            holder.ri13r04.setText(p4);
            holder.ri13r05.setText(p5);
            holder.ri13r06.setText(p6);


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



    public static Bitmap getRoundBitmap(Bitmap bitmap) {

        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());

        Bitmap bitmapRounded = Bitmap.createBitmap(min, min, bitmap.getConfig());

        Canvas canvas = new Canvas(bitmapRounded);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0.0f, 0.0f, min, min)), min/8, min/8, paint);

        return bitmapRounded;
    }




}