package com.example.sqllite_notes;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Add_Note extends AppCompatActivity {

    EditText title_input, content_input;
    Button create_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        title_input = findViewById(R.id.title_input);
        content_input = findViewById(R.id.content_input);
        create_button = findViewById(R.id.create_button);
        create_button.setOnClickListener(v -> {
            if(!title_input.getText().toString().isEmpty()) {
                MyDatabase db = new MyDatabase(this);
                db.addNote(title_input.getText().toString().trim(), content_input.getText().toString().trim());
            }else {
                Toast.makeText(this, "No Title given", Toast.LENGTH_SHORT).show();
            }
        });
    }
}