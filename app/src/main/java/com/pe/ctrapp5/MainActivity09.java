package com.pe.ctrapp5;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pe.ctrapp5.Adapter.Dap16;
import com.pe.ctrapp5.Adapter.Dap17;
import com.pe.ctrapp5.Data.Dta04;
import com.pe.ctrapp5.Data.Dta05;
import com.pe.ctrapp5.Data.Dta07;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj04;
import com.pe.ctrapp5.Model.Obj05;
import com.pe.ctrapp5.Model.Obj07;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity09 extends AppCompatActivity {


    FloatingActionButton f09f11;

    ImageView f09f10;

    TextView f09f01;
    TextView f09f02;
    TextView f09f03;
    TextView f09f04;
    TextView f09f05;

    TextView f09f06;
    TextView f09f07;
    TextView f09f08;
    TextView f09f09;

    Obj01 obj01;
    Obj04 obj04;
    Obj05 item;

    Dta05 dta05;
    Dta04 dta04;
    Dta07 dta07;

    Dap17 dap17;

    private Sender sender;

    String imgCod;

    final CharSequence[] lopn1 = { "(No definido)", "Tracto","Carreta","Auto" };
    final CharSequence[] lopn2 = { "(No definido)", "1 Eje","2 Ejes", "3 Ejes","4 Ejes","5 Ejes" };

    SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main09);

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");
        obj04=(Obj04)intent.getSerializableExtra("obj04");

        Toolbar toolbar =(Toolbar) findViewById(R.id.f09t01);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(obj04.getF02());

        f09f01= (TextView) findViewById(R.id.f09f01);
        f09f02= (TextView) findViewById(R.id.f09f02);
        f09f03= (TextView) findViewById(R.id.f09f03);
        f09f04= (TextView) findViewById(R.id.f09f04);
        f09f05= (TextView) findViewById(R.id.f09f05);
        f09f06= (TextView) findViewById(R.id.f09f06);
        f09f07= (TextView) findViewById(R.id.f09f07);
        f09f08= (TextView) findViewById(R.id.f09f08);
        f09f09= (TextView) findViewById(R.id.f09f09);

        f09f10= (ImageView) findViewById(R.id.f09f10);
        f09f11= (FloatingActionButton) findViewById(R.id.f09f11);

        //imagview click
        f09f10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc17();
            }
        });

        //fab click
        f09f11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fnc01();
                fnc16();
            }
        });

        f09f01.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f09f01.getRight() - f09f01.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        fnc05();
                        return true;
                    }
                }
                return false;
            }
        });

        f09f02.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f09f02.getRight() - f09f02.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        fnc08();
                        return true;
                    }
                }
                return false;
            }
        });

        f09f03.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f09f03.getRight() - f09f03.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        fnc11();
                        return true;
                    }
                }
                return false;
            }
        });

        f09f04.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f09f04.getRight() - f09f04.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        fnc14();
                        return true;
                    }
                }
                return false;
            }
        });


        f09f06.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f09f06.getRight() - f09f06.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        fnc25();
                        return true;
                    }
                }
                return false;
            }
        });


        item=new Obj05();
        sender = sender.getInstance();
        dta05 =new Dta05(this);
        dta04 =new Dta04(this);
        dta07 =new Dta07(this);



    }


    @Override
    public void onResume(){
        super.onResume();

        Log.e("obj04===============", obj04.toString());

        fnc02(obj04.getF01());

        dta04.deleteById(obj04.getF01());
        dta04.insert(obj04);


        imgCod="000000";

        Log.e("Activity====", "Activity 09");
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }



    //================================================================================================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main09, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Log.i("id____=",String.valueOf(id));

        if(id==R.id.mn9r1){
            fnc02(obj04.getF01());
        }

        if(id==R.id.mn9r2){
            fnc12();
        }

        if(id==R.id.mn9r3){

        }

        if(id==16908332){
            super.onBackPressed();
        }

        return true;
    }


    private void fnc12(){

        Intent intent = new Intent(this, MainActivity11.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj04",obj04);
        startActivity(intent);

    }


    //====================================================================


    private void fnc16() {
        final CharSequence[] options = { "Tomar fotografía", "Escoger de galeria","Cancelar" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar...");
        builder.setIcon(R.drawable.icon5);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        //take photo
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, 1);
                        break;
                    case 1:
                        // Choose from Gallery
                        Intent intent2 = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent2, 2);
                        break;

                    case 2:
                        // Cancel
                        dialog.dismiss();
                        break;
                    default:
                        break;
                }
            }
        });
        builder.show();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case 1:
                    //tomarfotografia
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    try {
                        bitmap=getResizedBitmap(bitmap, 600);
                        bitmap=cropToSquare(bitmap);
                        BitMapToString(bitmap);
                    } catch (Exception e) {
                        ShowMessage(e.getMessage());
                        e.printStackTrace();
                    }

                    break;

                case 2:
                    //seleccionar de galeria
                    Uri selectedImage = data.getData();
                    String[] filePath = { MediaStore.Images.Media.DATA };
                    Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePath[0]);
                    String picturePath = c.getString(columnIndex);
                    c.close();
                    Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                    thumbnail=getResizedBitmap(thumbnail, 600);
                    thumbnail=cropToSquare(thumbnail);
                    Log.e("pat==", picturePath+"");
                    BitMapToString(thumbnail);
                    break;

                default:
                    break;

            }

        }
    }


    public void BitMapToString(Bitmap userImage1) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        userImage1.compress(Bitmap.CompressFormat.PNG, 60, baos);
        byte[] b = baos.toByteArray();
        String p1 = Base64.encodeToString(b, Base64.DEFAULT);
        fnc15(p1);
    }

    public String BitMapToStringB(Bitmap userImage1) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        userImage1.compress(Bitmap.CompressFormat.PNG, 60, baos);
        byte[] b = baos.toByteArray();
        String p1 = Base64.encodeToString(b, Base64.DEFAULT);
        return  p1;
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static Bitmap cropToSquare(Bitmap bitmap){
        int width  = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = (height > width) ? width : height;
        int newHeight = (height > width)? height - ( height - width) : height;
        int cropW = (width - height) / 2;
        cropW = (cropW < 0)? 0: cropW;
        int cropH = (height - width) / 2;
        cropH = (cropH < 0)? 0: cropH;
        Bitmap cropImg = Bitmap.createBitmap(bitmap, cropW, cropH, newWidth, newHeight);

        return cropImg;
    }



    private void fnc15(String img){


        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem m1 = new DocItem(item.getF01(), img,"","","","");
        sender.connect().fnc07(m1).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                //Log.e("e___e", res.body().toString());
                if (res.body()==null) {
                    ShowMessage("Algo fue mal 😢");
                    return;
                }
                if (res.body().getNum().equals("000000")) {
                    ShowMessage("Surgio un error en el envio de informacion");
                    return;
                }
                item.setF12(res.body().getNum());
                item.setF13(img);
                fnc22(item);
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }



    private void fnc22(Obj05 im){

        dta05.deleteByF01(item.getF01());
        dta05.insert(im);

        byte[] decodedString = Base64.decode(im.getF13(), Base64.DEFAULT);
        Bitmap mbitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        f09f10.setImageBitmap(mbitmap);

        Bitmap bmp=getResizedBitmap(mbitmap, 60);
        String bs= BitMapToStringB(bmp);
        dta04.deleteById(obj04.getF01());
        obj04.setF07(bs);
        dta04.insert(obj04);

        Toast.makeText(getBaseContext(), "Imagen actualizada con exito", Toast.LENGTH_LONG).show();

    }



    //=======================================================================




    private void fnc17(){

        DocItem im;
        im= new DocItem(item.getF02(), item.getF13(), "", "", "", "");

        Intent intent = new Intent(this, MainActivity10.class);
        intent.putExtra("item",im);
        startActivity(intent);
    }


    private void fnc02(String cod){
        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            fnc04();
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Buscando...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(cod, "","","","","");
        sender.connect().fnc06(item).enqueue(new Callback<Obj05>() {
            @Override
            public void onResponse(Call<Obj05> call, Response<Obj05> res) {
                waitingDialog.hide();
                Log.e("e___e", res.body().toString());
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                fnc03(res.body());
            }

            @Override
            public void onFailure(Call<Obj05> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }

    private void fnc03(Obj05 m5){

        item=m5;

        Log.e("item====", m5.toString());

        byte[] decodedString = Base64.decode(item.getF13(), Base64.DEFAULT);
        Bitmap mbitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        f09f10.setImageBitmap(mbitmap);

        f09f01.setText(item.getF09());
        f09f02.setText(item.getF08());
        f09f03.setText(item.getF10());
        f09f04.setText(item.getF11());
        f09f05.setText(item.getF03());
        f09f06.setText(item.getF04());
        f09f07.setText(item.getF05());
        f09f08.setText(item.getF06());
        f09f09.setText(item.getF07());

        //1 marca
        //2 Tipo
        //3 Registro MTC
        //4 Ejes
        //5 Fecha
        //6 Chofer
        //7 Brevete
        //8 Estado
        //9 Fecha

        dta05.deleteByF01(item.getF01());
        dta05.insert(item);

    }


    private void fnc04() {
        //search local database
        Toast.makeText(this,  "Modo local 👀", Toast.LENGTH_SHORT).show();
        Obj05 im=dta05.GetById(obj04.getF01());
        fnc03(im);
    }

    //=======================================================================




    private void fnc05(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Editar");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        fg05f01.setHint("Marca del vehiculo");
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
                    ShowMessage("Por favor, ingrese un numero telefonico");
                    return;
                }
                fnc06(p1);
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


    private void fnc06(String txt){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("No existe conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando ....");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem("4", obj04.getF01(),txt,obj01.getF01(),"","");
        sender.connect().fnc31(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                if (res.body().getNum().equals("0")) {
                    ShowMessage("Ocurrio un problema");
                    return;
                }
                fnc07(txt);
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }



    private void fnc07(String txt){

        item.setF09(txt);
        fnc03(item);

    }



    //=======================================================================



    private void fnc08() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar...");
        builder.setIcon(R.drawable.icon5);
        builder.setItems(lopn1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                fnc09(id);
            }
        });
        builder.show();
    }


    private void fnc09(int cod){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("No existe conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando ....");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DecimalFormat df = new DecimalFormat("00");

        DocItem item = new DocItem("5", obj04.getF01(),df.format(cod),obj01.getF01(),"","");
        sender.connect().fnc31(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                if (res.body().getNum().equals("0")) {
                    ShowMessage("Ocurrio un problema");
                    return;
                }
                fnc10(lopn1[cod].toString());
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }



    private void fnc10(String txt){

        item.setF08(txt);
        fnc03(item);

    }


    //=======================================================================


    private void fnc11(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Editar");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        fg05f01.setHint("Numero Registro MTC");
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
                    ShowMessage("Por favor, ingrese un numero telefonico");
                    return;
                }
                fnc12(p1);
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


    private void fnc12(String txt){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("No existe conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando ....");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem("6", obj04.getF01(),txt,obj01.getF01(),"","");
        sender.connect().fnc31(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                if (res.body().getNum().equals("0")) {
                    ShowMessage("Ocurrio un problema");
                    return;
                }
                fnc13(txt);
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }



    private void fnc13(String txt){

        obj04.setF05(txt);
        dta04.update(obj04);

        item.setF10(txt);
        fnc03(item);

    }


    //=======================================================================



    private void fnc14() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar...");
        builder.setIcon(R.drawable.icon5);
        builder.setItems(lopn2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                fnc15(id);
            }
        });
        builder.show();
    }


    private void fnc15(int cod){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("No existe conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando ....");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DecimalFormat df = new DecimalFormat("00");

        DocItem item = new DocItem("7", obj04.getF01(),df.format(cod),obj01.getF01(),"","");
        sender.connect().fnc31(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                if (res.body().getNum().equals("0")) {
                    ShowMessage("Ocurrio un problema");
                    return;
                }
                fnc16(lopn2[cod].toString());
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }



    private void fnc16(String txt){

        item.setF11(txt);
        fnc03(item);

    }



    //=======================================================================



    private void fnc25(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Conductor");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        fg05f01.setHint("Nombres o DNI");
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
                p1=p1.replace(" ","");
                p1=p1.replace("\n","");
                p1=p1.toUpperCase(Locale.ROOT);
                fnc26(p1);
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



    private void fnc26(String txt){

        List<Obj07> l7;
        l7=dta07.List(txt, obj01.getF01());

        fnc27(txt, l7);
    }



    private void fnc27(String cod, List<Obj07> l7){

        if(l7.size()==0){
            ShowMessage("No se encontraron resultados");
        }
        if(l7.size()==1){
            fnc29(l7.get(0));
        }
        if(l7.size()>1){
            fnc28(l7);
        }

    }


    private void fnc28(List<Obj07> l7){

        dap17 = new Dap17(this,l7);

        View view = getLayoutInflater().inflate(R.layout.fragment09, null);
        ListView fr09r01 = view.findViewById(R.id.fr09r01);
        fr09r01.setAdapter(dap17);

        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar...");
        builder.setIcon(R.drawable.icon5);
        builder.setView(view);
        dialog= builder.create();

        builder.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        fr09r01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog.dismiss();
                fnc29(dap17.getItem(i));
                //Log.e("ii===========",String.valueOf(i));
            }
        });

        dialog.show();
    }


    private void fnc29(Obj07 m7){

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

        DocItem m1 = new DocItem("8", item.getF01(),m7.getF01(),obj01.getF01(),obj01.getF13(),"");
        sender.connect().fnc31(m1).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                if (res.body().getNum().equals("0")) {
                    ShowMessage("Ocurrio un problema");
                    return;
                }
                fnc30(m7);
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }



    private void fnc30(Obj07 m7){

        Log.e("m7========", m7.toString());

        obj04.setF09(m7.getF03());
        dta04.update(obj04);

        String dt1 = df1.format(new Date());

        item.setF04(m7.getF03());
        item.setF05(m7.getF04());
        item.setF06("H");
        item.setF07(dt1);

        fnc03(item);

    }




    //=======================================================================


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