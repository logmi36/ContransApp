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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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
import com.pe.ctrapp5.Data.Dta10;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj09;
import com.pe.ctrapp5.Model.Obj10;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity16 extends AppCompatActivity {

    Obj01 obj01;
    Obj09 obj09;
    Obj10 item;

    Dta10 dta10;

    private Sender sender;


    ImageView f16f01;

    TextView f16f02;
    TextView f16f03;
    TextView f16f04;
    TextView f16f05;
    TextView f16f06;
    TextView f16f07;
    TextView f16f08;
    TextView f16f09;
    TextView f16f10;
    TextView f16f11;
    TextView f16f12;
    TextView f16f13;
    TextView f16f14;
    TextView f16f15;
    TextView f16f16;
    TextView f16f17;
    TextView f16f18;
    TextView f16f19;
    TextView f16f20;
    TextView f16f21;
    FloatingActionButton f16f23;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);

        f16f01=(ImageView) findViewById(R.id.f16f01);

        f16f02=(TextView) findViewById(R.id.f16f02);
        f16f03=(TextView) findViewById(R.id.f16f03);
        f16f04=(TextView) findViewById(R.id.f16f04);
        f16f05=(TextView) findViewById(R.id.f16f05);
        f16f06=(TextView) findViewById(R.id.f16f06);
        f16f07=(TextView) findViewById(R.id.f16f07);
        f16f08=(TextView) findViewById(R.id.f16f08);
        f16f09=(TextView) findViewById(R.id.f16f09);
        f16f10=(TextView) findViewById(R.id.f16f10);
        f16f11=(TextView) findViewById(R.id.f16f11);
        f16f12=(TextView) findViewById(R.id.f16f12);
        f16f13=(TextView) findViewById(R.id.f16f13);
        f16f14=(TextView) findViewById(R.id.f16f14);
        f16f15=(TextView) findViewById(R.id.f16f15);
        f16f16=(TextView) findViewById(R.id.f16f16);
        f16f17=(TextView) findViewById(R.id.f16f17);
        f16f18=(TextView) findViewById(R.id.f16f18);
        f16f19=(TextView) findViewById(R.id.f16f19);
        f16f20=(TextView) findViewById(R.id.f16f20);
        f16f21=(TextView) findViewById(R.id.f16f21);

        f16f23=(FloatingActionButton) findViewById(R.id.f16f23);


        f16f01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fnc20();
            }
        });


        //fab click
        f16f23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc16();
            }
        });

        Intent intent = getIntent();

        obj01 =(Obj01) intent.getSerializableExtra("obj01");
        obj09 =(Obj09) intent.getSerializableExtra("obj09");

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle(obj09.getF03()+" " + obj09.getF04());
//        actionBar.setDisplayHomeAsUpEnabled(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.f16t01);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(obj09.getF03()+" " + obj09.getF04());






        dta10= new Dta10(this);
        item= new Obj10();
        sender = sender.getInstance();

    }


    @Override
    public void onResume(){
        super.onResume();

        fnc01(obj09.getF01());
        Log.e("Activity====", "Activity 16");

    }




    //    ======================================================



    private void fnc01(String img){


        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem m1 = new DocItem(obj09.getF01(), "","","","","");

        Log.e("m1====", m1.toString());

        sender.connect().fnc15(m1).enqueue(new Callback<Obj10>() {
            @Override
            public void onResponse(Call<Obj10> call, Response<Obj10> res) {
                waitingDialog.hide();
                //Log.e("e___e", res.body().toString());
                if (res.body()==null    ) {
                    ShowMessage("Surgio un problema 😢😢");
                    return;
                }

                fnc02(res.body());
            }

            @Override
            public void onFailure(Call<Obj10> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });

    }

    private void fnc02(Obj10 im){
        item=im;

        byte[] decodedString = Base64.decode(item.getF23(), Base64.DEFAULT);
        Bitmap mbitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        f16f01.setImageBitmap(mbitmap);

        f16f02.setText(item.getF02());
        f16f03.setText(item.getF03());
        f16f04.setText(item.getF04());
        f16f05.setText(item.getF05());
        f16f06.setText(item.getF06());
        f16f07.setText(item.getF08());
        f16f08.setText(item.getF10());
        f16f09.setText(item.getF09());
        f16f10.setText(item.getF07());

        f16f11.setText(item.getF11());
        f16f12.setText(item.getF12());
        f16f13.setText(item.getF13());
        f16f14.setText(item.getF14());
        f16f15.setText(item.getF15());
        f16f16.setText(item.getF16());

        f16f17.setText(item.getF17());
        f16f18.setText(item.getF18());
        f16f19.setText(item.getF19());
        f16f20.setText(item.getF20());
        f16f21.setText(item.getF21());

        dta10.deleteByF01(item.getF01());
        dta10.insert(item);


    }


    //    ======================================================



    private void fnc16() {
        String[] options = {"Tomar fotografía", "Escoger de galeria","Cancelar" };
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

        DocItem m1 = new DocItem(obj09.getF01(), "5025",img,"","","");
        sender.connect().fnc16(m1).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                Log.e("e___e", res.body().toString());
                if (res.body()==null) {
                    ShowMessage("No se encontraron resultados");
                    return;
                }
                if (res.body().getNum().equals("000000")) {
                    ShowMessage("Surgio un error en el envio de informacion");
                    return;
                }
                //item.setF12(res.body());
                item.setF23(img);
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



    private void fnc22(Obj10 im){

        dta10.deleteByF01(item.getF01());
        dta10.insert(im);
        fnc02(im);
        Toast.makeText(getBaseContext(), "Imagen actualizada con exito", Toast.LENGTH_LONG).show();

    }




    //    ===================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main09, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.mn9r1){
            fnc01(obj09.getF01());
        }
        if(id==R.id.mn9r2){
            fnc17();
        }
        if(id==R.id.mn9r3){

        }
        if(id==16908332){
            super.onBackPressed();
        }

        return true;
    }


    //=======================================================================
    private void fnc20(){

        DocItem im = new DocItem(item.getF03() + " " + item.getF04(), item.getF23(),"", "","","");

        Intent intent = new Intent(this, MainActivity10.class);
        intent.putExtra("item",im);
        startActivity(intent);
    }

    private void fnc17(){

        Intent intent = new Intent(this, MainActivity17.class);
        intent.putExtra("obj01",obj01);
        intent.putExtra("obj10",item);
        startActivity(intent);

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