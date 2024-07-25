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
import com.pe.ctrapp5.Adapter.Dap02;
import com.pe.ctrapp5.Data.Dta02;
import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj02;

import java.util.List;


public class MainActivity04 extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    Dap02 dap02;
    Dta02 dta02;
    List<Obj02> items;

    Obj02 item;
    Obj01 obj01;

    private int mCurrentItemPosition;

    FloatingActionButton fr04r02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main04);


        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");

        fr04r02 = (FloatingActionButton) findViewById(R.id.fr04r02);
        recyclerView = (RecyclerView) findViewById(R.id.fr04r01);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        registerForContextMenu(recyclerView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Reuniones");

        fr04r02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc03();
            }
        });

        item= new Obj02();

        dta02 = new Dta02(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        fnc00();
        Log.e("Activity====", "Activity 04");
    }


    public  void fnc00(){

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                item=items.get(position);
                fnc01(item);
            }
        };

        items=dta02.List();
        dap02 = new Dap02(this, items, listener);

        dap02.setOnLongItemClickListener(new Dap02.onLongItemClickListener() {
            @Override
            public void ItemLongClicked(View v, int position) {
                mCurrentItemPosition = position;
                v.showContextMenu();
            }
        });

        recyclerView.setAdapter(dap02);

    }


    private void fnc01(Obj02 im){

        Intent intent = new Intent(this, MainActivity05.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj02",im);
        startActivity(intent);

    }

    private void fnc03(){
        item.setF01("0");
        Intent intent = new Intent(this, MainActivity06.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj02",item);
        startActivity(intent);

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
            fnc02();
        }

        if(id==R.id.mn2r2){
            fnc05();
        }

        if(id==R.id.mn2r3){
            fnc06();
        }

        return true;
    }

    private void fnc02(){
        Obj02 p3;
        p3=items.get(mCurrentItemPosition);
        fnc04(p3.getF03());
    }


    private void fnc04(String f02)
    {
        String message="¿Esta seguro de eliminar la reunion "+f02+"?";
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage(message);
        dialog.setIcon(R.drawable.icon5);
        dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Obj02 p3;
                p3=items.get(mCurrentItemPosition);
                dta02.deleteById(p3.getF01());
                fnc00();
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

    private void fnc05(){
        Obj02 p3;
        p3=items.get(mCurrentItemPosition);
        Intent intent = new Intent(this, MainActivity06.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj02",p3);
        startActivity(intent);
    }


    //================================================================================================================================================


    private void fnc06()
    {
        Obj02 p3;
        p3=items.get(mCurrentItemPosition);
        String message="autor : " + p3.getF17() + "\n" + "registro : " + p3.getF18();

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage(message);
        dialog.setIcon(R.drawable.icon5);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }




}