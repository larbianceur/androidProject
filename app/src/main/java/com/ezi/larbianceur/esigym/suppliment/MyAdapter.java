package com.ezi.larbianceur.esigym.suppliment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezi.larbianceur.esigym.R;
import com.ezi.larbianceur.esigym.suppliment.details.SupplimentDetails;
import com.squareup.picasso.Picasso;

import java.util.List;


    public class MyAdapter extends ArrayAdapter<Product> {



        public MyAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Product> objects) {
            super( context, resource, textViewResourceId, objects );
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            View view1 = LayoutInflater.from( getContext()).inflate( R.layout.row_suppliement,null );
            ImageButton imageView = (ImageButton) view1.findViewById( R.id.mb1 );
            final Product item = getItem( i );
            TextView name = (TextView)view1.findViewById( R.id.supplementName );

            name.setText( item.name );

            loadImageFromUrl( item.urlImg,imageView );

            imageView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  Intent intent = new Intent( getContext(),SupplimentDetails.class);
                    intent.putExtra( "idImage",item.id );
                    getContext().startActivity( intent );
                }
            } );

            return view1;
        }

        private void loadImageFromUrl(String url, ImageView imageView) {
            Picasso.with(getContext()).load( url ).placeholder( R.mipmap.ic_launcher )
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
