package com.pe.ctrapp5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.pe.ctrapp5.Model.Obj01;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity24 extends AppCompatActivity {

    Obj01 obj01;

    Button btnDamageCtnD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main24);

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("EIR");

        btnDamageCtnD=(Button)findViewById(R.id.btnDamageCtnD);

        btnDamageCtnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc02();
            }
        });


    }


    @Override
    public void onResume(){
        super.onResume();
        Log.e("Activity====", "Activity 24");
    }


    //================================================================================================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main04, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Log.i("id____=",String.valueOf(id));

        if(id==R.id.mn4r1){

        }

        if(id==16908332){
            super.onBackPressed();
        }

        return true;
    }




    //================================================================================================================================================



    private void fnc02() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        dialog.setTitle(R.string.nav_header_title);
//        dialog.setMessage("Daño de contenedor");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment07, null);

        dialog.setView(login_layout);
        dialog.setIcon(R.drawable.icon5);

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    //================================================================================================================================================

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