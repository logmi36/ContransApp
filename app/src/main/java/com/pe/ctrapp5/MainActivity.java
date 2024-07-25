package com.pe.ctrapp5;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;
import com.pe.ctrapp5.Data.Dta01;
import com.pe.ctrapp5.Handler.Sender;
import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.databinding.ActivityMainBinding;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    TextView f3r1;
    TextView f3r3;
    TextView f3r5;
    ImageView f3r2;

    Button f03r01;

    Obj01 obj01;
    Dta01 dta01;
    String email;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private Sender sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).setAnchorView(R.id.fab).show();
                fnc20();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer, binding.appBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView=binding.navView;
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu =navigationView.getMenu();
        MenuItem menuItem1=menu.findItem(R.id.mn_01);
        MenuItem menuItem5=menu.findItem(R.id.mn_05);
        menuItem1.setVisible(false);
        menuItem5.setVisible(false);


        View headerView = navigationView.getHeaderView(0);

        f3r1 = (TextView) headerView.findViewById(R.id.f3r1);
        f3r3 = (TextView) headerView.findViewById(R.id.f3r3);
        f3r5 = (TextView) headerView.findViewById(R.id.f3r5);
        f3r2 =(ImageView) headerView.findViewById(R.id.f3r2);

        f03r01 =(Button) findViewById(R.id.f03r01);

        f03r01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnc20();
            }
        });

        f3r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mi perfil
                fnc01();
            }
        });

        //NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setOpenableLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

        dta01 = new Dta01(this);

        Intent intent = getIntent();
        email=(String)intent.getStringExtra("email");

        sender=sender.getInstance();


        getToken2();

    }

    @Override
    public void onResume(){
        super.onResume();
        obj01=dta01.getById(email);
        fnc00();
        Log.e("Activity====", "Activity");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
        return super.onSupportNavigateUp();
    }


    private void getToken2(){
        Log.e("token=======", "getting token.....");
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
             @Override
             public void onComplete(@NonNull Task<String> task) {
                 if (!task.isSuccessful()) {
                     Log.w("err", "Fetching FCM registration token failed", task.getException());
                     return;
                 }

                 // Get new FCM registration token
                 String token = task.getResult();
                 obj01.setF20(token);
                 dta01.update(obj01);
                 Log.e("token=======", token);
             }
        });
    }




    public void fnc00(){
        String base64 =dta01.getImage(obj01.getF01());

        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap mbitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Bitmap imageRounded=Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas=new Canvas(imageRounded);
        Paint mpaint=new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint); // Round Image Corner 100 100 100 100
        f3r1.setText(obj01.getF02());
        f3r3.setText(obj01.getF01());
        f3r2.setImageBitmap(imageRounded);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.mn0_1){
            //mi perfil
            fnc01();
        }

        if(id==R.id.mn0_2){
            finish();
        }
        if(id==R.id.mn0_3){
            //notificaciones
            fnc12();
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //reuniones
        if (id==R.id.mn_01){
            fnc02();
        }

        //vehiculos
        if (id==R.id.mn_02){
            fnc03();
        }

        //conductores
        if (id==R.id.mn_03){
            fnc04();
        }

        if (id==R.id.mn_04){
            fnc05();
        }

        if (id==R.id.mn_12){
            //documentos
            fnc11();
        }
        if (id==R.id.mn_13){
            //notificaciones
            fnc12();
        }
        if (id==R.id.mn_05){
            fnc09();
        }

        if (id==R.id.mn_06){
            fnc10();
        }

        if (id==R.id.mn_08){
            //mi perfil
            fnc06();
        }

        if (id==R.id.mn_09){
            fnc07();
        }

        if (id==R.id.mn_10){
            fnc08();
        }

        if (id==R.id.mn_11){
            finish();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void fnc01(){
        //perfil
        Intent intent = new Intent(this, MainActivity18.class);
        intent.putExtra("obj01",obj01);
        startActivity(intent);
    }

    private void fnc02(){
        //reuniones
        Intent intent = new Intent(this, MainActivity04.class);
        intent.putExtra("obj01",obj01);
        startActivity(intent);
    }


    private void fnc03(){
        //vehiculos
        Intent intent = new Intent(this, MainActivity08.class);
        intent.putExtra("obj01",obj01);
        startActivity(intent);
    }


    private void fnc04(){
        //condcutores
        Intent intent = new Intent(this, MainActivity13.class);
        intent.putExtra("obj01",obj01);
        startActivity(intent);
    }

    private void fnc05(){
        //contenedores
        Intent intent = new Intent(this, MainActivity15.class);
        intent.putExtra("obj01",obj01);
        startActivity(intent);
    }


    private void fnc06(){
        //perfil
        Intent intent = new Intent(this, MainActivity18.class);
        intent.putExtra("obj01",obj01);
        startActivity(intent);
    }

    private void fnc07(){
        //acerca de
        Intent intent = new Intent(this, MainActivity19.class);
        startActivity(intent);
    }


    private void fnc08(){
        //configuracion
        Intent intent = new Intent(this, MainActivity20.class);
        startActivity(intent);
    }


    private void fnc09(){
        //citas
        Intent intent = new Intent(this, MainActivity21.class);
        intent.putExtra("obj01",obj01);
        startActivity(intent);
    }

    private void fnc10(){
        //genracion de citas
        Intent intent = new Intent(this, MainActivity25.class);
        intent.putExtra("obj01",obj01);
        startActivity(intent);
    }

    private void fnc11(){
        //documentos
        Intent intent = new Intent(this, MainActivity28.class);
        intent.putExtra("obj01",obj01);
        startActivity(intent);
    }


    private void fnc12(){
        //notificaciones
        Intent intent = new Intent(this, MainActivity30.class);
        intent.putExtra("obj01",obj01);
        startActivity(intent);
    }


    //================================================


    private void fnc20(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage("Enviar mensaje");
        dialog.setIcon(R.drawable.icon5);

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.fragment05, null);

        final EditText fg05f01 = login_layout.findViewById(R.id.fg05f01);
        String txt="Escribir algo...";
        fg05f01.setHint(txt);
        fg05f01.setText("");

        dialog.setView(login_layout);

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                String p1= fg05f01.getText().toString().trim();
                fnc21(p1);

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


    private void fnc21(String txt){

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

        DocItem item = new DocItem("11", txt,"",obj01.getF01(),"","");

        sender.connect().fnc31(item).enqueue(new Callback<DocItem>() {
            @Override
            public void onResponse(Call<DocItem> call, Response<DocItem> res) {
                waitingDialog.hide();
                if (res.body().getNum()=="0") {
                    ShowMessage("Ocurrio un error 😢");
                    return;
                }
                fnc22();
            }

            @Override
            public void onFailure(Call<DocItem> call, Throwable t) {
                waitingDialog.hide();
                Log.e("e_rr", t.getMessage());
                ShowMessage("Whoops, algo fue mal 😢");
            }
        });

    }

    private void fnc22(){
        Toast.makeText(this, "Mensaje enviado con exito 😉", Toast.LENGTH_SHORT).show();
    }

    //================================================



    private void ShowMessage(String _message)
    {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.nav_header_title);
        dialog.setMessage(_message);
        dialog.setIcon(R.drawable.icon4);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
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