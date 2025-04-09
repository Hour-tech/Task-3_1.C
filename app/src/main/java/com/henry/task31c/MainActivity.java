package com.henry.task31c;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {


    RecyclerView myrecyclerView;
    Button startButton;
    EditText userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myrecyclerView = findViewById(R.id.recyclerView);
        startButton = findViewById(R.id.buttonStart);
        userName = findViewById(R.id.editTextText2);

        //GET THE EXISTING NAME AND DISPLAY AGAIN
        String oldName = getIntent().getStringExtra("userName");
        if (oldName != null)
        {
            userName.setText(oldName);
        }

        //START BUTTON ACTIVATED
        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            //store user name and retrieve to result page
            String name = userName.getText().toString();
            intent.putExtra("userName", name);
            startActivity(intent);
        });
     }
}
