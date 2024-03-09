package com.example.sqllite_notes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateNote extends AppCompatActivity {

    TextView title_input, content_input;
    Button update_btn, delete_btn;

    String id, title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        title_input = findViewById(R.id.title_input_update);
        content_input = findViewById(R.id.content_input_update);
        update_btn = findViewById(R.id.update_button);
        delete_btn = findViewById(R.id.delete_button);

        getAndSetData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_btn.setOnClickListener(v -> {
            if(!title_input.getText().toString().isEmpty()) {
                MyDatabase db = new MyDatabase(this);
                title = title_input.getText().toString().trim();
                content = content_input.getText().toString().trim();
                db.updateData(id, title, content);
            }else {
                Toast.makeText(this, "No Title given", Toast.LENGTH_SHORT).show();
            }
        });

        delete_btn.setOnClickListener(v -> {
            confirmDialog();
        });
    }

    public void getAndSetData() {
        Intent i = getIntent();
        if(i.hasExtra("id") && i.hasExtra("title") && i.hasExtra("content")) {
            id = i.getStringExtra("id");
            title = i.getStringExtra("title");
            content = i.getStringExtra("content");

            title_input.setText(title);
            content_input.setText(content);
            Log.d("jay", title+" "+content);
        }else
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
    }

    public void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete this note?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabase db = new MyDatabase(UpdateNote.this);
                db.deleteRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}