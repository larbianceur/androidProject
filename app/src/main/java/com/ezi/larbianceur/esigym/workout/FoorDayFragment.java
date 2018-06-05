package com.ezi.larbianceur.esigym.workout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ezi.larbianceur.esigym.MySingleton;
import com.ezi.larbianceur.esigym.R;
import com.ezi.larbianceur.esigym.model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FoorDayFragment extends Fragment {


    ExpendibleAdapter listAdapter;
    ExpandableListView expandableListView;
    List<Item> items;
    List<String>listHeader;
    HashMap<String,List<Item>> listDataChild;
    String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.workout_fragment,container,false);

        url = "http://192.168.1.3:8080/WorkoutScript.php?DaysNumber=" + 4;

        listHeader = new ArrayList<String>();

        listDataChild = new HashMap<String, List<Item>>();

        StringRequest stringRequest = new StringRequest( Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject( response );

                    for (int compteur = 0; compteur < jsonObject.length(); compteur++) {

                        String type = (String) jsonObject.names().get( compteur );

                        JSONObject jsonObject1 = jsonObject.getJSONObject( type );

                        JSONArray jsonArray = jsonObject1.getJSONArray( "exercices" );

                        listHeader.add(type);

                        items = new ArrayList<Item>();

                        for (int lar = 0; lar < jsonArray.length(); lar++) {
                            items.add( new Item( jsonArray.getJSONObject( lar ).getString( "exercice" ), jsonArray.getJSONObject( lar ).getString( "url" ) ) );
                        }

                        listDataChild.put( listHeader.get( compteur ), items );

                    }
                    listAdapter = new ExpendibleAdapter( getContext(), listHeader, listDataChild );
                    expandableListView = (ExpandableListView)view.findViewById( R.id.expendListView );
                    expandableListView.setAdapter( listAdapter );

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(getContext()).addToRequestQueue( stringRequest );


        return view;
    }
}
