package com.pe.ctrapp5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pe.ctrapp5.Adapter.Dap03;
import com.pe.ctrapp5.Data.Dta02;
import com.pe.ctrapp5.Data.Dta03;
import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj02;
import com.pe.ctrapp5.Model.Obj03;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity05 extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    Dap03 dap03;
    Dta03 dta03;
    Dta02 dta02;
    List<Obj03> items;


    Obj01 obj01;

    Obj02 obj02;
    Obj03 item;

    private int mCurrentItemPosition;

    FloatingActionButton fr05r02;

    SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main05);


        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");
        obj02=(Obj02)intent.getSerializableExtra("obj02");

        fr05r02=(FloatingActionButton) findViewById(R.id.fr05r02);
        recyclerView = (RecyclerView) findViewById(R.id.fr05r01);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        registerForContextMenu(recyclerView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(obj02.getF03());

        fr05r02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc03();
            }
        });


        dta03 = new Dta03(this);
        dta02 = new Dta02(this);

    }

    @Override
    public void onResume(){
        super.onResume();
        fnc00();
        Log.e("Activity====", "Activity 05");
    }


    public  void fnc00(){

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                item=items.get(position);
                fnc01(item);
            }
        };

        //Log.e("obj02===", obj02.toString());

        items=dta03.ListById(obj02.getF01());
        dap03 = new Dap03(this, items, listener);

        dap03.setOnLongItemClickListener(new Dap03.onLongItemClickListener() {
            @Override
            public void ItemLongClicked(View v, int position) {
                mCurrentItemPosition = position;
                v.showContextMenu();
            }
        });

        recyclerView.setAdapter(dap03);

    }


    private void fnc01(Obj03 im){

        Intent intent = new Intent(this, MainActivity07.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj02",obj02);
        intent.putExtra("obj03",im);
        startActivity(intent);

    }

    private  void fnc03(){
        //add new user

        String s=obj02.getF20();
        int n = Integer.parseInt(s);
        n=n+1;
        obj02.setF20(String.valueOf(n));
        dta02.update(obj02);

        String dt1 = df1.format(new Date());
        String dt2 = df2.format(new Date());


        Obj03 p3 = new Obj03();
        p3.setF02(obj02.getF01());
        p3.setF03("Julio Cordova");
        p3.setF04("Contabilidad");
        p3.setF05(dt1);
        p3.setF06(dt2);

        dta03.insert(p3);

        fnc00();

    }




    //================================================================================================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main05, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Log.i("id____=",String.valueOf(id));

        if(id==R.id.mn5r1){
            fnc03();
        }

        if(id==R.id.mn5r2){

        }

        if(id==R.id.mn5r3){
            fnc00();
        }

        if(id==R.id.mn5r4){

        }

        if(id==R.id.mn5r5){
            fnc06();
        }

        if(id==16908332){
            super.onBackPressed();
        }

        return true;
    }

    //================================================================================================================================================

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main06, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.mn6r1){
            fnc02();
        }

        if(id==R.id.mn6r2){

        }

        if(id==16908332){
            super.onBackPressed();
        }
        return true;
    }

    private void fnc02(){
        Obj03 p3;
        p3=items.get(mCurrentItemPosition);
        fnc05(p3);
    }


    //================================================


    private void fnc05(Obj03 p3)
    {
        String txt="¿Esta seguro de eliminar a "+p3.getF03()+"?";
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage(txt);
        dialog.setIcon(R.drawable.icon5);
        dialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dta03.deleteById(p3.getF01());
                String s= obj02.getF20();
                int n= Integer.parseInt(s);
                n=n-1;
                obj02.setF20(String.valueOf(n));
                dta02.update(obj02);
                fnc00();
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


    private void fnc06(){
        dta03.deleteByMeeting(obj02.getF01());
        obj02.setF20("0");
        dta02.update(obj02);
        fnc00();
    }





}