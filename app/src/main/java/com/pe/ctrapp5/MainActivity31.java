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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pe.ctrapp5.Adapter.Dap04;
import com.pe.ctrapp5.Adapter.Dap16;
import com.pe.ctrapp5.Data.Dta04;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj04;
import com.pe.ctrapp5.Model.Obj07;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity31 extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    Obj01 obj01;
    Obj07 obj07;

    private int mCurrentItemPosition;

    FloatingActionButton fr31r02;

    private Sender sender;

    List<Obj04> items;
    Obj04 item;
    Dap04 dap04;

    String txtSrh;

    Dta04 dta04;

    List<Obj04> ml4;

    private SwipeRefreshLayout fr31r03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main31);

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");
        obj07=(Obj07)intent.getSerializableExtra("obj07");


        fr31r02 = (FloatingActionButton) findViewById(R.id.fr31r02);
        recyclerView = (RecyclerView) findViewById(R.id.fr31r01);
        fr31r03=(SwipeRefreshLayout) findViewById(R.id.fr31r03);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        registerForContextMenu(recyclerView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(obj07.getF04());

        sender = sender.getInstance();

        fr31r02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc251();
            }
        });

        fr31r03.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fnc07("");
            }
        });


        dta04 =new Dta04(this);

        items=new ArrayList<Obj04>();
        item= new Obj04();

        txtSrh="";


    }


    @Override
    public void onResume(){
        super.onResume();
        fr31r03.setRefreshing(false);

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            fnc04();
            return;
        }

        fnc07("");

        Log.e("Activity====", "Activity 31");

    }


    //================================================================================================================================================


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==16908332){
            super.onBackPressed();
        }

        return true;
    }


    //================================================================================================================================================


    private  void fnc00(){

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                item=items.get(position);
                fnc06(item);
            }
        };

        dap04 = new Dap04(this, items, listener);

        dap04.setOnLongItemClickListener(new Dap04.onLongItemClickListener() {
            @Override
            public void ItemLongClicked(View v, int position) {
                mCurrentItemPosition = position;
                v.showContextMenu();
            }
        });

        recyclerView.setAdapter(dap04);
    }




    private void fnc04() {
        //search local database
        Toast.makeText(this,  "Modo local 👀", Toast.LENGTH_SHORT).show();
        items=dta04.ListbyF10(obj07.getF01());
        fnc00();
    }


    private void fnc06(Obj04 im){

        Intent intent = new Intent(this, MainActivity09.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj04",im);
        startActivity(intent);

    }


    private void fnc07(String cod){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            fnc04();
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Buscando : "+ cod + "...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(obj07.getF01(), obj01.getF01(),cod,"","","");
        sender.connect().fnc37(item).enqueue(new Callback<List<Obj04>>() {
            @Override
            public void onResponse(Call<List<Obj04>> call, Response<List<Obj04>> res) {
                waitingDialog.hide();
                fr31r03.setRefreshing(false);
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                fnc08(res.body());
            }

            @Override
            public void onFailure(Call<List<Obj04>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc08(List<Obj04> l4){

        dta04.deleteByF10(obj07.getF01());

        int n=l4.size();
        for(int i=0;i<n; i++){
            dta04.insert(l4.get(n-1-i));
        }

        items=dta04.ListbyF10(obj07.getF01());

        fnc00();

    }


    //================================================================================================================================================



    private void fnc251(){

        ml4= dta04.List(obj01.getF01());

        String[] l4 = new String[ml4.size()];

        for(int i = 0; i < ml4.size(); i++)
            l4[i] = ml4.get(i).getF02();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar placa de vehiculo");
        builder.setIcon(R.drawable.icon4);
        builder.setItems(l4, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                fnc252(item);
            }
        });
        builder.show();

    }

    private void fnc252(int id){
        Obj04 m4=ml4.get(id);
        fnc29(m4);
    }



    private void fnc29(Obj04 m4){

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

        DocItem item = new DocItem(m4.getF01(),obj01.getF13(),obj07.getF01(),obj01.getF01(),"","");
        sender.connect().fnc33(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                if (res.body().getNum().equals("0")) {
                    ShowMessage("Ocurrio un problema 😢");
                    return;
                }
                fnc30(m4);
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }



    private void fnc30(Obj04 m4){

        m4.setF09(obj07.getF03());
        m4.setF10(obj07.getF01());
        dta04.deleteById(m4.getF01());
        dta04.insert(m4);
        items=dta04.ListbyF10(obj07.getF01());
        fnc00();

    }



    //================================================================================================================================================



    private void fnc14(){

        String nom=items.get(mCurrentItemPosition).getF02();
        String id=items.get(mCurrentItemPosition).getF01();

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("¿Esta seguro de eliminar " + nom + " ?");
        dialog.setIcon(R.drawable.icon5);
        dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fnc15(id);
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private void fnc15(String cod){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("No existe conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando ...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem("10", cod,"D",obj01.getF01(),"","");

        sender.connect().fnc31(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                if (res.body().getNum().equals("0")) {
                    ShowMessage("Hubo un error en la eliminacion 😢");
                    return;
                }
                fnc16(cod);
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });

    }

    private void fnc16(String cod){
        Toast.makeText(this, "Vehiculo eliminado con exito 😉", Toast.LENGTH_SHORT).show();
        dta04.deleteById(cod);
        items=dta04.ListbyF10(obj07.getF01());
        fnc00();
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
            fnc14();
        }

        if(id==R.id.mn2r2){
            Obj04 m4=items.get(mCurrentItemPosition);
            fnc06(m4);
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