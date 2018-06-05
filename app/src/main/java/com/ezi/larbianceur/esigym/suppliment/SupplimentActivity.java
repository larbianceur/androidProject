package com.ezi.larbianceur.esigym.suppliment;

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
import android.widget.Button;
import android.widget.GridView;
import com.ezi.larbianceur.esigym.BottomNavigationViewHelper;
import com.ezi.larbianceur.esigym.LoginActivity;
import com.ezi.larbianceur.esigym.MainActivity;
import com.ezi.larbianceur.esigym.motivation.MotivationActivity;
import com.ezi.larbianceur.esigym.NutritionActivity;
import com.ezi.larbianceur.esigym.R;
import com.ezi.larbianceur.esigym.workout.WorkoutActivity;

public class SupplimentActivity extends AppCompatActivity {

    SharedPreferences prefs;
    GridView gv;
    Button nextBtn,prevBtn;
    Paginator p;
    int totalPages;
    private int currentPage=0;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_suppliment );
        prefs=getSharedPreferences("MyData", Context.MODE_PRIVATE );
        boolean isLogin = prefs.getBoolean( "isConnect", false );

        if (!isLogin) {
            startActivity( new Intent( this, LoginActivity.class ) );
        }
        mViewPager = (ViewPager) findViewById( R.id.container );
        setupViewPager( mViewPager );

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        TabLayout tabLayout = (TabLayout) findViewById( R.id.tabs );
        tabLayout.setupWithViewPager( mViewPager );

        tabLayout.getTabAt(0).setText( "Gainer" );
        tabLayout.getTabAt(1).setText( "Protiens" );
        tabLayout.getTabAt(2).setText( "BCAA" );

        bottomNavigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intent1 = new Intent(SupplimentActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_meals:
                        Intent intent4 = new Intent(SupplimentActivity.this, NutritionActivity.class);
                        startActivity(intent4);
                        break;

                    case R.id.ic_motivation:
                        Intent intent3 = new Intent(SupplimentActivity.this, MotivationActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_suppliment:

                        break;

                    case R.id.ic_workout:
                        Intent intent2 = new Intent(SupplimentActivity.this, WorkoutActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        } );


}

    public void setupViewPager(ViewPager upViewPager) {
        SupplimentsPageAdapter adapter = new SupplimentsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new GainerFragment());
        adapter.addFragment(new ProteinFragment());
        adapter.addFragment(new BcaaFragment());
        upViewPager.setAdapter(adapter);
    }
}
