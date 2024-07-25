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
import java.util.List;


import com.pe.ctrapp5.Holder.Hld02;
import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.Model.Obj02;
import com.pe.ctrapp5.R;



public class Dap02 extends RecyclerView.Adapter<Hld02> {


    private RecyclerViewClickListener listener;

    private Context context;
    private List<Obj02> items;

    onLongItemClickListener mOnLongItemClickListener;

    public Dap02(Context _context, List<Obj02> _items, RecyclerViewClickListener _listener)
    {
        this.context=_context;
        this.items=_items;
        this.listener=_listener;
    }

    @NonNull
    @Override
    public Hld02 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.ri03, parent, false);
        return new Hld02(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Hld02 holder, int position) {

        if(items!=null  )
        {
            String p1=items.get(position).getF03();
            String p2=items.get(position).getF20();
            String p3=items.get(position).getF09();
            String p4=items.get(position).getF15();
            String p5=items.get(position).getF02();
            String p6=items.get(position).getF16();


            holder.ri03r01.setText(p1);
            holder.ri03r02.setText(p2);
            holder.ri03r03.setText(p3);
            holder.ri03r04.setText(p4);
            holder.ri03r05.setText(p5);
            holder.ri03r06.setText(p6);


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