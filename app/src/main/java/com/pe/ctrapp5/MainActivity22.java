package com.pe.ctrapp5;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.pe.ctrapp5.Data.Dta12;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj12;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity22 extends AppCompatActivity {

    TextView f22r01;
    TextView f22r02;
    TextView f22r03;
    TextView f22r04;
    TextView f22r05;
    TextView f22r06;
    TextView f22r07;
    TextView f22r08;
    TextView f22r09;
    TextView f22r10;
    TextView f22r11;
    TextView f22r12;
    TextView f22r13;
    TextView f22r14;

    ImageButton f22r15;
    ImageButton f22r16;
    ImageButton f22r17;
    TextView f22r18;
    TextView f22r19;
    TextView f22r20;

    Obj01 obj01;
    Obj12 obj12;
    Dta12 dta12;
    private Sender sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        Intent intent = getIntent();
        obj01 =(Obj01) intent.getSerializableExtra("obj01");
        obj12 =(Obj12) intent.getSerializableExtra("obj12");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(obj12.getF01());

        f22r01= (TextView) findViewById(R.id.f22r01);
        f22r02= (TextView) findViewById(R.id.f22r02);
        f22r03= (TextView) findViewById(R.id.f22r03);
        f22r04= (TextView) findViewById(R.id.f22r04);
        f22r05= (TextView) findViewById(R.id.f22r05);
        f22r06= (TextView) findViewById(R.id.f22r06);
        f22r07= (TextView) findViewById(R.id.f22r07);
        f22r08= (TextView) findViewById(R.id.f22r08);
        f22r09= (TextView) findViewById(R.id.f22r09);
        f22r10= (TextView) findViewById(R.id.f22r10);
        f22r11= (TextView) findViewById(R.id.f22r11);
        f22r12= (TextView) findViewById(R.id.f22r12);
        f22r13= (TextView) findViewById(R.id.f22r13);
        f22r14= (TextView) findViewById(R.id.f22r14);

        f22r15= (ImageButton) findViewById(R.id.f22r15);
        f22r16= (ImageButton) findViewById(R.id.f22r16);
        f22r17= (ImageButton) findViewById(R.id.f22r17);

        f22r18= (TextView) findViewById(R.id.f22r18);
        f22r19= (TextView) findViewById(R.id.f22r19);
        f22r20= (TextView) findViewById(R.id.f22r20);

        sender = sender.getInstance();
        dta12 =new Dta12(this);

        f22r15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc02();
            }
        });

        f22r16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc04();
            }
        });

        f22r17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc06();
            }
        });

        fnc01();

        Log.e("Activity====", "Activity 22");

    }

    private void fnc01(){
        f22r01.setText(obj12.getF01());
        f22r02.setText(obj12.getF02());
        f22r03.setText(obj12.getF03());
        f22r04.setText(obj12.getF04() + "-" + obj12.getF05());
        f22r05.setText(obj12.getF06() + " " + obj12.getF07());
        f22r06.setText(obj12.getF08());
        f22r07.setText(obj12.getF09());
        f22r08.setText(obj12.getF10());
        f22r09.setText(obj12.getF11());
        f22r10.setText(obj12.getF12());
        f22r11.setText(obj12.getF13());

        f22r18.setText(obj12.getF15());
        f22r19.setText(obj12.getF16());
        f22r20.setText(obj12.getF17());

        f22r15.setImageResource(R.drawable.ic_cta2);
        if(obj12.getF15().equals(""))
            f22r15.setImageResource(R.drawable.ic_cta1);

        f22r16.setImageResource(R.drawable.ic_ctb2);
        if(obj12.getF16().equals(""))
            f22r16.setImageResource(R.drawable.ic_ctb1);

        f22r17.setImageResource(R.drawable.ic_ctc2);
        if(obj12.getF17().equals(""))
            f22r17.setImageResource(R.drawable.ic_ctc1);

    }


    //====================================================================

    private void fnc02(){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            Log.e("e___", "Sin conexion de red");
            ShowMessage("Sin conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando arribo de cita...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(obj12.getF01(), obj01.getF01(), "","","","");
        sender.connect().fnc19(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();

                if (res.body().getNum().equals("")) {
                    ShowMessage("No se pudo actualizar la hora de arribo de la cita");
                    return;
                }
                fnc03(res.body().getNum());
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc03(String res){

        dta12.deleteByF01(obj12.getF01());
        obj12.setF15(res);
        dta12.insert(obj12);
        fnc01();

    }


    //====================================================================

    private void fnc04(){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            Log.e("e___", "Sin conexion de red");
            ShowMessage("Sin conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando ingreso de cita...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(obj12.getF01(), obj01.getF01(),"","","","");
        sender.connect().fnc20(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();

                if (res.body().getNum().equals("")) {
                    ShowMessage("No se pudo actualizar la hora de ingreso de la cita");
                    return;
                }
                fnc05(res.body().getNum());
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc05(String res){

        dta12.deleteByF01(obj12.getF01());
        obj12.setF16(res);
        dta12.insert(obj12);
        fnc01();

    }


    //====================================================================

    private void fnc06(){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            Log.e("e___", "Sin conexion de red");
            ShowMessage("Sin conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando salida de cita...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(obj12.getF01(), obj12.getF01(), "","","","");
        sender.connect().fnc21(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();

                if (res.body().getNum().equals("")) {
                    ShowMessage("No se pudo actualizar la hora de salida de la cita");
                    return;
                }
                fnc07(res.body().getNum());
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc07(String res){

        dta12.deleteByF01(obj12.getF01());
        obj12.setF17(res);
        dta12.insert(obj12);
        fnc01();

    }


    private void fnc08(){

        Intent intent = new Intent(this, MainActivity24.class);
        intent.putExtra("obj01",obj01);
        startActivity(intent);

    }




    private void fnc09(String num, String ser){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            Log.e("e___", "Sin conexion de red");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Buscando : "+ num + "...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(num, ser,"","","","");
        sender.connect().fnc18(item).enqueue(new Callback<Obj12>() {
            @Override
            public void onResponse(Call<Obj12> call, Response<Obj12> res) {
                waitingDialog.hide();

                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados para la cita :" + num);
                    return;
                }
                fnc06(res.body());
            }

            @Override
            public void onFailure(Call<Obj12> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc06(Obj12 im){

        obj12=im;
        dta12.deleteByF01(im.getF01());
        dta12.insert(im);

        fnc01();

    }


    //================================================================================================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main08, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Log.i("id____=",String.valueOf(id));

        if(id==R.id.mn8r1){
            fnc01();
        }

        if(id==R.id.mn8r2){
            fnc08();
        }

        if(id==R.id.mn8r3){
            fnc09("",obj12.getF01());
        }

        if(id==R.id.mn8r4){

        }

        if(id==16908332){
            super.onBackPressed();
        }

        return true;
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


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager  = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}