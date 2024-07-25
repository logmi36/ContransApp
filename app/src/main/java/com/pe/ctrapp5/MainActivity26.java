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


import com.pe.ctrapp5.Data.Dta13;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj13;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity26 extends AppCompatActivity {

    TextView f26r01;
    TextView f26r02;
    TextView f26r03;
    TextView f26r04;
    TextView f26r05;
    TextView f26r06;
    TextView f26r07;
    TextView f26r08;
    TextView f26r09;
    TextView f26r10;
    TextView f26r11;
    TextView f26r12;
    TextView f26r13;
    TextView f26r14;



    Obj01 obj01;
    Obj13 obj13;
    Dta13 dta13;
    private Sender sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main26);

        Intent intent = getIntent();
        obj01 =(Obj01) intent.getSerializableExtra("obj01");
        obj13 =(Obj13) intent.getSerializableExtra("obj13");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(obj13.getF01());

        f26r01= (TextView) findViewById(R.id.f26r01);
        f26r02= (TextView) findViewById(R.id.f26r02);
        f26r03= (TextView) findViewById(R.id.f26r03);
        f26r04= (TextView) findViewById(R.id.f26r04);
        f26r05= (TextView) findViewById(R.id.f26r05);
        f26r06= (TextView) findViewById(R.id.f26r06);
        f26r07= (TextView) findViewById(R.id.f26r07);
        f26r08= (TextView) findViewById(R.id.f26r08);
        f26r09= (TextView) findViewById(R.id.f26r09);
        f26r10= (TextView) findViewById(R.id.f26r10);
        f26r11= (TextView) findViewById(R.id.f26r11);
        f26r12= (TextView) findViewById(R.id.f26r12);
        f26r13= (TextView) findViewById(R.id.f26r13);
        f26r14= (TextView) findViewById(R.id.f26r14);

        sender = sender.getInstance();
        dta13 =new Dta13(this);

        fnc02();

        Log.e("Activity====", "Activity 26");

    }

    private void fnc01(){

        f26r01.setText(obj13.getF01());
        f26r02.setText(obj13.getF02());
        f26r03.setText(obj13.getF03());
        f26r04.setText(obj13.getF04() + "-" + obj13.getF05());
        f26r05.setText(obj13.getF06() + " " + obj13.getF07());
        f26r06.setText(obj13.getF08());
        f26r07.setText(obj13.getF09());
        f26r08.setText(obj13.getF10());
        f26r09.setText(obj13.getF11());
        f26r10.setText(obj13.getF12());
        f26r11.setText(obj13.getF13());

        f26r12.setText(obj13.getF15());
        f26r13.setText(obj13.getF16());
        f26r14.setText(obj13.getF17());

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
        waitingDialog.setMessage("Buscando : "+ obj13.getF01() + "...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(obj13.getF01(), obj01.getF01(),"","","","");

        Log.e("e__l", item.toString());
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

        DocItem item = new DocItem(obj13.getF01(),obj01.getF01(),"",obj01.getF01(),"","");

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