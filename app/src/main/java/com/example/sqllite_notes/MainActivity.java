package com.example.sqllite_notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_note;
    MyDatabase myDB;

    ArrayList<String> id_list;
    ArrayList<String> title_list;
    ArrayList<String> content_list;

    ImageView empty_img;
    TextView empty_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empty_img = findViewById(R.id.empty_img);
        empty_str = findViewById(R.id.empty_str);
        recyclerView = findViewById(R.id.recycler);
        add_note = findViewById(R.id.add_note);
        add_note.setOnClickListener(v -> {
            Intent intent = new Intent(this, Add_Note.class);
            startActivity(intent);
        });

        myDB = new MyDatabase(MainActivity.this);
        id_list = new ArrayList<>();
        title_list = new ArrayList<>();
        content_list = new ArrayList<>();

        storeDataInArray();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter adapter = new CustomAdapter(this, this, id_list, title_list, content_list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArray() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            empty_img.setVisibility(View.VISIBLE);
            empty_str.setVisibility(View.VISIBLE);
        }
        else {
            while(cursor.moveToNext()) {
                id_list.add(cursor.getString(0));
                title_list.add(cursor.getString(1));
                content_list.add(cursor.getString(2));
            }
            empty_img.setVisibility(View.GONE);
            empty_str.setVisibility(View.GONE);
        }
    }
}