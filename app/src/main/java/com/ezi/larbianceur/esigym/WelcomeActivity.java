package com.ezi.larbianceur.esigym;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    SharedPreferences prefs;
    private Button inscrption;
    private Button connexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_welcome );
        prefs=getSharedPreferences("MyData", Context.MODE_PRIVATE );

        boolean isLogin = prefs.getBoolean("isConnect", false);

        if(isLogin){
            startActivity( new Intent( this,MainActivity.class ) );
        }





        inscrption =(Button)findViewById(R.id.inscrption);
            connexion =(Button)findViewById(R.id.connexion);





            /*methodes pour inscrir et connecter*/

            connexion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(WelcomeActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            });


            inscrption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(WelcomeActivity.this,RegistrationActivity.class);
                    startActivity(intent);
                }
            });




        }

    }

