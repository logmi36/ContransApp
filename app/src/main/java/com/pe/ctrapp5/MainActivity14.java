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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pe.ctrapp5.Adapter.Dap16;
import com.pe.ctrapp5.Data.Dta04;
import com.pe.ctrapp5.Data.Dta07;
import com.pe.ctrapp5.Data.Dta08;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj04;
import com.pe.ctrapp5.Model.Obj07;
import com.pe.ctrapp5.Model.Obj08;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity14 extends AppCompatActivity {


    FloatingActionButton f14f15;

    ImageView f14f01;

    TextView f14f02;
    TextView f14f03;
    TextView f14f04;


    TextView f14f06;
    TextView f14f07;
    TextView f14f08;
    TextView f14f09;

    TextView f14f16;


    Obj01 obj01;
    Obj07 obj07;
    Obj08 item;

    Dta08 dta08;
    Dta07 dta07;
    Dta04 dta04;

    List<Obj04> ml4;

    Dap16 dap16;

    private Sender sender;

    String imgCod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");
        obj07=(Obj07)intent.getSerializableExtra("obj07");

        Toolbar toolbar =(Toolbar) findViewById(R.id.f14t01);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(obj07.getF05());

        f14f02= (TextView) findViewById(R.id.f14f02);
        f14f03= (TextView) findViewById(R.id.f14f03);
        f14f04= (TextView) findViewById(R.id.f14f04);

        f14f06= (TextView) findViewById(R.id.f14f06);
        f14f07= (TextView) findViewById(R.id.f14f07);
        f14f08= (TextView) findViewById(R.id.f14f08);
        f14f09= (TextView) findViewById(R.id.f14f09);

        f14f16= (TextView) findViewById(R.id.f14f16);

        f14f01= (ImageView) findViewById(R.id.f14f01);
        f14f15= (FloatingActionButton) findViewById(R.id.f14f15);

        //imagview click
        f14f01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc17();
            }
        });

        //fab click
        f14f15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc16();
            }
        });


        f14f07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fnc18();
            }
        });

        f14f07.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f14f07.getRight() - f14f07.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        fnc251();
                        return true;
                    }
                }
                return false;
            }
        });

        f14f16.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f14f16.getRight() - f14f16.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        fnc19();
                        return true;
                    }
                }
                return false;
            }
        });


        f14f03.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f14f03.getRight() - f14f03.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        fnc22();
                        return true;
                    }
                }
                return false;
            }
        });
        item=new Obj08();
        sender = sender.getInstance();
        dta08 =new Dta08(this);
        dta04 =new Dta04(this);
        dta07 =new Dta07(this);

        fnc02(obj07.getF01());
        imgCod="000000";
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.e("Activity====", "Activity 14");
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
        getMenuInflater().inflate(R.menu.main12, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Log.i("id____=",String.valueOf(id));

        if(id==R.id.mn12r1){
            fnc02(obj07.getF01());
        }

        if(id==R.id.mn12r2){
            //vehiculos
            fnc40();
        }


        if(id==16908332){
            super.onBackPressed();
        }

        return true;
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

        //Log.e("data==", data.toString());

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case 1:
                    //tomarfotografia
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    try {
                        bitmap=getResizedBitmap(bitmap, 600);
                        bitmap=cropToSquare(bitmap);
                        String bs=BitMapToString(bitmap);
                        fnc15(bs);
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
                    thumbnail=getResizedBitmap(thumbnail, 800);
                    thumbnail=cropToSquare(thumbnail);
                    Log.e("pat==", picturePath+"");
                    String bs= BitMapToString(thumbnail);
                    fnc15(bs);
                    break;

                default:
                    break;

            }

        }
    }


    public String BitMapToString(Bitmap userImage1) {
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

        DocItem m1 = new DocItem(item.getF01(), obj01.getF01(),img,"","","");
        sender.connect().fnc13(m1).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                if (res.body()==null) {
                    ShowMessage("Ha surgido un error 😢");
                    return;
                }
                //item.setF12(res.body());
                item.setF16(img);
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



    private void fnc22(Obj08 im){

        dta08.deleteByF01(item.getF01());
        dta08.insert(im);

        byte[] decodedString = Base64.decode(im.getF16(), Base64.DEFAULT);
        Bitmap mbitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        f14f01.setImageBitmap(mbitmap);

        Bitmap bmp=getResizedBitmap(mbitmap, 150);
        String bs= BitMapToString(bmp);
        dta07.deleteByF01(obj07.getF01());
        obj07.setF09(bs);
        dta07.insert(obj07);

        Toast.makeText(getBaseContext(), "Imagen actualizada con exito", Toast.LENGTH_LONG).show();

    }

    //    ===================================================================

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

        DocItem item = new DocItem(cod, obj01.getF01(),"","","","");
        sender.connect().fnc12(item).enqueue(new Callback<Obj08>() {
            @Override
            public void onResponse(Call<Obj08> call, Response<Obj08> res) {
                waitingDialog.hide();
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                fnc03(res.body());
            }

            @Override
            public void onFailure(Call<Obj08> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }

    private void fnc03(Obj08 m8){

        item=m8;

        Log.e("item====", m8.toString());

        byte[] decodedString = Base64.decode(item.getF16(), Base64.DEFAULT);
        Bitmap mbitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        f14f01.setImageBitmap(mbitmap);

        f14f02.setText(item.getF03());
        f14f03.setText(item.getF04());
        f14f04.setText(item.getF05());
        //f14f05.setText(item.getF06());
        f14f06.setText(item.getF07());

        f14f07.setText(item.getF09());
        f14f08.setText(item.getF10());
        f14f09.setText(item.getF11());
//        f14f10.setText(item.getF12());
//        f14f11.setText(item.getF13());
//        f14f12.setText(item.getF14());
//        f14f13.setText(item.getF15());
//
//        f14f14.setText(item.getF01());
        f14f16.setText(item.getF17());


        dta08.deleteByF01(item.getF01());
        dta08.insert(item);

    }


    private void fnc04() {
        //search local database
        Toast.makeText(this,  "Modo local 👀", Toast.LENGTH_SHORT).show();
        Obj08 im=dta08.GetById(obj07.getF01());
        fnc03(im);
    }


    private void fnc17(){

        DocItem im;
        im= new DocItem(item.getF05(), item.getF16(), "", "", "", "");

        Intent intent = new Intent(this, MainActivity10.class);
        intent.putExtra("item",im);
        startActivity(intent);
    }


    private void fnc18(){

        if(item.getF09().isEmpty()){
            return;
        }

        Obj04 obj04;
        obj04 = new Obj04();
        obj04.setF01(item.getF08());
        obj04.setF02(item.getF09());

        Intent intent = new Intent(this, MainActivity09.class);
        intent.putExtra("obj04",obj04);
        intent.putExtra("obj01",obj01);
        startActivity(intent);
    }


    //=======================================================================


    private void fnc19(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Editar numero telefonico");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        fg05f01.setHint("Numero Telefonico");
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
                fnc20(p1);
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



    private void fnc20(String cod){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("No existe conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando : "+ cod + "...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem("1", cod,obj07.getF01(),obj01.getF01(),"","");
        sender.connect().fnc31(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                if (res.body().getNum().equals("0")) {
                    ShowMessage("Ocurrio un problema");
                    return;
                }
                fnc21(cod);
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }



    private void fnc21(String txt){

        obj07.setF17(txt);
        dta07.deleteByF01(obj07.getF01());
        dta07.insert(obj07);

        f14f16.setText(txt);

    }

    private void fnc40(){

        Intent intent = new Intent(this, MainActivity31.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj07",obj07);
        startActivity(intent);

    }


    //=======================================================================


    private void fnc22(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Editar Nro de brevete");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        fg05f01.setHint("Numero de brevete");
        fg05f01.setText(obj07.getF04());
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
                    ShowMessage("Por favor, ingrese un numero de brevete");
                    return;
                }
                fnc23(p1);
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



    private void fnc23(String cod){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("No existe conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Buscando : "+ cod + "...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem("2", cod,obj07.getF01(),obj01.getF01(),"","");
        sender.connect().fnc31(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                if (res.body().getNum().equals("0")) {
                    ShowMessage("Ocurrio un problema");
                    return;
                }
                fnc24(cod);
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }



    private void fnc24(String txt){

        obj07.setF04(txt);
        dta07.deleteByF01(obj07.getF01());
        dta07.insert(obj07);

        f14f03.setText(txt);

    }



    //=======================================================================


    private void fnc25(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Editar");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        fg05f01.setHint("Numero de placa");
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
                if(p1.equals("")){
                    ShowMessage("Ingrese un numero de placa");
                    return;
                }
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

    private void fnc26(String cod){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("No existe conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Buscando : "+ cod + "...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(cod,"","","","","");
        sender.connect().fnc05(item).enqueue(new Callback<List<Obj04>>() {
            @Override
            public void onResponse(Call<List<Obj04>> call, Response<List<Obj04>> res) {
                waitingDialog.hide();
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados 😢");
                    return;
                }
                fnc27(cod, res.body());
            }

            @Override
            public void onFailure(Call<List<Obj04>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }



    private void fnc27(String cod, List<Obj04> l4){

        if(l4.size()==0){
            fnc31(cod);
        }
        if(l4.size()==1){
            fnc29(l4.get(0));
        }
        if(l4.size()>1){
            fnc28(l4);
        }

    }


    private void fnc28(List<Obj04> m4){

        dap16 = new Dap16(this,m4);

        View view = getLayoutInflater().inflate(R.layout.fragment09, null);
        ListView fr09r01 = view.findViewById(R.id.fr09r01);
        fr09r01.setAdapter(dap16);

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
                fnc29(dap16.getItem(i));
                //Log.e("ii===========",String.valueOf(i));
            }
        });

        dialog.show();
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

        item.setF08(m4.getF01());
        item.setF09(m4.getF02());
        item.setF10(m4.getF05());
        item.setF11(m4.getF04());

        dta08.deleteByF01(item.getF01());
        dta08.insert(item);

        fnc03(item);

    }


    private void fnc31(String txt){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("No existe conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando " + txt +" ...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem m1 = new DocItem(obj01.getF13(), txt, item.getF01(), obj01.getF01(),"","");
        sender.connect().fnc32(m1).enqueue(new Callback<Obj04>() {
            @Override
            public void onResponse(Call<Obj04> call, Response<Obj04> res) {
                waitingDialog.hide();
                if (res.body()==null) {
                    ShowMessage("Ocurrio un problema 😢");
                    return;
                }
                fnc30(res.body());
            }

            @Override
            public void onFailure(Call<Obj04> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
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