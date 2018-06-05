package com.ezi.larbianceur.esigym;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ezi.larbianceur.esigym.motivation.MotivationActivity;
import com.ezi.larbianceur.esigym.suppliment.SupplimentActivity;
import com.ezi.larbianceur.esigym.workout.WorkoutActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class NutritionActivity extends AppCompatActivity {
    SharedPreferences prefs;
    String regUrl ;
    Toolbar toolbar;
    int id;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_nutrition );

        prefs=getSharedPreferences("MyData", Context.MODE_PRIVATE );

        boolean isLogin = prefs.getBoolean("isConnect", false);

        if(!isLogin){
            startActivity( new Intent( this,LoginActivity.class ) );
        }

        id = prefs.getInt( "id",0 );

        if(id == 0){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean( "isConnect",false );
            editor.commit();
            startActivity( new Intent( this,LoginActivity.class ) );
        }
        try {


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intent5 = new Intent(NutritionActivity.this, MainActivity.class);
                        startActivity(intent5);
                        break;

                    case R.id.ic_meals:
                        break;

                    case R.id.ic_motivation:
                        Intent intent3 = new Intent(NutritionActivity.this, MotivationActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_suppliment:
                        Intent intent1 = new Intent(NutritionActivity.this, SupplimentActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_workout:
                        Intent intent2 = new Intent(NutritionActivity.this, WorkoutActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        } );

        toolbar = (Toolbar)findViewById( R.id.action_bar );
        toolbar.setTitle( "Meals Plan" );
        }catch (Exception e){
            Toast.makeText( NutritionActivity.this,e.getMessage(),Toast.LENGTH_LONG ).show();
        }
        regUrl= "http://192.168.1.3:8080/repas.php?id="+id;
        builder = new AlertDialog.Builder( NutritionActivity.this );

        StringRequest stringRequest = new StringRequest( Request.Method.GET, regUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonArray = new JSONObject( response);
                    JSONObject jsonArray1 = jsonArray.getJSONObject( "meals" );



                        JSONObject jsonObject = jsonArray1.getJSONObject( "repas1");
                        TextView meal1 = (TextView) findViewById( R.id.meal1 );
                        meal1.setText( jsonObject.getString( "contenu" ) );


                        JSONObject jsonObject2 = jsonArray1.getJSONObject( "repas2");
                        TextView meal2 = (TextView) findViewById( R.id.meal2);
                        meal2.setText( jsonObject2.getString( "contenu" ) );

                    JSONObject jsonObject3 = jsonArray1.getJSONObject( "repas3");
                        TextView meal3 = (TextView) findViewById( R.id.meal3 );
                        meal3.setText( jsonObject3.getString( "contenu" ) );

                    JSONObject jsonObject4 = jsonArray1.getJSONObject( "repas4");
                        TextView meal4 = (TextView) findViewById( R.id.meal4 );
                        meal4.setText( jsonObject4.getString( "contenu" ) );

                    JSONObject jsonObject5 = jsonArray1.getJSONObject( "repas5");
                        TextView meal5 = (TextView) findViewById( R.id.meal5 );
                        meal5.setText( jsonObject5.getString( "contenu" ) );

                    } catch (JSONException e) {

                    Toast.makeText( NutritionActivity.this,e.getMessage(),Toast.LENGTH_LONG ).show();
                }
            }

            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );

        MySingleton.getInstance( NutritionActivity.this).addToRequestQueue( stringRequest );
    }


    }
