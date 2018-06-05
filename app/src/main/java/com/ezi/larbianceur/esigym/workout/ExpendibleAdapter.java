package com.ezi.larbianceur.esigym.workout;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ezi.larbianceur.esigym.R;
import com.ezi.larbianceur.esigym.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ExpendibleAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<Item>> listHashMap;

    public ExpendibleAdapter(Context context, List<String> listDataHeader, HashMap<String, List<Item>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return   listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get( listDataHeader.get( i )).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get( i );
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get( listDataHeader.get( i ) ).get( i1 );
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {



        String headerTitle = (String)getGroup( i );
        if(view==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE  );
            view = inflater.inflate( R.layout.workout_item_groupview ,null );
        }

        TextView header =(TextView)view.findViewById( R.id.heading );
        header.setText( headerTitle );

        return view;

    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {





        final Item item =(Item)getChild(i,i1);
        LayoutInflater inflater =(LayoutInflater)this.context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        view = inflater.inflate( R.layout.workout_item_view ,null);

        TextView detaill = (TextView)view.findViewById( R.id.childItem );
        detaill.setText( item.getDetaill() );
            ImageButton goToVideo =(ImageButton)view.findViewById( R.id.goToVideo );
        goToVideo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                Intent intent = new Intent( context,VideoWorkout.class );
                context.startActivity( intent );
            }catch (Exception e ){
                Toast.makeText( context,e.getMessage(),Toast.LENGTH_LONG ).show();}
            }
        } );
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
