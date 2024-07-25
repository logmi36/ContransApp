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

import com.pe.ctrapp5.Holder.Hld09;
import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.Model.Obj09;
import com.pe.ctrapp5.R;

import java.util.List;



public class Dap09 extends RecyclerView.Adapter<Hld09> {


    private RecyclerViewClickListener listener;

    private Context context;
    private List<Obj09> items;

    onLongItemClickListener mOnLongItemClickListener;

    public Dap09(Context _context, List<Obj09> _items, RecyclerViewClickListener _listener)
    {
        this.context=_context;
        this.items=_items;
        this.listener=_listener;
    }

    @NonNull
    @Override
    public Hld09 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.ri09, parent, false);
        return new Hld09(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Hld09 holder, int position) {

        if(items!=null  )
        {
            String p1=items.get(position).getF03();
            String p2=items.get(position).getF04();
            String p3=items.get(position).getF07();

            String p7=items.get(position).getF12();

            byte[] dec= Base64.decode(p7, Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(dec, 0, dec.length);
            bmp=getRoundBitmap(bmp);
            holder.r9f1.setImageBitmap(bmp);

            holder.r9f2.setText(p1);
            holder.r9f3.setText(p2);
            holder.r9f4.setText(p3);


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
