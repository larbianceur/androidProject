package com.ezi.larbianceur.esigym;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.ezi.larbianceur.esigym.about.AboutUs;
import com.ezi.larbianceur.esigym.motivation.MotivationActivity;
import com.ezi.larbianceur.esigym.suppliment.SupplimentActivity;
import com.ezi.larbianceur.esigym.workout.WorkoutActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;
    Toolbar toolbar;
    Button aboutUs,profile;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        prefs =getSharedPreferences("MyData", Context.MODE_PRIVATE );

        boolean isLogin = prefs.getBoolean("isConnect", false);

        if(!isLogin){
            startActivity( new Intent( this,LoginActivity.class ) );
        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);
        toolbar = (Toolbar)findViewById( R.id.action_bar );
        toolbar.setTitle( "Esi Gym" );
        setSupportActionBar(toolbar);

        aboutUs = (Button)findViewById( R.id.btnAboutUs );
        profile = (Button)findViewById( R.id.btnProfile );

        aboutUs.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AboutUs.class );
                startActivity( intent );
            }
        } );

        profile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this,Profil.class );
                startActivity( intent );
            }
        } );
        bottomNavigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        break;

                    case R.id.ic_meals:
                        Intent intent4 = new Intent(MainActivity.this, NutritionActivity.class);
                        startActivity(intent4);
                        break;

                    case R.id.ic_motivation:
                        Intent intent3 = new Intent(MainActivity.this, MotivationActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_suppliment:
                        Intent intent1 = new Intent(MainActivity.this, SupplimentActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_workout:
                        Intent intent2 = new Intent(MainActivity.this, WorkoutActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        } );


    }
}
