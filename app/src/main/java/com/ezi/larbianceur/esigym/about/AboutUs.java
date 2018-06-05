package com.ezi.larbianceur.esigym.about;

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

import com.ezi.larbianceur.esigym.BottomNavigationViewHelper;
import com.ezi.larbianceur.esigym.LoginActivity;
import com.ezi.larbianceur.esigym.MainActivity;
import com.ezi.larbianceur.esigym.motivation.MotivationActivity;
import com.ezi.larbianceur.esigym.NutritionActivity;
import com.ezi.larbianceur.esigym.R;
import com.ezi.larbianceur.esigym.suppliment.SupplimentActivity;
import com.ezi.larbianceur.esigym.suppliment.SupplimentsPageAdapter;
import com.ezi.larbianceur.esigym.workout.WorkoutActivity;

public class AboutUs extends AppCompatActivity {

    SharedPreferences prefs;
    int id;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_about_us);

        prefs = getSharedPreferences( "MyData", Context.MODE_PRIVATE );

        boolean isLogin = prefs.getBoolean( "isConnect", false );



        if (!isLogin) {
            startActivity( new Intent( this, LoginActivity.class ) );
        }

        id = prefs.getInt( "id", 0 );

        if (id == 0) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean( "isConnect", false );
            editor.commit();
            startActivity( new Intent( this, LoginActivity.class ) );
        }



        mViewPager = (ViewPager) findViewById( R.id.container );

         TabLayout tabLayout = (TabLayout) findViewById( R.id.tabs );
        tabLayout.setupWithViewPager( mViewPager );
            setupViewPager( mViewPager );
        tabLayout.getTabAt(0).setText( "Application" );
        tabLayout.getTabAt(1).setText( "Equipe" );

            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
            BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
            Menu menu = bottomNavigationView.getMenu();
            MenuItem menuItem = menu.getItem(4);
            menuItem.setChecked(true);
            bottomNavigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.ic_home:
                            Intent intent5 = new Intent(AboutUs.this, MainActivity.class);
                            startActivity(intent5);
                            break;

                        case R.id.ic_meals:
                            Intent intent4 = new Intent(AboutUs.this, NutritionActivity.class);
                            startActivity(intent4);
                            break;

                        case R.id.ic_motivation:
                            Intent intent3 = new Intent(AboutUs.this, MotivationActivity.class);
                            startActivity(intent3);
                            break;

                        case R.id.ic_suppliment:
                            Intent intent1 = new Intent(AboutUs.this, SupplimentActivity.class);
                            startActivity(intent1);
                            break;

                        case R.id.ic_workout:
                            Intent intent2 = new Intent(AboutUs.this, WorkoutActivity.class);
                            startActivity(intent2);
                            break;
                    }
                    return false;
                }
            } );




    }

    public void setupViewPager(ViewPager upViewPager) {
        SupplimentsPageAdapter adapter = new SupplimentsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ApplicationFragment());
        adapter.addFragment(new EquipeFragment());
        upViewPager.setAdapter(adapter);
    }
}
