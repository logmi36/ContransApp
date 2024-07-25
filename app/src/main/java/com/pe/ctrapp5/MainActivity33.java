package com.pe.ctrapp5;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.pe.ctrapp5.Data.Dta01;
import com.pe.ctrapp5.Data.Dta13;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj13;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity33 extends AppCompatActivity {

    TextView f33r01;
    TextView f33r02;
    TextView f33r03;
    TextView f33r04;
    TextView f33r05;
    TextView f33r06;
    TextView f33r07;
    TextView f33r08;
    TextView f33r09;
    TextView f33r10;
    TextView f33r11;
    TextView f33r12;
    TextView f33r13;
    TextView f33r14;



    Obj01 obj01;
    Dta13 dta13;
    Dta01 dta01;
    private Sender sender;

    String idCita;

    Obj13 obj13;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main33);

        Intent intent = getIntent();
        idCita =intent.getStringExtra("idCita");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(idCita);

        f33r01= (TextView) findViewById(R.id.f33r01);
        f33r02= (TextView) findViewById(R.id.f33r02);
        f33r03= (TextView) findViewById(R.id.f33r03);
        f33r04= (TextView) findViewById(R.id.f33r04);
        f33r05= (TextView) findViewById(R.id.f33r05);
        f33r06= (TextView) findViewById(R.id.f33r06);
        f33r07= (TextView) findViewById(R.id.f33r07);
        f33r08= (TextView) findViewById(R.id.f33r08);
        f33r09= (TextView) findViewById(R.id.f33r09);
        f33r10= (TextView) findViewById(R.id.f33r10);
        f33r11= (TextView) findViewById(R.id.f33r11);
        f33r12= (TextView) findViewById(R.id.f33r12);
        f33r13= (TextView) findViewById(R.id.f33r13);
        f33r14= (TextView) findViewById(R.id.f33r14);

        sender = sender.getInstance();
        dta13 =new Dta13(this);
        dta01 =new Dta01(this);

        obj01 =dta01.getLast();

        fnc02();

        Log.e("Activity====", "Activity 33");

    }

    private void fnc01(){

        f33r01.setText(obj13.getF01());
        f33r02.setText(obj13.getF02());
        f33r03.setText(obj13.getF03());
        f33r04.setText(obj13.getF04() + "-" + obj13.getF05());
        f33r05.setText(obj13.getF06() + " " + obj13.getF07());
        f33r06.setText(obj13.getF08());
        f33r07.setText(obj13.getF09());
        f33r08.setText(obj13.getF10());
        f33r09.setText(obj13.getF11());
        f33r10.setText(obj13.getF12());
        f33r11.setText(obj13.getF13());

        f33r12.setText(obj13.getF15());
        f33r13.setText(obj13.getF16());
        f33r14.setText(obj13.getF17());

    }



    private void fnc02(){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("No existe conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Buscando : "+ idCita + "...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(idCita, "","","","","");

        sender.connect().fnc25(item).enqueue(new Callback<Obj13>() {
            @Override
            public void onResponse(Call<Obj13> call, Response<Obj13> res) {
                waitingDialog.hide();
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados 😢");
                    return;
                }
                fnc03(res.body());
            }

            @Override
            public void onFailure(Call<Obj13> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });

    }

    private void fnc03(Obj13 im){
        dta13.deleteByF01(im.getF01());
        dta13.insert(im);
        obj13=im;
        fnc01();
    }



    private void fnc04(){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("No existe conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(obj13.getF01(),"","",obj01.getF01(),"","");

        Log.e("e__l", item.toString());
        sender.connect().fnc26(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados 😢");
                    return;
                }
                fnc05(res.body());
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }

    private void fnc05(DocItem im){
        im.setSer(sender.url+im.getSer());
        Log.e("docitem=====", im.toString());
        String url=im.getSer();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        startActivity(intent);
    }



    //================================================================================================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main11, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Log.i("id____=",String.valueOf(id));


        if(id==R.id.mn11r1){
            fnc02();
        }

        if(id==R.id.mn11r2){
            fnc04();
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