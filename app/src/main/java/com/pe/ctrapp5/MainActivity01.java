package com.pe.ctrapp5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.pe.ctrapp5.Data.Dta01;
import com.pe.ctrapp5.Model.Obj01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity01 extends AppCompatActivity {

    TextView f01f01;
    TextView f01f02;
    TextView f01f04;
    TextView f01f05;
    TextView f01f06;
    TextView f01f07;
    TextView f01f08;
    Button f01f09;
    Dta01 dta01;


    SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main01);

        dta01=new Dta01(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Bienvenido");

        f01f01 = (TextView) findViewById(R.id.f01f01);
        f01f02 = (TextView) findViewById(R.id.f01f02);
        f01f04 = (TextView) findViewById(R.id.f01f04);
        f01f05 = (TextView) findViewById(R.id.f01f05);
        f01f06 = (TextView) findViewById(R.id.f01f06);
        f01f07 = (TextView) findViewById(R.id.f01f07);
        f01f08 = (TextView) findViewById(R.id.f01f08);
        f01f09 = (Button) findViewById(R.id.f01f09);

        f01f09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc04();
            }
        });

        Log.e("Activity====", "Activity 01");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main04, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Log.i("id____=",String.valueOf(id));

        if(id==R.id.mn4r1){
            fnc04();
        }

        if(id==16908332){
            fnc01();
        }

        return true;
    }



    private void fnc01(){
        String p1=clear(f01f01.getText().toString());
        if (!p1.equals("")){
            fnc02();
        }else{
            fnc03();
        }
    }



    private void fnc02()
    {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("¿Esta seguro de cancelar su registro?");
        dialog.setIcon(R.drawable.icon4);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fnc03();
            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private void fnc03(){
        super.onBackPressed();
    }

    private void fnc04(){

        String p1=clear(f01f01.getText().toString());
        String p2=clear(f01f02.getText().toString());
        String p4=clear(f01f04.getText().toString());
        String p5=clear(f01f05.getText().toString());
        String p6=clear(f01f06.getText().toString());
        String p7=clear(f01f07.getText().toString());
        String p8=clear(f01f08.getText().toString());

        if(p1.equals("")){
            ShowMessage("Por favor, ingrese sus nombres.");
            return;
        }
        if(p2.equals("")){
            ShowMessage("Por favor, ingrese sus apellidos.");
            return;
        }

        if(p4.equals("")){
            ShowMessage("Por favor, ingrese su correo electrónico");
            return;
        }
        if(!p4.contains("@")){
            ShowMessage("Por favor, ingrese un correo valido.");
            return;
        }
        if(p5.equals("")){
            ShowMessage("Por favor, ingrese su DNI");
            return;
        }
        if(p6.equals("")){
            ShowMessage("Por favor, ingrese su número de celular");
            return;
        }
        if(p7.equals("")){
            ShowMessage("Por favor, ingrese su contraseña");
            return;
        }
        if(p8.equals("")){
            ShowMessage("Por favor, repita su contraseña");
            return;
        }

        if(!p7.equals(p8)){
            ShowMessage("Las contraseñas con coinciden. Por favor, reingrese ambas.");
            f01f07.setText("");
            f01f08.setText("");
            return;
        }


        p1=toTitleCase(p1);
        p2=toTitleCase(p2);
        p4=p4.toLowerCase(Locale.ROOT);
        p5=p5.replace(".","");
        p6=p6.replace(".","");


        f01f01.setText(p1);
        f01f02.setText(p2);
        f01f04.setText(p4);
        f01f05.setText(p5);
        f01f06.setText(p6);

        String[] v1=p1.split(" ");
        String v2=p1;
        if(v1.length>0){
            v2=v1[0];
        }

        v1=p2.split(" ");
        String v3=p2;
        if(v1.length>0){
            v3=v1[0];
        }

        v2=v2.trim();
        v3=v3.trim();

        v2=v2+" " +v3;
        String dt1 = df1.format(new Date());
        String dt2 = df2.format(new Date());
        String img;

        Obj01 obj010 = new Obj01();
        obj010=dta01.getById(p4);
        img=this.getString(R.string.img);
        if(obj010!=null){
            img=obj010.getF11();
        }


        Obj01 obj01 = new Obj01();
        obj01.setF01(p4);
        obj01.setF02(v2);
        obj01.setF03(p1);
        obj01.setF04(p2);
        obj01.setF05(p5);
        obj01.setF06(p6);
        obj01.setF07(p7);
        obj01.setF08(dt1);
        obj01.setF09(dt1);
        obj01.setF10("1");
        obj01.setF11(img);
        obj01.setF12(dt2);
        obj01.setF13("000000");
        obj01.setF14("...");
        obj01.setF15(Build.MANUFACTURER);
        obj01.setF16(Build.MODEL);
        obj01.setF17(Build.ID);
        obj01.setF18(String.valueOf(Build.VERSION.SDK_INT));
        obj01.setF19("000000");
        obj01.setF20("");

        dta01.insert(obj01);


        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("email",obj01.getF01());
        startActivity(intent);

        this.finish();
    }



    //================================================


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

    private String clear(String p1){
        String p2=p1.trim();
        p2=p2.replace("'","");
        p2=p2.replace("\n","");
        return p2;
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



}