package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.TextView;

public class CustomBaseAdapetr extends BaseAdapter {

    Context context;
    String dataExample[];
    LayoutInflater inflater;
    public CustomBaseAdapetr(Context ctx, String [] dataExample){
        this.context = ctx;
        this.dataExample = dataExample;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return dataExample.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView txtView = (TextView) convertView.findViewById(R.id.txtNameNote);
        txtView.setText(dataExample[position]);
        return convertView;
    }
}
