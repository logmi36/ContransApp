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
import com.pe.ctrapp5.Adapter.Dap04;
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

public class MainActivity08 extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    Obj01 obj01;

    private int mCurrentItemPosition;

    FloatingActionButton fr08r02;

    private Sender sender;

    List<Obj04> items;
    Obj04 item;
    Dap04 dap04;

    String txtSrh;

    Dta04 dta04;

    private SwipeRefreshLayout fr08r03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main08);

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");


        fr08r02 = (FloatingActionButton) findViewById(R.id.fr08r02);
        recyclerView = (RecyclerView) findViewById(R.id.fr08r01);
        fr08r03=(SwipeRefreshLayout) findViewById(R.id.fr08r03);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        registerForContextMenu(recyclerView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Vehiculos");

        sender = sender.getInstance();

        fr08r02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc09();
            }
        });

        fr08r03.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        fr08r03.setRefreshing(false);

        items=dta04.List(obj01.getF01());
        fnc00();

        Log.e("Activity====", "Activity 08");

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


    private void fnc01(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Buscar vehiculo");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Numero de matricula o placa :";
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
                p1=p1.replace("-","");
                p1=p1.replace("\n","");
                txtSrh=p1;
                if(p1.equals("")){
                    ShowMessage("Por favor, ingrese un numero de cuestionario");
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
        sender.connect().fnc05(item).enqueue(new Callback<List<Obj04>>() {
            @Override
            public void onResponse(Call<List<Obj04>> call, Response<List<Obj04>> res) {
                waitingDialog.hide();
                fr08r03.setRefreshing(false);
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                fnc03(res.body());
            }

            @Override
            public void onFailure(Call<List<Obj04>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc03(List<Obj04> l3){

        items=l3;
        fnc00();

    }

    private void fnc04(String cod) {
        //search local database
        Toast.makeText(this,  "Modo local 👀", Toast.LENGTH_SHORT).show();
        items=dta04.ListByF02(cod);
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
        sender.connect().fnc05(item).enqueue(new Callback<List<Obj04>>() {
            @Override
            public void onResponse(Call<List<Obj04>> call, Response<List<Obj04>> res) {
                waitingDialog.hide();
                fr08r03.setRefreshing(false);
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

        dta04.deleteByF08(obj01.getF01());

        int n=l4.size();
        for(int i=0;i<n; i++){
            dta04.insert(l4.get(n-1-i));
        }

        items=dta04.List(obj01.getF01());

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
        dialog.setMessage("Nueva matricula");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Numero de matricula";
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
        sender.connect().fnc05(item).enqueue(new Callback<List<Obj04>>() {
            @Override
            public void onResponse(Call<List<Obj04>> call, Response<List<Obj04>> res) {
                waitingDialog.hide();
                fr08r03.setRefreshing(false);
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                fnc11(cod, res.body());
            }

            @Override
            public void onFailure(Call<List<Obj04>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }



    private void fnc11(String dni, List<Obj04> l4){

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

        DocItem item = new DocItem(obj01.getF13(),cod,  "000000",obj01.getF01(),"","");
        sender.connect().fnc32(item).enqueue(new Callback<Obj04>() {
            @Override
            public void onResponse(Call<Obj04> call, Response<Obj04> res) {
                waitingDialog.hide();
                fr08r03.setRefreshing(false);
                if (res.body()==null) {
                    ShowMessage("Ha surgido un error");
                    return;
                }
                fnc13(res.body());
            }

            @Override
            public void onFailure(Call<Obj04> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }

    public void fnc13(Obj04 m4){
        dta04.insert(m4);
        items=dta04.List(obj01.getF01());
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
        items=dta04.List(obj01.getF01());
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