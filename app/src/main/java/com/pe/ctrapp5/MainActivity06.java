package com.pe.ctrapp5;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.pe.ctrapp5.Data.Dta02;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj02;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity06 extends AppCompatActivity implements View.OnClickListener {

    Button f06r01;
    Button f06r02;
    Button f06r031;
    Button f06r032;
    Button f06r041;
    Button f06r042;
    Button f06r05;
    Button f06r06;

    Obj01 obj01;
    Dta02 dta02;

    Obj02 obj02;

    final String[] l1 = { "Charla", "Capacitacion","Asistencia" };
    final CharSequence[] l2={"Sala quiñones", "Sala Chavin", "Gerencia General", "Capacitacion","Taller"};


    SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main06);

        f06r01 = (Button) findViewById(R.id.f06r01);
        f06r02 = (Button) findViewById(R.id.f06r02);
        f06r031 = (Button) findViewById(R.id.f06r031);
        f06r032 = (Button) findViewById(R.id.f06r032);
        f06r041 = (Button) findViewById(R.id.f06r041);
        f06r042 = (Button) findViewById(R.id.f06r042);
        f06r05 = (Button) findViewById(R.id.f06r05);
        f06r06 = (Button) findViewById(R.id.f06r06);

        Intent intent = getIntent();
        obj01=(Obj01)intent.getSerializableExtra("obj01");
        obj02=(Obj02)intent.getSerializableExtra("obj02");


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Nueva reunion");

        f06r01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc01();
            }
        });

        f06r02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc02();
            }
        });

        f06r031.setOnClickListener(this);
        f06r032.setOnClickListener(this);

        f06r041.setOnClickListener(this);
        f06r042.setOnClickListener(this);

        f06r05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc05();
            }
        });


        f06r06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc06();
            }
        });

        dta02 = new Dta02(this);

        fnc00();
    }


    @Override
    public void onResume(){
        super.onResume();
        Log.e("Activity====", "Activity 06");
    }

    public void fnc00(){

//        Log.e("obj02===", obj02.toString());

        String d=obj02.getF01();

        if(!d.equals("0")){
            //mostrar
            f06r01.setText(obj02.getF02());
            f06r02.setText(obj02.getF03());
            f06r031.setText(obj02.getF05());
            f06r032.setText(obj02.getF07());
            f06r041.setText(obj02.getF11());
            f06r042.setText(obj02.getF13());
            f06r05.setText(obj02.getF16());
        }
    }


    private void fnc01() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar tipo");
        builder.setIcon(R.drawable.icon4);
        builder.setItems(l1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                fnc011(item);
            }
        });
        builder.show();
    }


    private void fnc011(int n) {
        String m=l1[n].toString();
        obj02.setF02(m);
        f06r01.setText(m);
    }

    private void fnc02(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Ingresar Tema");
        dialog.setIcon(R.drawable.icon5);

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Tema...";
        fg05f01.setHint(txt);
        fg05f01.setText(obj02.getF03());


        dialog.setIcon(R.drawable.icon4);
        dialog.setView(login_layout);

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                String p1= fg05f01.getText().toString();
                p1=clear(p1);
                if(p1.equals("")){
                    ShowMessage("Por favor, ingrese un tema");
                    return;
                }

                obj02.setF03(p1);
                f06r02.setText(p1);

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



    private void fnc05() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar lugar");
        builder.setIcon(R.drawable.icon4);
        builder.setItems(l2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                fnc051(item);
            }
        });
        builder.show();
    }


    private void fnc051(int n) {
        String m=l2[n].toString();
        obj02.setF16(m);
        f06r05.setText(m);
    }


    public void fnc06(){

        String d=obj02.getF01();

        if(d.equals("0")){
            String p1=obj02.getF04()+" " + obj02.getF06();
            String p2=obj02.getF05()+" " + obj02.getF07();

            String p3=obj02.getF10()+" " + obj02.getF12();
            String p4=obj02.getF11()+" " + obj02.getF13();

            String dt1 = df1.format(new Date());
            String dt2 = df2.format(new Date());

            obj02.setF08(p1);
            obj02.setF09(p2);

            obj02.setF14(p3);
            obj02.setF15(p4);

            obj02.setF17(obj01.getF01());
            obj02.setF18(dt1);
            obj02.setF19(dt2);
            obj02.setF20("0");

            dta02.insert(obj02);
        }
        else{

            String p1=obj02.getF04()+" " + obj02.getF06();
            String p2=obj02.getF05()+" " + obj02.getF07();

            String p3=obj02.getF10()+" " + obj02.getF12();
            String p4=obj02.getF11()+" " + obj02.getF13();

            String dt1 = df1.format(new Date());
            String dt2 = df2.format(new Date());

            obj02.setF08(p1);
            obj02.setF09(p2);

            obj02.setF14(p3);
            obj02.setF15(p4);

            obj02.setF17(obj01.getF01());
            obj02.setF18(dt1);
            obj02.setF19(dt2);

            dta02.update(obj02);

        }


        this.finish();
    }

    //========================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main04, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Log.i("id____=",String.valueOf(id));

        if(id==R.id.mn4r1){
            fnc06();
        }

        if(id==16908332){
            super.onBackPressed();
        }

        return true;
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


    //================================================


    @Override
    public void onClick(View view) {

        if(view==f06r031){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            f06r031.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            obj02.setF04(year + "-" + (monthOfYear + 1)+ "-"+dayOfMonth);
                            obj02.setF05(dayOfMonth+ "-"+ (monthOfYear + 1)+ "-"+year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if(view==f06r032){

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            f06r032.setText(hourOfDay + ":" + minute);
                            String p=" AM";
                            if(hourOfDay>=12){
                                p=" PM";
                            }
                            obj02.setF06(hourOfDay + ":" + minute + ":00");
                            obj02.setF07(hourOfDay + ":" + minute + p);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();

        }


        if(view==f06r041){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            f06r041.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            obj02.setF10(year + "-" + (monthOfYear + 1)+ "-"+dayOfMonth);
                            obj02.setF11(dayOfMonth+ "-"+ (monthOfYear + 1)+ "-"+year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if(view==f06r042){

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            f06r042.setText(hourOfDay + ":" + minute);
                            String p=" AM";
                            if(hourOfDay>=12){
                                p=" PM";
                            }
                            obj02.setF12(hourOfDay + ":" + minute + ":00");
                            obj02.setF13(hourOfDay + ":" + minute + p);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();

        }

    }






    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}