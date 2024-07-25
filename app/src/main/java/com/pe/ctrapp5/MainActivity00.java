package com.pe.ctrapp5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.Manifest;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.pm.PackageManager;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;

import com.pe.ctrapp5.Data.Dta01;
import com.pe.ctrapp5.Model.Obj01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity00 extends AppCompatActivity {


    SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    Button a00b01;
    Button a00b02;
    TextView a01f01;
    Dta01 dta01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main00);

        a00b01 = (Button) findViewById(R.id.a00b01);
        a00b02 = (Button) findViewById(R.id.a00b02);
        a01f01 = (TextView) findViewById(R.id.a01f01);

        dta01 =new Dta01(this);

        a00b01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fnc00();
            }
        });

        a00b02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fnc07();
            }
        });

        a01f01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        fnc18();

    }




    @Override
    public void onResume(){
        super.onResume();
        Log.e("Activity====", "Activity 00");
    }



    private void fnc00(){

        Obj01 im1= dta01.getLast();
        String p1="";
        String p2="";
        if(im1!=null){
            p1=im1.getF01();
            p2=im1.getF07();
        }
        fnc01(p1,p2);
    }

    private void fnc01(String p1, String p2) {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Bienvenido");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment01, null);

        final EditText f01f01 = login_layout.findViewById(R.id.f01f01);
        final EditText f01f02 = login_layout.findViewById(R.id.f01f02);

        f01f01.setText(p1);
        f01f02.setText(p2);

        dialog.setView(login_layout);
        dialog.setIcon(R.drawable.icon5);

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                String q1= f01f01.getText().toString().trim();
                String q2= f01f02.getText().toString().trim();
                q1=clear(q1);
                q1=q1.toLowerCase(Locale.ROOT);
                f01f01.setText(q1);
                fnc02(q1,q2);
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



    private void fnc02(String p1, String p2){

        if (!p1.contains("@")) {
            ShowMessage("Por favor, ingrese un correo electrónico valido.");
            return;
        }

        if (TextUtils.isEmpty(p1)) {
            ShowMessage("Por favor, ingrese un correo electrónico.");
            return;
        }

        if (TextUtils.isEmpty(p2)) {
            ShowMessage("Por favor, ingrese su contraseña.");
            return;
        }

        if (p1.contains("'")) {
            ShowMessage("El correo electronico no debe contener caracteres especiales");
            return;
        }

        if (p2.contains("'")) {
            ShowMessage("La contraseña no debe contener caracteres especiales");
            return;
        }

        fnc04(p1, p2);

    }



    private void fnc04(String p1, String p2){

        Obj01 im1= dta01.getByIdAndPass(p1,p2);

        if(im1==null){
            //user does not exists
            ShowMessage("Usuario " + p1 + " no existe en el aplicativo. Por favor, registrese.");
            return;
        }

        String dt1 = df1.format(new Date());
        String dt2 = df2.format(new Date());
        im1.setF09(dt1);
        im1.setF12(dt2);
        int ans= dta01.update(im1);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("email",p1);
        startActivity(intent);

    }


    //==============================================================



    private void fnc07(){
        Intent act = new Intent(this, MainActivity01.class);
        startActivity(act);
    }


    //==============================================================

    private void ShowMessage(String _message)
    {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage(_message);
        dialog.setIcon(R.drawable.icon4);
        dialog.setPositiveButton("Acepar", new DialogInterface.OnClickListener() {
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
        return p2;
    }


    private void fnc18(){

        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.POST_NOTIFICATIONS
        };

        Boolean per;
        per=false;

        for(String permission : permissions){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(), permission)!= PackageManager.PERMISSION_GRANTED){
                per=true;
            }
        }

        if(per){
            ActivityCompat.requestPermissions(this,permissions,1);
        }


    }


}