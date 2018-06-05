package com.ezi.larbianceur.esigym;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    SharedPreferences prefs;
    Button registerButton;
    EditText firstName,Email,lastName,Password,ConfirmPassword;
    String but;
    EditText poids;
    AlertDialog.Builder builder;
    String regUrl = "http://192.168.1.3:8080/registration.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registration );

        prefs=getSharedPreferences("MyData", Context.MODE_PRIVATE );

        boolean isLogin = prefs.getBoolean("isConnect", false);

        if(isLogin){
            startActivity( new Intent( this,MainActivity.class ) );
        }

        final Spinner spinner = (Spinner)findViewById( R.id.spinner );
        final List<String> spinnerArrayM = new ArrayList<String>(  );
        spinnerArrayM.add( "Gaining Muscles" );
        spinnerArrayM.add( "Burning Fats" );
        ArrayAdapter<String> adapterM = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item,spinnerArrayM);
        spinner.setAdapter( adapterM );
        registerButton = (Button)findViewById( R.id.registerBtn );
        firstName = (EditText)findViewById( R.id.firstName );
        Email = (EditText)findViewById( R.id.email );
        lastName = (EditText)findViewById( R.id.lastName );
        poids = (EditText)findViewById( R.id.weight );

        if(savedInstanceState != null){
            Email.setText((String) savedInstanceState.get( "email" )  );
            firstName.setText( (String)savedInstanceState.get("firstName") );
            lastName.setText( (String)savedInstanceState.get("lastName") );
            lastName.setText( (String)savedInstanceState.get("poids") );
        }

        Password = (EditText)findViewById( R.id.password );
        ConfirmPassword = (EditText)findViewById( R.id.password2 );

        builder = new AlertDialog.Builder( RegistrationActivity.this );
        try {



        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                but = spinnerArrayM.get( i );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                but = spinnerArrayM.get( 0 );
            }
        } );}
        catch (Exception e){
            Toast.makeText( RegistrationActivity.this,e.getMessage(),Toast.LENGTH_LONG ).show();
        }

        registerButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final User user = new User(firstName.getText().toString(),lastName.getText().toString(),Email.getText().toString(),Password.getText().toString(),ConfirmPassword.getText().toString());

                UserController userController = new UserController();

                Toast.makeText( RegistrationActivity.this,user.getName(),Toast.LENGTH_LONG ).show();

                String message =userController.checkInformation( user );
                if(!message.equals( "succes" )){
                builder.setTitle( "error" );
                builder.setMessage( message );
                displayAlert("input_error");
                } else {
                    StringRequest stringRequest = new StringRequest( Request.Method.POST, regUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                Toast.makeText( RegistrationActivity.this,response,Toast.LENGTH_LONG ).show();
                               JSONArray jsonArray = new JSONArray( response);

                              JSONObject jsonObject = jsonArray.getJSONObject( 0 );


                                String code = jsonObject.getString( "code" );
                                String message = jsonObject.getString( "message" );


                                Toast.makeText(RegistrationActivity.this,code,Toast.LENGTH_LONG );
                                builder.setTitle( "server Response" );
                                builder.setMessage( message );
                                displayAlert( code );

                            } catch (Exception e) {
                              Toast.makeText( RegistrationActivity.this ,e.getMessage(),Toast.LENGTH_LONG).show();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText( RegistrationActivity.this,error.getMessage(),Toast.LENGTH_LONG ).show();

                        }
                    } ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                           Map<String,String> params = new HashMap<String, String>();

                            params.put( "firstName",user.getName() );
                            params.put( "but",but );
                            params.put( "poids",poids.getText().toString());
                            params.put("lastName",user.getUserName());
                            params.put("email",user.getEmail());
                            params.put( "password",user.getPassword());
                            return params;
                        }
                    };
                    MySingleton.getInstance( RegistrationActivity.this).addToRequestQueue( stringRequest );
                }

                }
        } );


    }

    private void displayAlert(final String code) {
        builder.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
             if(code.equals( "input_error" )){
                Password.setText( "" );
                ConfirmPassword.setText( "" );
             }
             else if(code.equals( "req_succes" )){
                 prefs =getSharedPreferences("MyData", Context.MODE_PRIVATE );
                 SharedPreferences.Editor editor = prefs.edit();
                 editor.putBoolean( "isConnect",true );
                 editor.commit();
                 Intent intent = new Intent( RegistrationActivity.this,MainActivity.class );
                 startActivity( intent );
                 finish();
             }
             else if (code.equals( "req_failed" )){
                 firstName.setText( "" );
                 lastName.setText( "" );
                 Password.setText( "" );
                 ConfirmPassword.setText( "" );
             }
            }
        } );

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public void onSaveInstanceState(Bundle etat) {
        etat.putString("firstName",firstName.getText().toString() );
        etat.putString( "email",Email.getText().toString() );
        etat.putString( "lastName",lastName.getText().toString() );
        super.onSaveInstanceState(etat);
    }

}
