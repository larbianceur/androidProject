package com.ezi.larbianceur.esigym;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Larbi Anceur on 18-May-18.
 */

public class MySingleton {
    private static MySingleton mySingleton;
    private RequestQueue requestQueue;
    private static Context context;

    private MySingleton(Context context1) {
        context = context1;
        requestQueue = getRequestQueue();
    }
    public RequestQueue getRequestQueue (){
        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue( context.getApplicationContext() );

        }
        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context ctx){
        if(mySingleton==null){
            mySingleton = new MySingleton( ctx );
        }
        return mySingleton;
    }

    public <T> void addToRequestQueue(Request<T> request){
       requestQueue.add( request );
    }



}

