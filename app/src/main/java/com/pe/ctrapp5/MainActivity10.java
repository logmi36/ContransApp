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
import com.pe.ctrapp5.Model.DocItem;


public class MainActivity10 extends AppCompatActivity {

    private ZoomageView imageSingle;

    DocItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        imageSingle = findViewById(R.id.imageSingle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        item=(DocItem)intent.getSerializableExtra("item");

        //actionBar.setTitle(obj05.getF02());
        actionBar.setTitle(item.getNum());

        //byte[] decodedString = Base64.decode(obj05.getF13(), Base64.DEFAULT);
        byte[] decodedString = Base64.decode(item.getSer(), Base64.DEFAULT);
        Bitmap mbitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageSingle.setImageBitmap(mbitmap);

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.e("Activity====", "Activity 10");
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){

            case 16908332:
                super.onBackPressed();
                break;
            default:
                break;
        }

        return true;
    }
}