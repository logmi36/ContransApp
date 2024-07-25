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
import com.pe.ctrapp5.Adapter.Dap18;
import com.pe.ctrapp5.Data.Dta18;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj18;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity28 extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    Obj01 obj01;

    private int mCurrentItemPosition;

    FloatingActionButton fr28r02;

    private Sender sender;

    List<Obj18> items;
    Obj18 item;
    Dap18 dap18;
    Dta18 dta18;

    String txtSrh;



    private SwipeRefreshLayout fr28r03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main28);

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");


        fr28r02 = (FloatingActionButton) findViewById(R.id.fr28r02);
        recyclerView = (RecyclerView) findViewById(R.id.fr28r01);
        fr28r03=(SwipeRefreshLayout) findViewById(R.id.fr28r03);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        registerForContextMenu(recyclerView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Documentos");

        sender = sender.getInstance();

        fr28r02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc09();
            }
        });

        fr28r03.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fnc15();
            }
        });


        dta18 =new Dta18(this);

        items=new ArrayList<Obj18>();
        item= new Obj18();

        txtSrh="";


    }


    @Override
    public void onResume(){
        super.onResume();
        fr28r03.setRefreshing(false);

        Log.e("Activity====", "Activity 28");

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            fnc04("");
            return;
        }

        fnc02("");

    }

    private  void fnc15() {
        if(!txtSrh.equals("")){
            fnc02(txtSrh);
        }
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
            fnc09();
        }

        if(id==R.id.mn8r3){
            //actualizar
            fnc07("");
        }

        if(id==R.id.mn8r4){
            //limpiar
            fnc14();
        }

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

        dap18 = new Dap18(this, items, listener);

        dap18.setOnLongItemClickListener(new Dap18.onLongItemClickListener() {
            @Override
            public void ItemLongClicked(View v, int position) {
                mCurrentItemPosition = position;
                v.showContextMenu();
            }
        });

        recyclerView.setAdapter(dap18);
    }


    private void fnc01(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Buscar Documento");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Numero de documento:";
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
                    ShowMessage("Por favor, ingrese un numero de documento");
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

        DocItem item = new DocItem(cod, obj01.getF01(),"","","","");
        sender.connect().fnc34(item).enqueue(new Callback<List<Obj18>>() {
            @Override
            public void onResponse(Call<List<Obj18>> call, Response<List<Obj18>> res) {
                waitingDialog.hide();
                fr28r03.setRefreshing(false);
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                fnc03(res.body());
            }

            @Override
            public void onFailure(Call<List<Obj18>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc03(List<Obj18> l3){

        items=l3;
        fnc00();

    }

    private void fnc04(String cod) {
        //search local database
        Toast.makeText(this,  "Modo local 👀", Toast.LENGTH_SHORT).show();
        items=dta18.ListbyId(cod);
        fnc00();
    }


    private void fnc06(Obj18 im){

        Intent intent = new Intent(this, MainActivity32.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj18",im);
        startActivity(intent);

    }


    private void fnc07(String cod){

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

        DocItem item = new DocItem(cod, obj01.getF01(),"","","","");
        sender.connect().fnc34(item).enqueue(new Callback<List<Obj18>>() {
            @Override
            public void onResponse(Call<List<Obj18>> call, Response<List<Obj18>> res) {
                waitingDialog.hide();
                fr28r03.setRefreshing(false);
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                fnc08(res.body());
            }

            @Override
            public void onFailure(Call<List<Obj18>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc08(List<Obj18> l4){

        dta18.deleteByF05(obj01.getF01());

        int n=l4.size();
        for(int i=0;i<n; i++){
            dta18.insert(l4.get(n-1-i));
        }

        items=dta18.ListbyId(obj01.getF01());

        fnc00();

    }


    //================================================================================================================================================



    private void fnc09(){

        if(obj01.getF13().equals("000000")){
            ShowMessage("Por favor, actualizar la empresa en su perfil.");
            return;
        }

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Nuevo documento");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Numero de documento";
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
                p1=p1.replace(" ","");
                p1=p1.replace("\n","");
                p1=p1.toUpperCase(Locale.ROOT);
                if(p1.equals("")){
                    ShowMessage("Por favor ingrese un numero de placa");
                    return;
                }
                fnc10(p1);
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



    private void fnc10(String cod){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            fnc04(cod);
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Buscando : "+ cod + " ...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(cod, obj01.getF01(),"","","","");
        sender.connect().fnc34(item).enqueue(new Callback<List<Obj18>>() {
            @Override
            public void onResponse(Call<List<Obj18>> call, Response<List<Obj18>> res) {
                waitingDialog.hide();
                fr28r03.setRefreshing(false);
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                fnc11(cod, res.body());
            }

            @Override
            public void onFailure(Call<List<Obj18>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }



    private void fnc11(String dni, List<Obj18> l4){

        if(l4.size()==0){
            fnc12(dni);
        }
        if(l4.size()>1){
            items=l4;
            fnc00();
        }

        if(l4.size()==1){
            fnc06(l4.get(0));
        }

    }


    private void fnc12(String cod){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            fnc04(cod);
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando : "+ cod + "...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(cod,obj01.getF13(),obj01.getF01(),"","","");
        sender.connect().fnc35(item).enqueue(new Callback<Obj18>() {
            @Override
            public void onResponse(Call<Obj18> call, Response<Obj18> res) {
                waitingDialog.hide();
                fr28r03.setRefreshing(false);
                if (res.body()==null) {
                    ShowMessage("Ha surgido un error");
                    return;
                }
                fnc13(res.body());
            }

            @Override
            public void onFailure(Call<Obj18> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }

    public void fnc13(Obj18 m18){
        dta18.insert(m18);
        items=dta18.ListbyId(obj01.getF01());
        fnc00();
    }

    public void fnc14(){
        dta18.deleteByF05(obj01.getF01());
        items=dta18.ListbyId(obj01.getF01());
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
            fnc15();
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