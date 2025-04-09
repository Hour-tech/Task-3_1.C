package com.henry.task31c;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    TextView allQuestion;
    TextView progressNumber;
    Button ansA, ansB, ansC;
    Button submitButton, next_Button;
    ProgressBar myProgressBar;
    String userName;
    int progressBarValue;
    int score = 0;
    int totalQuestion = QuizActivity.question.length;
    int currentQuestion = 0;
    String selectedAnswer = "";
    public static String[] question = {
        "What is the value of 2 + 2 ?",
        "What is the value of 2/2?",
        "What is the right location of Deakin university?",
        "What is the value of 2 - 2 ?"
    };

    public static String[][] choices = {
        {"0", "4", "1"},
        {"1", "0", "4"},
        {"Carlton", "Burwood", "Dockland"},
        {"0", "10", "20"}

    };

   public static String[] correctAnswers = {
        "4",
        "1",
        "Burwood",
        "0"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        allQuestion = findViewById(R.id.Allquestion);
        ansA = findViewById(R.id.answerA);
        ansB = findViewById(R.id.answerB);
        ansC = findViewById(R.id.answerC);
        submitButton = findViewById(R.id.buttonSubmit);
        myProgressBar = findViewById(R.id.progressBar);
        next_Button = findViewById(R.id.nextButton);
        progressNumber = findViewById(R.id.numberIncrease);
        progressNumber.setText((0) + "/" + totalQuestion);


        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        next_Button.setOnClickListener(this);

        loadNewQuestion();
        userName = getIntent().getStringExtra("userName");

    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.parseColor("#FF6750a4"));
        ansB.setBackgroundColor(Color.parseColor("#FF6750a4"));
        ansC.setBackgroundColor(Color.parseColor("#FF6750a4"));

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.buttonSubmit){
            if(selectedAnswer.equals(QuizActivity.correctAnswers[currentQuestion])) {
                score++;
                correctAnswer();
            } else {
                wrongAnswer();
            }

        }else{
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.DKGRAY);
        }

        if(clickedButton.getId()==R.id.nextButton) {
            currentQuestion++;
            loadNewQuestion();
            progressNumber.setText((currentQuestion) + "/" + totalQuestion);
            if(progressBarValue < 100)
            {
                progressBarValue += 25;
                myProgressBar.setProgress(progressBarValue);
            }
            next_Button.setBackgroundColor(Color.parseColor("#FF6750a4"));
        }
    }


    void correctAnswer() {

        if (selectedAnswer.equals(ansA.getText().toString())) {
            ansA.setBackgroundColor(Color.GREEN);
        } else if (selectedAnswer.equals(ansB.getText().toString())) {
            ansB.setBackgroundColor(Color.GREEN);
        } else if (selectedAnswer.equals(ansC.getText().toString())) {
            ansC.setBackgroundColor(Color.GREEN);
        }
    }

    void wrongAnswer() {

        if (selectedAnswer.equals(ansA.getText().toString())) {
            ansA.setBackgroundColor(Color.RED);
        } else if (selectedAnswer.equals(ansB.getText().toString())) {
            ansB.setBackgroundColor(Color.RED);
        } else if (selectedAnswer.equals(ansC.getText().toString())) {
            ansC.setBackgroundColor(Color.RED);
        }

        if (ansA.getText().toString().equals(QuizActivity.correctAnswers[currentQuestion])) {
            ansA.setBackgroundColor(Color.GREEN);
        } else if (ansB.getText().toString().equals(QuizActivity.correctAnswers[currentQuestion])) {
            ansB.setBackgroundColor(Color.GREEN);
        } else if (ansC.getText().toString().equals(QuizActivity.correctAnswers[currentQuestion])) {
            ansC.setBackgroundColor(Color.GREEN);
        }
    }

    void loadNewQuestion() {
        if(currentQuestion == totalQuestion) {
            finishQuiz();
            return;
        }
        allQuestion.setText(QuizActivity.question[currentQuestion]);
        ansA.setText(QuizActivity.choices[currentQuestion][0]);
        ansB.setText(QuizActivity.choices[currentQuestion][1]);
        ansC.setText(QuizActivity.choices[currentQuestion][2]);
    }

    void finishQuiz() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("score", score);
        intent.putExtra("totalQuestion", totalQuestion);
        startActivity(intent);
    }

}
