package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.TextView;
import com.example.myapplication.ViewModel.NotesModel;

public class CustomBaseAdapetr extends BaseAdapter {

    Context context;
    NotesModel dataExample[];
    HomeView homeView;
    public CustomBaseAdapetr(Context ctx, NotesModel [] dataExample, HomeView homeView){
        this.context = ctx;
        this.dataExample = dataExample;
        this.homeView = homeView;
    }
    @Override
    public int getCount() {
        return dataExample.length;
    }

    @Override
    public Object getItem(int position) {
        return dataExample[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_custom_list_view, null);
        TextView txtView = (TextView) convertView.findViewById(R.id.txtNameNote);
        TextView txtIDNote = (TextView) convertView.findViewById(R.id.txtIDNote);
        TextView txtCreatedAt = (TextView) convertView.findViewById(R.id.txtCreatedAtNote);
        String title = dataExample[position].getTitle();

        txtView.setText(title.length() < 30 ? title : title.substring(0, 30) + "...");
        txtIDNote.setText(String.valueOf(dataExample[position].getId()));
        txtCreatedAt.setText(dataExample[position].getCreated_at());
        txtIDNote.setVisibility(View.GONE);
        return convertView;
    }

    public void openEditNoteView(NotesModel note){
        Intent intent = new Intent(context, EditNoteView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("id", note.getId());
        intent.putExtra("title", note.getTitle());
        intent.putExtra("description", note.getDescription());
        intent.putExtra("created_at", note.getCreated_at());
        intent.putExtra("updated_at", note.getUpdated_at());
        context.startActivity(intent);
    }
}
