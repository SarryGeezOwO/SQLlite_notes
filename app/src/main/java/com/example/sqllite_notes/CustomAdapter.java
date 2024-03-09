package com.example.sqllite_notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList<String> id_list;
    ArrayList<String> title_list;
    ArrayList<String> content_list;

    CustomAdapter(Context context, Activity activity,
                    ArrayList<String> id_list,
                    ArrayList<String> title_list,
                    ArrayList<String> content_list) {
        this.context = context;
        this.activity = activity;
        this.id_list = id_list;
        this.title_list = title_list;
        this.content_list = content_list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.note_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title_output.setText(title_list.get(position));
        holder.content_output.setText(content_list.get(position));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateNote.class);
                intent.putExtra("id", String.valueOf(id_list.get(position)));
                intent.putExtra("title", String.valueOf(title_list.get(position)));
                intent.putExtra("content", String.valueOf(content_list.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }


    @Override
    public int getItemCount() {
        return id_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title_output, content_output;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title_output = itemView.findViewById(R.id.title_output);
            content_output = itemView.findViewById(R.id.content_output);
            mainLayout = itemView.findViewById(R.id.note_view);
        }
    }
}
