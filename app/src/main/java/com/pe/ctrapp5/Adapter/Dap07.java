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

import com.pe.ctrapp5.Holder.Hld07;
import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.Model.Obj07;
import com.pe.ctrapp5.R;

import java.util.List;



public class Dap07 extends RecyclerView.Adapter<Hld07> {


    private RecyclerViewClickListener listener;

    private Context context;
    private List<Obj07> items;

    onLongItemClickListener mOnLongItemClickListener;

    public Dap07(Context _context, List<Obj07> _items, RecyclerViewClickListener _listener)
    {
        this.context=_context;
        this.items=_items;
        this.listener=_listener;
    }

    @NonNull
    @Override
    public Hld07 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.ri07, parent, false);
        return new Hld07(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Hld07 holder, int position) {

        if(items!=null  )
        {
            String p1=items.get(position).getF03();
            String p2=items.get(position).getF05();
            String p3=items.get(position).getF11();

            String p7=items.get(position).getF09();

            byte[] dec= Base64.decode(p7, Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(dec, 0, dec.length);
            bmp=getRoundBitmap(bmp);
            holder.r7f1.setImageBitmap(bmp);

            holder.r7f2.setText(p1);
            holder.r7f3.setText(p2);
            holder.r7f4.setText(p3);


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
