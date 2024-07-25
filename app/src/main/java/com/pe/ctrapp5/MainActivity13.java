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
import com.pe.ctrapp5.Adapter.Dap07;
import com.pe.ctrapp5.Data.Dta07;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity13 extends AppCompatActivity {

    //conductores

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    Obj01 obj01;
    Dap07 dap07;
    Dta07 dta07;

    List<Obj07> items;
    Obj07 item;

    private int mCurrentItemPosition;

    FloatingActionButton fr13r02;

    private Sender sender;

    String txtSrh;


    private SwipeRefreshLayout fr13r03;

    //conductores

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Conductores");

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");


        fr13r02 = (FloatingActionButton) findViewById(R.id.fr13r02);
        recyclerView = (RecyclerView) findViewById(R.id.fr13r01);
        fr13r03=(SwipeRefreshLayout) findViewById(R.id.fr13r03);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        registerForContextMenu(recyclerView);


        sender = sender.getInstance();

        fr13r02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc16();
            }
        });

        fr13r03.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fnc15();
            }
        });


        dta07 =new Dta07(this);

        items=new ArrayList<Obj07>();
        item= new Obj07();

        txtSrh="";



    }


    @Override
    public void onResume(){
        super.onResume();
        fr13r03.setRefreshing(false);

        items=dta07.List("", obj01.getF01());
        fnc00();

        Log.e("Activity====", "Activity 13");

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
            //buscar
            fnc01();
        }

        if(id==R.id.mn8r2){
            //nuevo conductor
            fnc16();
        }

        if(id==R.id.mn8r3){
            //sincronizar
            fnc21("");
        }

        if(id==R.id.mn8r4){
            //limpiar
        }

        if(id==16908332){
            super.onBackPressed();
        }

        return true;
    }



    private  void  fnc00(){

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                item=items.get(position);
                fnc26(item);
            }
        };

        dap07 = new Dap07(this, items, listener);

        dap07.setOnLongItemClickListener(new Dap07.onLongItemClickListener() {
            @Override
            public void ItemLongClicked(View v, int position) {
                mCurrentItemPosition = position;
                v.showContextMenu();
            }
        });

        recyclerView.setAdapter(dap07);
        //Toast.makeText(this, "registros encontrados : " +items.size() , Toast.LENGTH_SHORT).show();
    }




    private void fnc01(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Buscar Conductor");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Nombres o DNI :";
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
                    ShowMessage("Por favor ingrese el nombre o DNI del conductor 😢");
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
        sender.connect().fnc11(item).enqueue(new Callback<List<Obj07>>() {
            @Override
            public void onResponse(Call<List<Obj07>> call, Response<List<Obj07>> res) {
                waitingDialog.hide();
                fr13r03.setRefreshing(false);
//                Log.e("e___e", res.body().toString());
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                fnc03(res.body());
            }

            @Override
            public void onFailure(Call<List<Obj07>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc03(List<Obj07> l3){

        items=l3;
        fnc00();

    }


    private void fnc04(String cod) {
        //search local database
        Toast.makeText(this,  "Modo local 👀", Toast.LENGTH_SHORT).show();
        items=dta07.List(cod, obj01.getF01());
        fnc00();
    }


    private void fnc06(Obj07 im){

        dta07.deleteByF01(im.getF01());
        dta07.insert(im);
        Intent intent = new Intent(this, MainActivity14.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj07",im);
        startActivity(intent);

    }


    private  void fnc15() {
            fnc02(txtSrh);
    }


    private void fnc16(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Nuevo Conductor");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="DNI";
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
                    ShowMessage("Por favor, ingrese un numero de DNI");
                    return;
                }
                fnc17(p1);
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



    private void fnc17(String cod){

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
        sender.connect().fnc11(item).enqueue(new Callback<List<Obj07>>() {
            @Override
            public void onResponse(Call<List<Obj07>> call, Response<List<Obj07>> res) {
                waitingDialog.hide();
                fr13r03.setRefreshing(false);
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                fnc18(cod, res.body());
            }

            @Override
            public void onFailure(Call<List<Obj07>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }



    private void fnc18(String dni, List<Obj07> l3){

        if(l3.size()==0){
            fnc19(dni);
        }
        if(l3.size()>1){
            items=l3;
            fnc00();
        }

        if(l3.size()==1){
            fnc06(l3.get(0));
        }

    }


    private void fnc19(String cod){

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

        DocItem item = new DocItem(cod, obj01.getF01(),"","","","");
        sender.connect().fnc30(item).enqueue(new Callback<Obj07>() {
            @Override
            public void onResponse(Call<Obj07> call, Response<Obj07> res) {
                waitingDialog.hide();
                fr13r03.setRefreshing(false);
                if (res.body()==null) {
                    ShowMessage("No existe comunicacion con API DNI");
                    return;
                }
                fnc20(res.body());
            }

            @Override
            public void onFailure(Call<Obj07> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }

    public void fnc20(Obj07 m7){
        dta07.deleteByF01(m7.getF01());
        dta07.insert(m7);

        items=dta07.List("",obj01.getF01());
        fnc00();
    }



    private void fnc21(String cod){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            fnc04(cod);
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando : "+ cod + " ...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(cod, obj01.getF01(),"","","","");
        sender.connect().fnc11(item).enqueue(new Callback<List<Obj07>>() {
            @Override
            public void onResponse(Call<List<Obj07>> call, Response<List<Obj07>> res) {
                waitingDialog.hide();
                fr13r03.setRefreshing(false);
//                Log.e("e___e", res.body().toString());
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                fnc22(res.body());
            }

            @Override
            public void onFailure(Call<List<Obj07>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc22(List<Obj07> l7){


        dta07.deleteByF10(obj01.getF01());

        int n=l7.size();
        for(int i=0;i<n; i++){
            dta07.insert(l7.get(n-1-i));
        }

        items=dta07.List("", obj01.getF01());
        fnc00();

    }

    //================================================================================================================================================


    private void fnc23(){

        String nom=items.get(mCurrentItemPosition).getF03();
        String id=items.get(mCurrentItemPosition).getF01();

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("¿Esta seguro de eliminar a " + nom + " ?");
        dialog.setIcon(R.drawable.icon5);
        dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fnc24(id);
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

    private void fnc24(String cod){

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

        DocItem item = new DocItem("9", cod,"D",obj01.getF01(),"","");

        sender.connect().fnc31(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                if (res.body().getNum().equals("0")) {
                    ShowMessage("Hubo un error en la eliminacion 😢");
                    return;
                }
                fnc25(cod);
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });

    }

    private void fnc25(String cod){
        Toast.makeText(this, "Conductor eliminado con exito 😉", Toast.LENGTH_SHORT).show();
        dta07.deleteByF01(cod);
        items=dta07.List("", obj01.getF01());
        fnc00();
    }



    private void fnc26(Obj07 im){

        Intent intent = new Intent(this, MainActivity31.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj07",im);
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
            //eliminar
            fnc23();
        }

        if(id==R.id.mn2r2){
            //editar
            Obj07 m7=items.get(mCurrentItemPosition);
            fnc06(m7);
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