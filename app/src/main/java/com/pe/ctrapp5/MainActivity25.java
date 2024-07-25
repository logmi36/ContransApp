package com.pe.ctrapp5;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.pe.ctrapp5.Adapter.Dap13;
import com.pe.ctrapp5.Data.Dta04;
import com.pe.ctrapp5.Data.Dta07;
import com.pe.ctrapp5.Data.Dta13;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Listener.RecyclerViewClickListener;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj04;
import com.pe.ctrapp5.Model.Obj05;
import com.pe.ctrapp5.Model.Obj07;
import com.pe.ctrapp5.Model.Obj13;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity25 extends AppCompatActivity {

    public RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton fr25r02;
    SwipeRefreshLayout fr25r03;


    Obj01 obj01;
    int mCurrentItemPosition;

    Sender sender;

    List<Obj13> items;
    Obj13 item;
    Dap13 dap13;

    String txtSrh;

    Dta13 dta13;
    Dta04 dta04;
    Dta07 dta07;
    List<Obj04> ml4;
    List<Obj07> ml7;

    Bitmap bitmap;

    BarcodeEncoder qrgEncoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main25);

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");

        fr25r02 = (FloatingActionButton) findViewById(R.id.fr25r02);
        recyclerView = (RecyclerView) findViewById(R.id.fr25r01);
        fr25r03=(SwipeRefreshLayout) findViewById(R.id.fr25r03);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        registerForContextMenu(recyclerView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Mis Citas");

        sender = sender.getInstance();

        fr25r02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc07();
            }
        });

        fr25r03.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fnc02(obj01.getF01());
            }
        });


        dta13 =new Dta13(this);
        dta04 =new Dta04(this);
        dta07 =new Dta07(this);

        items=new ArrayList<Obj13>();
        item= new Obj13();

        //items=dta13.ListbyId("");
        //fnc00();

    }


    @Override
    public void onResume(){
        super.onResume();
        fr25r03.setRefreshing(false);
        fnc02(obj01.getF01());
        Log.e("Activity====", "Activity 25");
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
        }

        if(id==R.id.mn8r2){
            fnc07();
        }

        if(id==R.id.mn8r3){
            fnc02(obj01.getF01());
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

        dap13 = new Dap13(this, items, listener);

        dap13.setOnLongItemClickListener(new Dap13.onLongItemClickListener() {
            @Override
            public void ItemLongClicked(View v, int position) {
                mCurrentItemPosition = position;
                v.showContextMenu();
            }
        });

        recyclerView.setAdapter(dap13);

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
        waitingDialog.setMessage("Buscando ...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(cod, "","","","","");

        Log.e("e__l", item.toString());
        sender.connect().fnc24(item).enqueue(new Callback<List<Obj13>>() {
            @Override
            public void onResponse(Call<List<Obj13>> call, Response<List<Obj13>> res) {
                waitingDialog.hide();
                fr25r03.setRefreshing(false);
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados 😢");
                    return;
                }
                fnc03(res.body());
            }

            @Override
            public void onFailure(Call<List<Obj13>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc03(List<Obj13> l3){

        items=l3;
        dta13.deleteByF01(item.getF01());
        for(Obj13 p11 : l3){
            dta13.insert(p11);
        }

        fnc00();

    }

    private void fnc04(String cod) {
        //search local database
        Toast.makeText(this,  "Modo local 👀", Toast.LENGTH_SHORT).show();
        items=dta13.ListbyId(cod);
        fnc00();
    }


    private void fnc06(Obj13 im){

        Intent intent = new Intent(this, MainActivity26.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj13",im);
        startActivity(intent);

    }

    private void fnc07(){

        Intent intent = new Intent(this, MainActivity27.class);
        intent.putExtra("obj01",obj01);
        startActivity(intent);

    }

    private void fnc08(){

        String codCita=items.get(mCurrentItemPosition).getF01();

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Esta seguro de eliminar la cita " + codCita);
        dialog.setIcon(R.drawable.icon5);
        dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fnc09(codCita);
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

    private void fnc09(String codCita){

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

        DocItem item = new DocItem(codCita, "","","","","");

        sender.connect().fnc28(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                fr25r03.setRefreshing(false);
                if (res.body().getNum().equals("0")) {
                    ShowMessage("Hubo un error en la eliminacion 😢");
                    return;
                }
                fnc10();
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });

    }

    private void fnc10(){
        Toast.makeText(this, "Cita eliminada con exito 😉", Toast.LENGTH_SHORT).show();
        fnc02(obj01.getF01());
    }


    //=================================================================================
    //=================================================================================
    //=================================================================================
    //=================================================================================


    private void fnc11() {

        ml4=dta04.List(obj01.getF01());

        String[] l5 = new String[ml4.size()];

        for(int i = 0; i < ml4.size(); i++)
            l5[i] = ml4.get(i).getF02();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar vehiculo");
        builder.setIcon(R.drawable.icon4);
        builder.setItems(l5, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                fnc011(item);
            }
        });
        builder.show();
    }


    private void fnc011(int n) {
        Obj04 m4;
        m4=ml4.get(n);
        fnc13(m4);
    }


    //================================================================================================================================================



    private void fnc13(Obj04 p4){

        ml7=dta07.List("",obj01.getF01());

        String[] l7 = new String[ml7.size()];

        for(int i = 0; i < ml7.size(); i++)
            l7[i] = ml7.get(i).getF03();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar conductor");
        builder.setIcon(R.drawable.icon4);
        builder.setItems(l7, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                fnc14(p4,item);
            }
        });
        builder.show();
    }


    private void fnc14(Obj04 p4, int n) {

        Obj07 m7=ml7.get(n);
        fnc12(p4,m7);
    }






    private void fnc12(Obj04 p4, Obj07 p7){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("No existe conexion a internet 😢");
            return;
        }

        String codCita=items.get(mCurrentItemPosition).getF01();

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando ...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(codCita, p4.getF01(),p7.getF01(),p7.getF04(), p4.getF02(), obj01.getF01());

        sender.connect().fnc29(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                fr25r03.setRefreshing(false);
                if (res.body().getNum().equals("0")) {
                    ShowMessage("Hubo un error en la actualización 😢");
                    return;
                }
                fnc13();
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });

    }

    private void fnc13(){
        Toast.makeText(this, "Cita actualizada con exito 😉", Toast.LENGTH_SHORT).show();
        fnc02(obj01.getF01());
    }


    private void fnc14() {

        Log.e("-->", items.get(mCurrentItemPosition).toString());

        String codCita=items.get(mCurrentItemPosition).getF01();
        String codQR=items.get(mCurrentItemPosition).getF18();

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Codigo de cita " + codCita);

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment10, null);

        final ImageView fr07r01 = login_layout.findViewById(R.id.fr10r01);

        //qrgEncoder = new BarcodeEncoder(codQR, null, BarcodeEncoder.Type.TEXT, dimen);

        try {

            qrgEncoder = new BarcodeEncoder();

            BitMatrix bitMatrix= qrgEncoder.encode(codQR, BarcodeFormat.QR_CODE,400,400);

            int w = bitMatrix.getWidth();
            int h = bitMatrix.getHeight();
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    pixels[y * w + x] = bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE;
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, w, 0, 0, w, h);

            fr07r01.setImageBitmap(bitmap);

        } catch (WriterException e) {
            ShowMessage("Whoops, algo fue mal! 😢");
            return;
        }


        dialog.setView(login_layout);
        dialog.setIcon(R.drawable.icon5);

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
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
            fnc08();
        }

        if(id==R.id.mn2r2){
            fnc11();
        }

        if(id==R.id.mn2r3){
            fnc14();
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