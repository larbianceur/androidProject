package com.ezi.larbianceur.esigym.suppliment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ezi.larbianceur.esigym.MySingleton;
import com.ezi.larbianceur.esigym.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GainerFragment extends Fragment {


    GridView gv;
    Button nextBtn,prevBtn;
    Paginator p;
    int totalPages;
    private int currentPage=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.gainer_fragment,container,false);


        String url = "http://192.168.1.3:8080/Supplements.php?Type=Gainer";

        gv= (GridView) view.findViewById(R.id.gv);
        nextBtn= (Button) view.findViewById(R.id.nextBtn);
        prevBtn= (Button) view.findViewById(R.id.prevBtn);
        prevBtn.setEnabled(false);
        final ArrayList<Product> products = new ArrayList<Product>(  );


        StringRequest stringRequest = new StringRequest( Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    JSONObject jsonObject = new JSONObject( response );


                    JSONArray itemsJson = jsonObject.getJSONArray( "supplements" );

                    p =new Paginator(itemsJson.length(),4);

                    totalPages =p.TOTAL_NUM_ITEMS/p.ITEMS_PER_PAGE ;

                    for (int i = 0; i < itemsJson.length(); i++) {
                        int id = itemsJson.getJSONObject( i ).getInt( "id" );
                        String name = itemsJson.getJSONObject( i ).getString( "Name" );
                        String urlImage = "http://192.168.1.3:8080/images/"+itemsJson.getJSONObject( i ).getString( "url" );
                        products.add( new Product( urlImage,name,id ) );

                    }
                    gv.setAdapter(new MyAdapter(getContext(),R.layout.row_suppliement,R.id.items,p.generatePage(currentPage,products)));

                    nextBtn= (Button) view.findViewById(R.id.nextBtn);
                    prevBtn= (Button) view.findViewById(R.id.prevBtn);
                    prevBtn.setEnabled(false);

                    nextBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            currentPage=currentPage+1;

                            // enableDisableButtons();
                            gv.setAdapter(new MyAdapter(getContext(),R.layout.row_suppliement,R.id.items,p.generatePage(currentPage,products)));


                            toggleButtons();

                        }
                    });
                    prevBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            currentPage-=1;

                            gv.setAdapter(new MyAdapter(getContext(),R.layout.row_suppliement,R.id.items,p.generatePage(currentPage,products)));

                            toggleButtons();
                        }
                    });




                } catch (Exception e) {
                    Toast.makeText( getContext(),e.getMessage(),Toast.LENGTH_LONG ).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( getContext(),error.getMessage(), Toast.LENGTH_LONG).show();
            }
        } );
        MySingleton.getInstance( getContext()).addToRequestQueue( stringRequest );
        return view;
    }


    private void loadImageFromUrl(String url, ImageButton imageView) {
        Picasso.with( getContext() ).load( url ).placeholder( R.mipmap.ic_launcher )
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
    private void toggleButtons()
    {
        if(currentPage==totalPages)
        {
            nextBtn.setEnabled(false);
            prevBtn.setEnabled(true);
        }else
        if(currentPage==0)
        {
            prevBtn.setEnabled(false);
            nextBtn.setEnabled(true);
        }else

        {
            nextBtn.setEnabled(true);
            prevBtn.setEnabled(true);
        }

    }




}

