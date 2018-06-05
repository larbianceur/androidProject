package com.ezi.larbianceur.esigym.suppliment.details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ezi.larbianceur.esigym.LoginActivity;
import com.ezi.larbianceur.esigym.MySingleton;
import com.ezi.larbianceur.esigym.R;
import com.ezi.larbianceur.esigym.model.Item;
import com.ezi.larbianceur.esigym.model.ItemSuppliment;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SupplimentDetails extends AppCompatActivity {



    ExpendibleAdapterSupp listAdapter;
    ExpandableListView expandableListView;
    List<String>listHeader;
    HashMap<String,ItemSuppliment> listDataChild;
    String url;
ImageView imageView;
Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliment_details);
        // *** l'image ***

        toolbar =(Toolbar)findViewById( R.id.toolbar );
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listHeader = new ArrayList<String>();

        listDataChild = new HashMap<String, ItemSuppliment>();
        imageView = (ImageView)findViewById( R.id.image );



        int id = getIntent().getIntExtra( "idImage",0 );

        url = "http://192.168.1.3:8080/SupplementDetails.php?id="+id;
        StringRequest stringRequest = new StringRequest( Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;

                try {
                    jsonArray = new JSONArray( response );

                    JSONObject jsonObject = jsonArray.getJSONObject( 0 );

                    listHeader.add( "Name" );

                    listDataChild.put( listHeader.get( 0 ),new ItemSuppliment( jsonObject.getString( "name" )));

                    loadImage(imageView,jsonObject.getString( "url" ));


                    listHeader.add( "category" );

                    listDataChild.put( listHeader.get( 1 ),new ItemSuppliment( jsonObject.getString( "category" )));

                    listHeader.add( "advise" );

                    listDataChild.put( listHeader.get( 2 ),new ItemSuppliment( jsonObject.getString( "advise" )));
                    listHeader.add( "ingredient" );

                    listDataChild.put( listHeader.get( 3 ),new ItemSuppliment( jsonObject.getString( "ingredient")));



                    listHeader.add( "prix" );

                    listDataChild.put( listHeader.get( 4 ),new ItemSuppliment(  jsonObject.getString( "prix") ));


                    listAdapter = new ExpendibleAdapterSupp( SupplimentDetails.this, listHeader, listDataChild );
                    expandableListView = (ExpandableListView)findViewById( R.id.expendListView );
                    expandableListView.setAdapter( listAdapter );
                 //  Toast.makeText( SupplimentDetails.this,response,Toast.LENGTH_LONG ).show();
                } catch (Exception e) {

                    Toast.makeText( SupplimentDetails.this,e.getMessage(),Toast.LENGTH_LONG ).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance( SupplimentDetails.this).addToRequestQueue( stringRequest );



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected( item );
    }

    private void loadImage(ImageView imageView, String image) {

        String url ="http://192.168.1.3:8080/images/"+image;

        Picasso.with(SupplimentDetails.this).load( url ).placeholder( R.mipmap.ic_launcher )
                .error( R.mipmap.ic_launcher )
                .into( imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                } );
    }
    }



