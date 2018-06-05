package com.ezi.larbianceur.esigym.motivation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ezi.larbianceur.esigym.BottomNavigationViewHelper;
import com.ezi.larbianceur.esigym.LoginActivity;
import com.ezi.larbianceur.esigym.MainActivity;
import com.ezi.larbianceur.esigym.NutritionActivity;
import com.ezi.larbianceur.esigym.R;
import com.ezi.larbianceur.esigym.suppliment.SupplimentActivity;
import com.ezi.larbianceur.esigym.suppliment.SupplimentsPageAdapter;
import com.ezi.larbianceur.esigym.workout.WorkoutActivity;

public class MotivationActivity extends AppCompatActivity {

    ViewPager mViewPager;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_motivation );

        prefs=getSharedPreferences("MyData", Context.MODE_PRIVATE );
        boolean isLogin = prefs.getBoolean( "isConnect", false );

        if (!isLogin) {
            startActivity( new Intent( this, LoginActivity.class ) );
        }

        try {


        mViewPager = (ViewPager) findViewById( R.id.container );
        setupViewPager( mViewPager );

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        TabLayout tabLayout = (TabLayout) findViewById( R.id.tabs );
        tabLayout.setupWithViewPager( mViewPager );

        tabLayout.getTabAt(0).setText( "Video" );
        tabLayout.getTabAt(1).setText( "Speech" );


        bottomNavigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intent1 = new Intent(MotivationActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_meals:
                        Intent intent4 = new Intent(MotivationActivity.this, NutritionActivity.class);
                        startActivity(intent4);
                        break;

                    case R.id.ic_motivation:

                        break;

                    case R.id.ic_suppliment:
                        Intent intent3 = new Intent(MotivationActivity.this, SupplimentActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_workout:
                        Intent intent2 = new Intent(MotivationActivity.this, WorkoutActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        } );


        }catch (Exception e){
            Toast.makeText( MotivationActivity.this,e.getMessage(),Toast.LENGTH_LONG ).show();}
    }

    public void setupViewPager(ViewPager upViewPager) {
        SupplimentsPageAdapter adapter = new SupplimentsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new VideoFragment());
        adapter.addFragment(new SpeechFragment());
        upViewPager.setAdapter(adapter);
    }
    }

