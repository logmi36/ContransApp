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
import com.pe.ctrapp5.Adapter.Dap11;
import com.pe.ctrapp5.Data.Dta11;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj10;
import com.pe.ctrapp5.Model.Obj11;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity17 extends AppCompatActivity {

    public RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    SwipeRefreshLayout fr17r03;


    Obj01 obj01;
    Obj10 obj10;

    int mCurrentItemPosition;

    Sender sender;

    List<Obj11> items;
    Obj11 item;
    Dap11 dap11;

    String txtSrh;

    Dta11 dta11;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");
        obj10=(Obj10)intent.getSerializableExtra("obj10");

        recyclerView = (RecyclerView) findViewById(R.id.fr17r01);
        fr17r03=(SwipeRefreshLayout) findViewById(R.id.fr17r03);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        registerForContextMenu(recyclerView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(obj10.getF03()+ " " + obj10.getF04());

        sender = sender.getInstance();


        fr17r03.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fnc02(obj10.getF01());
            }
        });


        dta11 =new Dta11(this);

        items=new ArrayList<Obj11>();
        item= new Obj11();


    }


    @Override
    public void onResume(){
        super.onResume();
        fr17r03.setRefreshing(false);
        fnc02(obj10.getF01());
        Log.e("Activity====", "Activity 17");
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

        dap11 = new Dap11(this, items, listener);

        dap11.setOnLongItemClickListener(new Dap11.onLongItemClickListener() {
            @Override
            public void ItemLongClicked(View v, int position) {
                mCurrentItemPosition = position;
                v.showContextMenu();
            }
        });

        recyclerView.setAdapter(dap11);

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

        DocItem item = new DocItem(cod, "","","","","");

        Log.e("e__l", item.toString());
        sender.connect().fnc17(item).enqueue(new Callback<List<Obj11>>() {
            @Override
            public void onResponse(Call<List<Obj11>> call, Response<List<Obj11>> res) {
                waitingDialog.hide();
                fr17r03.setRefreshing(false);
//                Log.e("e___e", res.body().toString());
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                fnc03(res.body());
            }

            @Override
            public void onFailure(Call<List<Obj11>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc03(List<Obj11> l3){

        items=l3;
        dta11.deleteByF01(obj10.getF01());
        for(Obj11 p11 : l3){
            dta11.insert(p11);
        }

        fnc00();

    }

    private void fnc04(String cod) {
        //search local database
        Toast.makeText(this,  "Modo local 👀", Toast.LENGTH_SHORT).show();
        items=dta11.ListById(cod);
        fnc00();
    }


    private void fnc06(Obj11 im){

        DocItem im2;
        im2 = new DocItem(item.getF03()+" " + item.getF04(),im.getF05(),"", "","","");

        Intent intent = new Intent(this, MainActivity10.class);
        intent.putExtra("item",im2);
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