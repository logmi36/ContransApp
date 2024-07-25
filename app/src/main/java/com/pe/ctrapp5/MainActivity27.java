package com.pe.ctrapp5;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.pe.ctrapp5.Adapter.Dap15;
import com.pe.ctrapp5.Data.Dta04;
import com.pe.ctrapp5.Data.Dta05;
import com.pe.ctrapp5.Data.Dta07;
import com.pe.ctrapp5.Data.Dta09;
import com.pe.ctrapp5.Data.Dta15;
import com.pe.ctrapp5.Data.Dta18;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj04;
import com.pe.ctrapp5.Model.Obj05;
import com.pe.ctrapp5.Model.Obj07;
import com.pe.ctrapp5.Model.Obj09;
import com.pe.ctrapp5.Model.Obj15;
import com.pe.ctrapp5.Model.Obj18;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity27 extends AppCompatActivity implements View.OnClickListener{

    Dap15 dap15;
    Sender sender;
    Obj01 obj01;

    TextView f27r01;
    TextView f27r02;
    TextView f27r03;
    TextView f27r04;
    TextView f27r05;
    TextView f27r06;
    TextView f27r07;
    TextView f27r08;
    TextView f27r09;
    Button f27r10;

    Obj15 obj15;

    final String[] l1 = { "CTN", "PLT","CSL" };
    final String[] l2={"DT-IMPO","DT-CALLAO","DT-LURIN"};
    final String[] l3={"Devolucion","Asignacion"};

    Date dtt;
    SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat df4 = new SimpleDateFormat("hh:mm:ss a");


    Dta04 dta04;
    Dta07 dta07;
    Dta09 dta09;
    Dta18 dta18;
    Dta05 dta05;
    Dta15 dta15;
    List<Obj04> ml4;
    List<Obj09> ml9;
    List<Obj07> ml7;
    List<Obj18> ml18;

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main27);

        f27r01 =(TextView) findViewById(R.id.f27r01);
        f27r02 =(TextView) findViewById(R.id.f27r02);
        f27r03 =(TextView) findViewById(R.id.f27r03);
        f27r04 =(TextView) findViewById(R.id.f27r04);

        f27r05 =(TextView) findViewById(R.id.f27r05);
        f27r06 =(TextView) findViewById(R.id.f27r06);

        f27r07 =(TextView) findViewById(R.id.f27r07);
        f27r08 =(TextView) findViewById(R.id.f27r08);
        f27r09 =(TextView) findViewById(R.id.f27r09);
        f27r10 =(Button) findViewById(R.id.f27r10);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        obj01=(Obj01) intent.getSerializableExtra("obj01");

        actionBar.setTitle("Nueva Cita");


        f27r01.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f27r01.getRight() - f27r01.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        fnc02();
                        return true;
                    }
                }
                return false;
            }
        });

        f27r02.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f27r02.getRight() - f27r02.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        fnc03();
                        return true;
                    }
                }
                return false;
            }
        });

        f27r03.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f27r03.getRight() - f27r03.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        fnc04();
                        return true;
                    }
                }
                return false;
            }
        });


        f27r04.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f27r04.getRight() - f27r04.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        fnc05();
                        return true;
                    }
                }
                return false;
            }
        });


        f27r05.setOnClickListener(this);
        f27r06.setOnClickListener(this);



        f27r07.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f27r07.getRight() - f27r07.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        fnc11();
                        return true;
                    }
                }
                return false;
            }
        });


        f27r08.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f27r08.getRight() - f27r08.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        fnc13();
                        return true;
                    }
                }
                return false;
            }
        });


        f27r09.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (f27r09.getRight() - f27r09.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        fnc15();
                        return true;
                    }
                }
                return false;
            }
        });

        f27r10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc17();
            }
        });




        sender=sender.getInstance();

        dta04 = new Dta04(this);
        dta07 = new Dta07(this);
        dta09 = new Dta09(this);
        dta18 = new Dta18(this);
        dta05 = new Dta05(this);
        dta15 = new Dta15(this);


        obj15=new Obj15();
        mDay=0;

        obj15.setF01("00");
        obj15.setF02("00");
        obj15.setF03("00");
        obj15.setF04("00000");
        obj15.setF05("");
        obj15.setF06("");
        obj15.setF07("");
        obj15.setF08("");
        obj15.setF09("");
        obj15.setF12("");
        obj15.setF13("");
        obj15.setF14("");
        obj15.setF15("");
        obj15.setF16(obj01.getF20());
        obj15.setF17(obj01.getF02());
        obj15.setF18("");

        obj15.setF19("");
        obj15.setF20("");
        obj15.setF21("");


    }



    @Override
    public void onResume(){
        super.onResume();
        Log.e("Activity====", "Activity 27");
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    //================================================================================================================================================



    private void fnc02() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar tipo de carga");
        builder.setIcon(R.drawable.icon4);
        builder.setItems(l1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                fnc021(item);
            }
        });
        builder.show();
    }


    private void fnc021(int n) {
        String m=l1[n].toString();
        String p=String.format("%02d",n+1);
        obj15.setF01(p);
        obj15.setF19(m);
        f27r01.setText(m);
        //f27r01.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button5));
    }


    //=============================================================

    private void fnc03() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar almacen");
        builder.setIcon(R.drawable.icon4);
        builder.setItems(l2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                fnc031(item);
            }
        });
        builder.show();
    }


    private void fnc031(int n) {
        String m=l2[n].toString();
        String p=String.format("%02d",n+1);
        obj15.setF02(p);
        obj15.setF20(m);
        f27r02.setText(m);
        //f27r02.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button5));
    }


    //=============================================================

    private void fnc04() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar tipo de cita");
        builder.setIcon(R.drawable.icon4);
        builder.setItems(l3, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                fnc041(item);
            }
        });
        builder.show();
    }


    private void fnc041(int n) {
        String m=l3[n].toString();
        String p=String.format("%02d",n+1);
        obj15.setF03(p);
        obj15.setF21(m);
        f27r03.setText(m);
        //f27r03.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button5));
    }


    //=====================================================================================


    private void fnc05() {

        ml18=dta18.ListbyId(obj01.getF01());

        String[] l18 = new String[ml18.size()];

        for(int i = 0; i < ml18.size(); i++)
            l18[i] = ml18.get(i).getF02();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar documento");
        builder.setIcon(R.drawable.icon4);
        builder.setItems(l18, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                fnc06(item);
            }
        });
        builder.show();
    }


    private void fnc06(int n) {

        String txt=ml18.get(n).getF02();

        ml9=dta09.ListByDocument(txt);
        obj15.setF14(txt);

        String[] l9 = new String[ml9.size()];

        for(int i = 0; i < ml9.size(); i++)
            l9[i] = ml9.get(i).getF03() + " " + ml9.get(i).getF04();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar contenedor");
        builder.setIcon(R.drawable.icon4);
        builder.setItems(l9, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                fnc08(item);
            }
        });
        builder.show();

    }


    private void fnc08(int n){

        Obj09 m09 =ml9.get(n);

        Log.e("Obj09_____", m09.toString());
        obj15.setF04(m09.getF01());

        String ctn=m09.getF03()+" - " + m09.getF04();

        obj15.setF18(ctn);
        f27r04.setText(ctn);
        //f27r04.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button5));

    }






    private void fnc09(){

        dtt = new Date(mYear-1900, mMonth, mDay,0,0,0);
        String dt2 = df2.format(dtt);
        String dt3 = df3.format(dtt);

        f27r05.setText(dt3);
        //f27r05.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button5));
        obj15.setF05(dt2);

    }



    //================================================================================================================================================



    private void fnc10(){

        if(mDay==0){
            ShowMessage("Por favor seleccione una fecha 😢");
            return;
        }

        dtt = new Date(mYear-1900, mMonth, mDay,mHour,mMinute,0);
        String dt2 = df2.format(dtt);
        String dt4 = df4.format(dtt);

        f27r06.setText(dt4);
        //f27r06.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button5));
        obj15.setF05(dt2);

    }


    //================================================================================================================================================


    private void fnc11() {

        ml4=dta04.List(obj01.getF01());

        String[] l5 = new String[ml4.size()];

        for(int i = 0; i < ml4.size(); i++)
            l5[i] = ml4.get(i).getF02();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar vehículo");
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
        Log.e("m4", m4.toString());

        String txt=m4.getF02();
        f27r07.setText(txt);
        obj15.setF06(txt);

        obj15.setF12(m4.getF01());

        Obj05 m5;
        m5=dta05.GetById(m4.getF01(), obj01.getF01());

        obj15.setF15(m5.getF04());

        //Log.e("m5_", m5.toString());

        f27r08.setText("");
        obj15.setF07("");
        obj15.setF13("000000");
        if(m5!=null){
            f27r08.setText(m5.getF04());
            obj15.setF07(m5.getF05());
            obj15.setF13(m5.getF15());
        }
    }


    //================================================================================================================================================



    private void fnc13(){

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
                fnc14(item);
            }
        });
        builder.show();
    }


    private void fnc14(int n) {

        Obj07 m7=ml7.get(n);

        Log.e("m7__", m7.toString());

        obj15.setF15(m7.getF03());

        f27r08.setText(m7.getF04());
        obj15.setF07(m7.getF04());
        obj15.setF13(m7.getF01());
    }



    private void fnc15(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Observaciones");
        dialog.setIcon(R.drawable.icon5);

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Ingrese alguna observacion";
        fg05f01.setHint(txt);
        fg05f01.setText("");

        dialog.setView(login_layout);

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                String p1= fg05f01.getText().toString().trim();
                fnc16(p1);

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


    private void fnc16(String txt) {

        f27r09.setText(txt);
        //f27r09.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button5));
        obj15.setF08(txt);

    }



    //================================================================================================================================================

    private void fnc17() {



        if(obj15.getF01().equals("00")){
            ShowMessage("Por favor, seleccione el tipo de carga");
            return;
        }

        if(obj15.getF02().equals("00")){
            ShowMessage("Por favor, seleccione la unidad de negocio");
            return;
        }

        if(obj15.getF03().equals("00")){
            ShowMessage("Por favor, seleccione la el tipo de cita");
            return;
        }

        if(obj15.getF04().equals("000000")){
            ShowMessage("Por favor, seleccione un contenedor valido");
            return;
        }

        if(mDay==0){
            ShowMessage("Por favor, seleccione una fecha valida");
            return;
        }

        if(obj01.getF13().equals("000000")){
            ShowMessage("Por favor, configure el cliente o empresa en su perfil del aplicativo.");
            return;
        }

        if(obj15.getF06().equals("")){
            ShowMessage("Por favor, ingrese un numero de placa del vehiculo");
            return;
        }

        if(obj15.getF07().equals("")){
            ShowMessage("Por favor, ingrese un numero de brevete del conductor");
            return;
        }

        String idd;
        idd=obj15.getF09();

        if(idd.equals("")){
            idd=getRandomString(32);
        }

        obj15.setF09(idd);
        obj15.setF10(obj01.getF01());
        obj15.setF11(obj01.getF13());

        Log.e("obj15-->", obj15.toString());


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


        sender.connect().fnc27(obj15).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                if (res.body().getNum().equals("0")) {
                    ShowMessage("Algo fue mal 😢");
                    return;
                }
                fnc18();
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });
    }

    private void fnc18(){
        dta15.insert(obj15);
        Toast.makeText(this, "Datos enviados de forma exitosa", Toast.LENGTH_SHORT).show();
        this.finish();
    }




    //================================================================================================================================================

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
            fnc17();
        }

        if(id==16908332){
            super.onBackPressed();
        }

        return true;
    }



    //================================================================================================================================================

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


    @Override
    public void onClick(View view) {

        if(view==f27r05){

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                            //Log.e("year",String.valueOf(year));
                            mYear=year;
                            mMonth=monthOfYear;
                            mDay=dayOfMonth;
                            fnc09();
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }


        if(view==f27r06){

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            mHour=hourOfDay;
                            mMinute=minute;
                            fnc10();
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();

        }

    }

    private static final String ALLOWED_CHARACTERS ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }



}