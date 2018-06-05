package com.ezi.larbianceur.esigym.suppliment.details;


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
import com.ezi.larbianceur.esigym.model.ItemSuppliment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ExpendibleAdapterSupp extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,ItemSuppliment> listHashMap;

    public ExpendibleAdapterSupp(Context context, List<String> listDataHeader, HashMap<String,ItemSuppliment> listHashMap) {
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
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get( i );
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get( listDataHeader.get( i ) );
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
            view = inflater.inflate( R.layout.group_item_suppliment_detail ,null );
        }

        TextView header =(TextView)view.findViewById( R.id.heading );
        header.setText( headerTitle );

        return view;

    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {





        final ItemSuppliment item =(ItemSuppliment) getChild(i,i1);
        LayoutInflater inflater =(LayoutInflater)this.context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        view = inflater.inflate( R.layout.child_item_suppliment_detail ,null);

        TextView detaill = (TextView)view.findViewById( R.id.detaill );
        detaill.setText( item.getContenu() );

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
