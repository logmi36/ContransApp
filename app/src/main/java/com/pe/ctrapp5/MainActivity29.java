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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pe.ctrapp5.Adapter.Dap09;
import com.pe.ctrapp5.Data.Dta09;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj09;
import com.pe.ctrapp5.Model.Obj18;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity29 extends AppCompatActivity {

    //conductores

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    Obj01 obj01;
    Obj18 obj18;
    Dap09 dap09;
    Dta09 dta09;

    List<Obj09> items;
    Obj09 item;

    private int mCurrentItemPosition;

    FloatingActionButton fr29r02;

    private Sender sender;

    String txtSrh;


    private SwipeRefreshLayout fr29r03;

    //conductores

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main29);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Contenedores");

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");
        obj18=(Obj18)intent.getSerializableExtra("obj18");


        fr29r02 = (FloatingActionButton) findViewById(R.id.fr29r02);
        recyclerView = (RecyclerView) findViewById(R.id.fr29r01);
        fr29r03=(SwipeRefreshLayout) findViewById(R.id.fr29r03);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        registerForContextMenu(recyclerView);


        sender = sender.getInstance();

        fr29r02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc01();
            }
        });

        fr29r03.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fnc15();
            }
        });


        dta09 =new Dta09(this);

        items=new ArrayList<Obj09>();
        item= new Obj09();

        txtSrh="";

        fnc02(txtSrh);

    }


    @Override
    public void onResume(){
        super.onResume();
        fr29r03.setRefreshing(false);
        Log.e("Activity====", "Activity 15");

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
            fnc15();
        }

        if(id==R.id.mn8r4){

        }

        if(id==16908332){
            super.onBackPressed();
        }

        return true;
    }



    private  void fnc00(){

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                item=items.get(position);
                fnc06(item);
            }
        };

        dap09 = new Dap09(this, items, listener);

        dap09.setOnLongItemClickListener(new Dap09.onLongItemClickListener() {
            @Override
            public void ItemLongClicked(View v, int position) {
                mCurrentItemPosition = position;
                v.showContextMenu();
            }
        });

        recyclerView.setAdapter(dap09);
    }




    private void fnc01(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Buscar Contenedor");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Numero de contenedor :";
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
                txtSrh=p1;
                if(p1.equals("")){
                    ShowMessage("Por favor, ingrese un numero de contenedor");
                    return;
                }
                fnc02(p1);
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



    private void fnc02(String cod){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            fnc04(cod);
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Buscando : "+ cod + "...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(obj18.getF02(), obj01.getF01(),cod,"","","");
        sender.connect().fnc36(item).enqueue(new Callback<List<Obj09>>() {
            @Override
            public void onResponse(Call<List<Obj09>> call, Response<List<Obj09>> res) {
                waitingDialog.hide();
                fr29r03.setRefreshing(false);
//                Log.e("e___e", res.body().toString());
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                fnc03(res.body());
            }

            @Override
            public void onFailure(Call<List<Obj09>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc03(List<Obj09> l9){

        for(Obj09 p9 : l9){
            dta09.deleteByF01(p9.getF01());
            dta09.insert(p9);
        }

        items=l9;
        fnc00();

    }


    private void fnc04(String cod) {
        //search local database
        Toast.makeText(this,  "Modo local 👀", Toast.LENGTH_SHORT).show();
        items=dta09.ListById(item.getF14());
        fnc00();
    }


    private void fnc06(Obj09 im){

        dta09.deleteByF01(im.getF01());
        dta09.insert(im);
        Intent intent = new Intent(this, MainActivity16.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj09",im);
        startActivity(intent);

    }


    private  void fnc15() {
        if(!txtSrh.equals("")){
            fnc02(txtSrh);
        }
    }


    private void fnc08(){

        Intent intent = new Intent(this, MainActivity23.class);
        intent.putExtra("obj01",obj01);
        startActivity(intent);

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
            fnc15();
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