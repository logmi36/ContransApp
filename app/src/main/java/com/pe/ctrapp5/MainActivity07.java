package com.pe.ctrapp5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj02;
import com.pe.ctrapp5.Model.Obj03;


public class MainActivity07 extends AppCompatActivity {


    FloatingActionButton f07f09;
    ImageView f07f08;


    Obj01 obj01;
    Obj02 obj02;
    Obj03 obj03;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main07);

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");
        obj02=(Obj02)intent.getSerializableExtra("obj02");
        obj03=(Obj03)intent.getSerializableExtra("obj03");


        Toolbar toolbar =(Toolbar) findViewById(R.id.f07t01);
        setSupportActionBar(toolbar);

//        toolbar.setTitle(obj03.getF03());
//        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(obj03.getF03());

//

        f07f08= (ImageView) findViewById(R.id.f07f08);
        f07f09= (FloatingActionButton) findViewById(R.id.f07f09);

        Log.e("Activity====", "Activity 07");
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId()==16908332){
            finish();
        }

        return super.onOptionsItemSelected(item);

    }



    private void ShowMessage(String _message)
    {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage(_message);
        dialog.setIcon(R.drawable.icon5);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

}