package com.example.millionairequiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class QuestionEightActivity extends ActivityMaster {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questioneight);

        // retrieve from previous activity
        int previousScore = YourPreference.getInstance(this).getIntData("QUESTION_SEVEN", 0);


        // clears previous SharedPreferences data
        YourPreference.getInstance(this).removeData("QUESTION_EIGHT");

        // initializing buttons
        Button option1Button = findViewById(R.id.option1Button);
        Button option2Button = findViewById(R.id.option2Button);
        Button option3Button = findViewById(R.id.option3Button);

        // listeners for buttons
        option1Button.setOnClickListener(v -> {
            onAnswerSelected("Mombasa");
        });


        option2Button.setOnClickListener(v -> {
            onAnswerSelected("Meru");
        });


        option3Button.setOnClickListener(v -> {
            onAnswerSelected("Nairobi");
        });

        // initializing buttons
        Button btnSubmit = findViewById(R.id.btn_submit);

        // listener for buttons
        btnSubmit.setOnClickListener(v -> {
            // goes to next activity once submit button is clicked
            navigateToNextQuestion();
        });
    }

    @Override
    protected void checkAnswer(String selectedAnswer) {
        // calls activitymaster to handle the answer checking
        super.checkAnswer(selectedAnswer);
    }

    @Override
    protected int getScore() {
        // retrieves score stored in sharedpreferences
        return YourPreference.getInstance(this).getIntData(getScoreKey(), 0);
    }

    @Override
    protected void incrementScore(String selectedAnswer) {
        // retrieve the score from the previous question
        int questionSevenScore = YourPreference.getInstance(this).getIntData("QUESTION_SEVEN", 0);

        // retrieve the current score from SharedPreferences
        int currentScore = getScore();

        // calculate the new score by adding the score
        int newScore = currentScore + questionSevenScore;

        // save the updated score to SharedPreferences
        saveScore(newScore);

        Log.d("ActivityMaster", "Incrementing score. New score: " + newScore);
    }

    protected void saveScore(int score) {
        // Save the score to SharedPreferences
        YourPreference.getInstance(this).saveData(getScoreKey(), score);
    }

    private void navigateToNextQuestion() {
        // goes to next activity
        startActivity(new Intent(QuestionEightActivity.this, QuestionNineActivity.class));
        finish(); // can finish
    }

    @Override
    protected int getCurrentQuestionIndex() {
        return 1;
    }

    @Override
    protected void setupQuestion() {
    }

    @Override
    protected String[] getQuestionData(int questionIndex) {
        // retrieves question data
        return new String[]{"Question Eight", "Option A", "Option B", "Option C"};
    }

    @Override
    protected int getLayoutResourceId() {
        //shows matching xml file
        return R.layout.questioneight;
    }

    @Override
    protected String getCorrectAnswer() {
        return "Nairobi";
    }

    @Override
    protected Class<? extends ActivityMaster> getNextQuestionActivity() {
        return QuestionNineActivity.class;
    }

    @Override
    protected String getScoreKey() {
        return "QUESTION_EIGHT";
    }
}