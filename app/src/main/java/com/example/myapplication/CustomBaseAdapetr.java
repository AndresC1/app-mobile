package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.ViewModel.NotesModel;

public class CustomBaseAdapetr extends BaseAdapter {

    Context context;
    NotesModel dataExample[];
    LayoutInflater inflater;
    public CustomBaseAdapetr(Context ctx, NotesModel [] dataExample){
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
        Button btnEditNote = (Button) convertView.findViewById(R.id.btnEditNote);
        btnEditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditNoteView.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", dataExample[position].getId());
                intent.putExtra("title", dataExample[position].getTitle());
                intent.putExtra("description", dataExample[position].getDescription());
                intent.putExtra("created_at", dataExample[position].getCreated_at());
                intent.putExtra("updated_at", dataExample[position].getUpdated_at());
                context.startActivity(intent);
            }
        });
        txtView.setText(dataExample[position].getTitle());
        return convertView;
    }
}
