package com.henry.task31c;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    TextView congratulationText, resultText;
    Button takeNewQuiz, finishButton;

    int score, totalQuestion;

    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        congratulationText = findViewById(R.id.textView4);
        resultText = findViewById(R.id.textView5);
        takeNewQuiz = findViewById(R.id.button);
        finishButton = findViewById(R.id.button2);


        //ACTIVATE NEW QUIZ BUTTON
        takeNewQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            //PASSING BACK THE EXIST NAME
            intent.putExtra("userName", userName);
            startActivity(intent);
        });
        //CLOSE THE APP
        finishButton.setOnClickListener(v -> {
            finish();
        });

        //GET VALUE FROM INTENT THAT PASSED FROM DIFFERENT PAGES
        userName = getIntent().getStringExtra("userName");
        score = getIntent().getIntExtra("score", 0);
        totalQuestion = getIntent().getIntExtra("totalQuestion", 0);

        congratulationText.setText("Congratulation to " + userName);
        resultText.setText("Score is " + score + " out of " + totalQuestion);

    }
}
