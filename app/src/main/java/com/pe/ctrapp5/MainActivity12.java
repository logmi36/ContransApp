package com.pe.ctrapp5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jsibbold.zoomage.ZoomageView;
import com.pe.ctrapp5.Data.Dta06;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj04;
import com.pe.ctrapp5.Model.Obj06;

public class MainActivity12 extends AppCompatActivity {

    private ZoomageView f12r01;

    Obj01 obj01;
    Obj04 obj04;
    Obj06 obj06;

    Dta06 dta06;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        f12r01 = findViewById(R.id.f12r01);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");
        obj04=(Obj04)intent.getSerializableExtra("obj04");
        obj06=(Obj06)intent.getSerializableExtra("obj06");

        actionBar.setTitle(obj04.getF02());

        byte[] decodedString = Base64.decode(obj06.getF04(), Base64.DEFAULT);
        Bitmap mbitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        f12r01.setImageBitmap(mbitmap);

        dta06 = new Dta06(this);

        dta06.insert(obj06);
    }


    @Override
    public void onResume(){
        super.onResume();
        Log.e("Activity====", "Activity 12");
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==16908332){
            super.onBackPressed();
        }
        return true;
    }


}