package com.pe.ctrapp5;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pe.ctrapp5.Adapter.Dap06;
import com.pe.ctrapp5.Data.Dta06;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj04;
import com.pe.ctrapp5.Model.Obj06;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity11 extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    Obj01 obj01;

    private int mCurrentItemPosition;

    private SwipeRefreshLayout fr11r03;

    private Sender sender;

    List<Obj06> items;
    Obj04 obj04;
    Dap06 dap06;
    Dta06 dta06;

    Obj06 item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");
        obj04=(Obj04)intent.getSerializableExtra("obj04");


        recyclerView = (RecyclerView) findViewById(R.id.fr11r01);
        fr11r03=(SwipeRefreshLayout) findViewById(R.id.fr11r03);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        registerForContextMenu(recyclerView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(obj04.getF02());

        dta06 = new Dta06(this);
        sender = sender.getInstance();


        fr11r03.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        items= new ArrayList<>();

        fnc02();
    }


    @Override
    public void onResume(){
        super.onResume();
        fr11r03.setRefreshing(false);
        Log.e("Activity====", "Activity 11");
    }



    private void fnc02(){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            fnc11();
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Buscando ...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(obj04.getF01(), "","","","","");
        sender.connect().fnc08(item).enqueue(new Callback<List<Obj06>>() {
            @Override
            public void onResponse(Call<List<Obj06>> call, Response<List<Obj06>> res) {
                waitingDialog.hide();
                fr11r03.setRefreshing(false);
                Log.e("e___e", res.body().toString());
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                items=res.body();

                dta06.deleteById(obj04.getF01());

                for(Obj06 m6 : items){
                    dta06.insert(m6);
                }

                fnc03();
            }

            @Override
            public void onFailure(Call<List<Obj06>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc11(){
        items=dta06.ListById(obj04.getF01());
        fnc03();
        Toast.makeText(this,  "Modo offline 👀", Toast.LENGTH_SHORT).show();
    }


    private  void fnc03(){

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                item=items.get(position);
                fnc04(item);
            }
        };

        dap06 = new Dap06(this, items, listener);

        dap06.setOnLongItemClickListener(new Dap06.onLongItemClickListener() {
            @Override
            public void ItemLongClicked(View v, int position) {
                mCurrentItemPosition = position;
                v.showContextMenu();
            }
        });

        recyclerView.setAdapter(dap06);
    }


    private void fnc04(Obj06 obj06){

        Intent intent = new Intent(this, MainActivity12.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj04",obj04);
        intent.putExtra("obj06",obj06);
        startActivity(intent);

    }

    private void fnc05()
    {

        Obj06 m6=items.get(mCurrentItemPosition);

        String message="¿Esta seguro de eliminar el item :  "+m6.getF02()+"?";
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage(message);
        dialog.setIcon(R.drawable.icon5);
        dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fnc06(m6);
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }


    private void fnc06(Obj06 m6){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            fnc12(m6);
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando ...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(m6.getF02(), "","","","","");
        sender.connect().fnc09(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                fr11r03.setRefreshing(false);
                Log.e("e___e", res.body().toString());
                if (res.body().getNum().equals("000000")) {
                    ShowMessage("Algo fue mal 😢");
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

    private void fnc07(String r){
        if(r.equals("1")){
            Toast.makeText(this,  "No se puede eliminar la imagen principal 😢", Toast.LENGTH_SHORT).show();
        }
        if(r.equals("0")){
            Toast.makeText(this,  "Imagen eliminada 😉", Toast.LENGTH_SHORT).show();
            fnc02();
        }
    }


    private void fnc12(Obj06 m6){
        dta06.deleteByF02(m6.getF02());
        fnc11();
    }


    private void fnc08()
    {

        Obj06 m6=items.get(mCurrentItemPosition);

        String message="¿Esta seguro de eliminar todas las imagenes del vehiculo :  "+obj04.getF02()+"?";
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage(message);
        dialog.setIcon(R.drawable.icon5);
        dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fnc09();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }


    private void fnc09(){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("No existe conexion a internet");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando ...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(obj04.getF01(), "","","","","");
        sender.connect().fnc10(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                fr11r03.setRefreshing(false);
                Log.e("e___e", res.body().toString());
                if (res.body()==null) {
                    ShowMessage("Ha surgido un error 😢");
                    return;
                }
                fnc10(res.body().getNum());
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }

    private void fnc10(String r){
        if(r.equals("1")){
            Toast.makeText(this,  "Historial eliminado 😉", Toast.LENGTH_SHORT).show();
            fnc02();
        }
        else{
            Toast.makeText(this,  "Ha surgido un error 😢", Toast.LENGTH_SHORT).show();
            fnc02();
        }
    }


    //================================================================================================================================================

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main08, menu);
//        return super.onCreateOptionsMenu(menu);
//
//    }
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Log.i("id____=",String.valueOf(id));

//        if(id==R.id.mn8r1){
//
//        }
//
//        if(id==R.id.mn8r2){
//
//        }
//
//        if(id==R.id.mn8r3){
//            fnc02();
//        }
//
//        if(id==R.id.mn8r4){
//            fnc08();
//        }

        if(id==16908332){
            super.onBackPressed();
        }

        return true;
    }


    //================================================================================================================================================



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main02, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.mn2r1){

        }

        if(id==R.id.mn2r2){

        }

        if(id==R.id.mn2r3){

        }

        return true;
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


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager  = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}