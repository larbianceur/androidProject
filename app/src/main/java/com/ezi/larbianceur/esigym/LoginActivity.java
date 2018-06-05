package com.ezi.larbianceur.esigym;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ezi.larbianceur.esigym.controller.UserController;
import com.ezi.larbianceur.esigym.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences prefs;
    Button goToRegister,login;
    String url = "http://192.168.1.3:8080/login.php";
    AlertDialog.Builder builder;
    EditText password,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        prefs=getSharedPreferences("MyData", Context.MODE_PRIVATE );

        boolean isLogin = prefs.getBoolean("isConnect", false);

        if(isLogin){
            startActivity( new Intent( this,MainActivity.class ) );
        }


        email = (EditText)findViewById( R.id.userNameInput );

        if(savedInstanceState != null){
            email.setText((String) savedInstanceState.get( "email" ));
        }

        password = (EditText)findViewById( R.id.passwordInput );
        login =(Button) findViewById( R.id.login );
        builder = new AlertDialog.Builder( LoginActivity.this );

        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user = new User(email.getText().toString(),password.getText().toString());

                UserController userController = new UserController();



                String message = userController.checkLogin(user);

                if(!message.equals( "sucess" )) {

                    builder.setTitle( "error" );
                    builder.setMessage( message );
                    displayAlert();
                }else {
                    StringRequest stringRequest = new StringRequest( Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray( response );
                                JSONObject jsonObject = jsonArray.getJSONObject( 0 );
                                String code = jsonObject.getString( "code" );
                                if(code.equals( "login_fialed" )){
                                    builder.setTitle( "Login Error.." );
                                    builder.setMessage(  jsonObject.getString( "message" )  );
                                    displayAlert();
                                }else {
                                    prefs =getSharedPreferences("MyData", Context.MODE_PRIVATE );
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putBoolean( "isConnect",true );
                                    int id = jsonObject.getInt( "id" );
                                    editor.putInt( "id", id);
                                    editor.commit();
                                    Intent intent = new Intent( LoginActivity.this,MainActivity.class );
                                    startActivity( intent );
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    } )  {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();

                            params.put("email",user.getEmail());
                            params.put( "password",user.getPassword());
                            return params;

                        }
                    };
                    MySingleton.getInstance( LoginActivity.this).addToRequestQueue( stringRequest );
                }

            }
        } );


    /*    goToRegister = (Button)findViewById( R.id.goToReg );
        goToRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( LoginActivity.this,RegistrationActivity.class ) );

            }
        } );

*/    }

    private void displayAlert() {

        builder.setPositiveButton( "ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                email.setText( "" );
                password.setText( "" );
            }
        } );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public void onSaveInstanceState(Bundle etat) {
        etat.putString("email", email.getText().toString());
        super.onSaveInstanceState(etat);
    }

}
