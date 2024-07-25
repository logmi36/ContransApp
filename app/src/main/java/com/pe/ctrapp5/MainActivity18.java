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
import com.pe.ctrapp5.Adapter.Dap14;
import com.pe.ctrapp5.Data.Dta01;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj14;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity18 extends AppCompatActivity  {

    FloatingActionButton f18f09;
    ImageView f18f08;

    TextView f18f01;
    TextView f18f02;
    TextView f18f04;
    TextView f18f05;
    TextView f18f06;
    TextView f18f07;

    TextView f18f10;

    Obj01 obj01;
    Dta01 dta01;

    Dap14 dap14;


    private Sender sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main18);

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");

        Toolbar toolbar =(Toolbar) findViewById(R.id.f18t01);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(obj01.getF02());

        f18f01= (TextView) findViewById(R.id.f18f01);
        f18f02= (TextView) findViewById(R.id.f18f02);
        f18f04= (TextView) findViewById(R.id.f18f04);
        f18f05= (TextView) findViewById(R.id.f18f05);
        f18f06= (TextView) findViewById(R.id.f18f06);
        f18f07= (TextView) findViewById(R.id.f18f07);
        f18f10= (TextView) findViewById(R.id.f18f10);

        f18f08= (ImageView) findViewById(R.id.f18f08);
        f18f09= (FloatingActionButton) findViewById(R.id.f18f09);

        dta01 =new Dta01(this);
        sender= sender.getInstance();

        f18f01.setText(obj01.getF03());
        f18f02.setText(obj01.getF04());
        f18f04.setText(obj01.getF01());
        f18f05.setText(obj01.getF05());
        f18f06.setText(obj01.getF06());
        f18f10.setText(obj01.getF14());

        String cl="";
        for(Integer i=0; i<obj01.getF07().length();i++){
            cl=cl+"*";
        }
        f18f07.setText(cl);

//        Log.e("___size=", String.valueOf( obj01.getF11().length()));
        String base64 =dta01.getImage(obj01.getF01());

        obj01.setF11(base64);

        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap mbitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        f18f08.setImageBitmap(mbitmap);

        f18f01.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f18f01.getRight() - f18f01.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        fnc01();
                        return true;
                    }
                }
                return false;
            }
        });


        f18f02.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f18f02.getRight() - f18f02.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        fnc03();
                        return true;
                    }
                }
                return false;
            }
        });



        f18f05.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f18f05.getRight() - f18f05.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        fnc09();
                        return true;
                    }
                }
                return false;
            }
        });

        f18f06.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f18f06.getRight() - f18f06.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        fnc11();
                        return true;
                    }
                }
                return false;
            }
        });

        f18f07.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f18f07.getRight() - f18f07.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        fnc13();
                        return true;
                    }
                }
                return false;
            }
        });


        f18f10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f18f10.getRight() - f18f10.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        fnc18();
                    }
                }
                return false;
            }
        });

        f18f09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fnc01();
                fnc16();
            }
        });

        //imagview click
        f18f08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc17();
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.e("Activity====", "Activity 18");
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    //===============================================================================================================


    private void fnc01(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Editar");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Escriba sus nombres";
        fg05f01.setHint(txt);
        fg05f01.setText(obj01.getF03());
        dialog.setIcon(R.drawable.icon5);

        dialog.setView(login_layout);

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                String p1= fg05f01.getText().toString();
                p1=clear(p1);
                if(p1.equals("")){
                    ShowMessage("Por favor, ingrese su nombre");
                    return;
                }
                p1=toTitleCase(p1);
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

    private void fnc02(String p1){

        String p2=p1;
        if(p2.split(" ").length>0){
            p2=p2.split(" ")[0];
        }

        String p3=obj01.getF04();
        if(p3.split(" ").length>0){
            p3=p3.split(" ")[0];
        }

        p2=p2+" " + p3;

        obj01.setF02(p2);
        obj01.setF03(p1);
        dta01.update(obj01);
        f18f01.setText(p1);
        Toast.makeText(getBaseContext(), "Datos actualizados con exito 😉", Toast.LENGTH_LONG).show();

    }


    //================================================


    private void fnc03(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Editar");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Escriba sus apellidos";
        fg05f01.setHint(txt);
        fg05f01.setText(obj01.getF04());
        dialog.setIcon(R.drawable.icon5);

        dialog.setView(login_layout);

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                String p1= fg05f01.getText().toString();
                p1=clear(p1);

                if(p1.equals("")){
                    ShowMessage("Por favor, ingrese sus apellidos");
                    return;
                }
                p1=toTitleCase(p1);
                fnc04(p1);
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

    private void fnc04(String p1){

        String p2=obj01.getF03();
        if(p2.split(" ").length>0){
            p2=p2.split(" ")[0];
        }

        String p3=p1;
        if(p3.split(" ").length>0){
            p3=p3.split(" ")[0];
        }

        p2=p2+" " + p3;

        obj01.setF04(p1);
        obj01.setF02(p2);
        dta01.update(obj01);
        f18f02.setText(p1);
        Toast.makeText(getBaseContext(), "Datos actualizados con exito 😉", Toast.LENGTH_LONG).show();


    }




    //================================================

    private void fnc09(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Editar");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Escriba su numero de documento";
        fg05f01.setHint(txt);
        fg05f01.setText(obj01.getF05());
        dialog.setIcon(R.drawable.icon5);

        dialog.setView(login_layout);

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                String p1= fg05f01.getText().toString().trim();
                p1=clear(p1);
                if(p1.equals("")){
                    ShowMessage("Por favor, ingrese su numero de documento de identidad.");
                    return;
                }
                p1=toTitleCase(p1);
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

    private void fnc10(String p1){

        obj01.setF05(p1);
        dta01.update(obj01);
        f18f05.setText(p1);
        Toast.makeText(getBaseContext(), "Datos actualizados con exito 😉", Toast.LENGTH_LONG).show();

    }


    //================================================


    private void fnc11(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Editar");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Escriba su numero de celular";
        fg05f01.setHint(txt);
        fg05f01.setText(obj01.getF06());
        dialog.setIcon(R.drawable.icon5);

        dialog.setView(login_layout);

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                String p1= fg05f01.getText().toString().trim();
                p1=clear(p1);
                if(p1.equals("")){
                    ShowMessage("Por favor, ingrese su numero de celular");
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

    private void fnc12(String p1){

        obj01.setF06(p1);
        dta01.update(obj01);
        f18f06.setText(p1);
        Toast.makeText(getBaseContext(), "Datos actualizados con exito 😉", Toast.LENGTH_LONG).show();

    }


    //================================================


    private void fnc13(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Editar");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment06, null);

        final EditText fg06f01 = login_layout.findViewById(R.id.fg06f01);
        final EditText fg06f02 = login_layout.findViewById(R.id.fg06f02);
        dialog.setIcon(R.drawable.icon5);

        dialog.setView(login_layout);

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                String p1= fg06f01.getText().toString().trim();
                String p2= fg06f02.getText().toString().trim();
                p1=clear(p1);
                p2=clear(p2);
                if(p1.equals("")){
                    ShowMessage("Por favor, ingrese su nueva contraseña");
                    return;
                }
                if(p2.equals("")){
                    ShowMessage("Por favor, repita su contraseña");
                    return;
                }

                if(!p1.equals(p2)){
                    ShowMessage("Las contraseñas no coinciden, por favor reingrese su contraseña.");
                    return;
                }

                fnc14(p1);
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


    private void fnc14(String p1){

        obj01.setF07(p1);
        dta01.update(obj01);
        String txt="";
        for(int i=0; i<p1.length();i++){
            txt=txt+"*";
        }
        f18f07.setText(txt);
        Toast.makeText(getBaseContext(), "Datos actualizados con exito 😉", Toast.LENGTH_LONG).show();

    }



    //================================================
    //image

    private void fnc15(String p1){

        dta01.updateImage(obj01.getF01(), p1);

        obj01.setF11(p1);

        byte[] decodedString = Base64.decode(p1, Base64.DEFAULT);
        Bitmap mbitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        f18f08.setImageBitmap(mbitmap);

        //Toast.makeText(getBaseContext(), "Datos actualizados con exito 😉", Toast.LENGTH_LONG).show();

    }

    private void fnc17(){

        DocItem im = new DocItem(obj01.getF02(), obj01.getF11(),"", "","","");

        Intent intent = new Intent(this, MainActivity10.class);
        intent.putExtra("item",im);
        startActivity(intent);
    }





    //=======================================================================


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




    //    =====================================================================


    private void fnc18(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        AlertDialog OptionDialog = dialog.create();
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Buscar Empresa");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Nombre o nro RUC";
        fg05f01.setHint(txt);
        fg05f01.setText("");
        dialog.setIcon(R.drawable.icon5);

        dialog.setView(login_layout);

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                String p1= fg05f01.getText().toString().trim();
                p1=clear(p1);
                if(p1.equals("")){
                    ShowMessage("Por favor ingrese el nombre o nro RUC de la empresa");
                    return;
                }
                fnc19(p1);
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

    private void fnc19(String txt){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("Sin conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

        DocItem item = new DocItem(txt, "","","","","");
        sender.connect().fnc22(item).enqueue(new Callback<List<Obj14>>() {
            @Override
            public void onResponse(Call<List<Obj14>> call, Response<List<Obj14>> res) {
                waitingDialog.hide();

                if (res.body()==null) {
                    ShowMessage("Ha ocurrido un error 😢");
                    return;
                }
                fnc20(res.body());
            }

            @Override
            public void onFailure(Call<List<Obj14>> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }


    private void fnc20(List<Obj14> items){

        dap14 = new Dap14(this,items);

        View view = getLayoutInflater().inflate(R.layout.fragment09, null);
        ListView fr09r01 = view.findViewById(R.id.fr09r01);
        fr09r01.setAdapter(dap14);

        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar...");
        builder.setIcon(R.drawable.icon5);
        builder.setView(view);
        builder.setCancelable(false);
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
                fnc21(dap14.getItem(i));
                //Log.e("ii===========",String.valueOf(i));
            }
        });


        dialog.show();
    }



    private void fnc21(Obj14 m14){
        Log.e("obj14______", m14.toString());
        obj01.setF13(m14.getF01());
        obj01.setF14(m14.getF03());
        dta01.update(obj01);
        f18f10.setText(m14.getF03());
        Toast.makeText(getBaseContext(), "Datos actualizados con exito 😉", Toast.LENGTH_LONG).show();
    }


    //    =====================================================================


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
        userImage1.compress(Bitmap.CompressFormat.PNG, 50, baos);
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



//================================================================================================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main10, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Log.i("id____=",String.valueOf(id));

        if(id==R.id.mn10r1){

        }

        if(id==R.id.mn10r2){
            fnc22();
        }

        if(id==16908332){
            super.onBackPressed();
        }

        return true;
    }


    private void fnc22(){

        Boolean ans;
        ans=isNetworkAvailable();
        if(!ans){
            ShowMessage("Sin conexion a internet 😢");
            return;
        }

        ProgressDialog waitingDialog;
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle(R.string.nav_header_title);
        waitingDialog.setMessage("Enviando...");
        waitingDialog.setIcon(R.drawable.icon5);
        waitingDialog.setCancelable(false);
        waitingDialog.show();



        sender.connect().fnc23(obj01).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();

                if (res.body()==null) {
                    ShowMessage("Ha ocurrido un error 😢");
                    return;
                }
                fnc23(res.body());
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });

    }

    private void fnc23(DocItem im){
        Log.e("im_____", im.toString());
        obj01.setF19(im.getNum());
        dta01.update(obj01);
        Toast.makeText(getBaseContext(), "Datos actualizados con exito 😉", Toast.LENGTH_LONG).show();
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



    private static String toTitleCase(String str) {

        if(str == null || str.isEmpty())
            return "";

        if(str.length() == 1)
            return str.toUpperCase();

        //split the string by space
        String[] parts = str.split(" ");

        StringBuilder sb = new StringBuilder( str.length() );

        for(String part : parts){

            if(part.length() > 1 )
                sb.append( part.substring(0, 1).toUpperCase() )
                        .append( part.substring(1).toLowerCase() );
            else
                sb.append(part.toUpperCase());

            sb.append(" ");
        }

        return sb.toString().trim();
    }


    private String clear(String txt){
        txt=txt.trim();
        txt=txt.replace("'","");
        txt=txt.replace("\n","");
        return  txt;
    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager  = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}