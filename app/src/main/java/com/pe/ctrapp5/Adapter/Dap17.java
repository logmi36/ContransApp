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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pe.ctrapp5.Model.Obj07;
import com.pe.ctrapp5.R;

import java.util.List;


public class Dap17 extends BaseAdapter {


    Context context;
    List<Obj07> items;

    public Dap17(Context context, List<Obj07> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Obj07 getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.ri14, null);

        String p1 = getItem(i).getF03();
        String p2 = getItem(i).getF04();
        String p3 = getItem(i).getF09();

        ImageView r14f1 = v.findViewById(R.id.r14f1);
        TextView r14f2 = v.findViewById(R.id.r14f2);
        TextView r14f3 = v.findViewById(R.id.r14f3);

        byte[] dec= Base64.decode(p3, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(dec, 0, dec.length);
        bmp=getRoundBitmap(bmp);
        r14f1.setImageBitmap(bmp);

        r14f2.setText(p1);
        r14f3.setText(p2);

        return  v;
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
