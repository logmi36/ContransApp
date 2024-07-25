package com.pe.ctrapp5;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.pe.ctrapp5.Data.Dta12;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj12;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity21 extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    Obj01 obj01;

    ImageView f21r01;

    private Sender sender;

    Obj12 item;
    Dta12 dta12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main21);

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");

        f21r01=(ImageView) findViewById(R.id.f21r01);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Citas");

        sender = sender.getInstance();

        f21r01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc03();
            }
        });

        dta12 =new Dta12(this);
        item= new Obj12();

    }


    @Override
    public void onResume(){
        super.onResume();
        Log.e("Activity====", "Activity 21");
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

        }

        if(id==R.id.mn8r3){
        }

        if(id==R.id.mn8r4){
            fnc55();
        }

        if(id==16908332){
            super.onBackPressed();
        }

        return true;
    }


    private void fnc55(){

        List<Obj12> l1= dta12.List();

        if(l1.size()>0) {
            item = l1.get(0);
            fnc06(item);
        }

    }


    //================================================================================================================================================


    private void fnc01(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Buscar cita");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Numero de cita :";
        fg05f01.setHint(txt);
        dialog.setIcon(R.drawable.icon5);

        dialog.setView(login_layout);

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                String p1= fg05f01.getText().toString();
                p1=p1.trim();
                p1=p1.replace("'","");
                p1=p1.replace("\n","");
                if(p1.equals("")){
                    ShowMessage("Por favor, ingrese un numero de cita");
                    return;
                }
                fnc02("",p1);
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



    private void fnc02(String num, String ser){

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


    private void fnc03(){
        //camera!
    }




    private void fnc06(Obj12 im){

        dta12.deleteByF01(im.getF01());
        dta12.insert(im);

        Intent intent = new Intent(this, MainActivity22.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj12",im);
        startActivity(intent);

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