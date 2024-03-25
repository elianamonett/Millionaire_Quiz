package com.example.millionairequiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class QuestionThreeActivity extends ActivityMaster {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionthree);

        // retrieve from previous activity
        int previousScore = YourPreference.getInstance(this).getIntData("QUESTION_TWO", 0);

        // clear previous SharedPreferences data
        YourPreference.getInstance(this).removeData("QUESTION_THREE");

        // initializing buttons
        Button option1Button = findViewById(R.id.option7Button);
        Button option2Button = findViewById(R.id.option8Button);
        Button option3Button = findViewById(R.id.option9Button);

        // listeners for buttons
        option1Button.setOnClickListener(v -> {
            onAnswerSelected("Osaka");
        });


        option2Button.setOnClickListener(v -> {
            onAnswerSelected("Tokyo");
        });


        option3Button.setOnClickListener(v -> {
            onAnswerSelected("Yokohama");
        });

        // initializing buttons
        Button btnSubmit = findViewById(R.id.btn_submit3);

        // listeners for buttons
        btnSubmit.setOnClickListener(v -> {
            // navigates to next activity once the submit button is clicked
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
        int questionTwoScore = YourPreference.getInstance(this).getIntData("QUESTION_TWO", 0);

        // retrieve the current score from SharedPreferences
        int currentScore = getScore();

        // calculate the new score by adding the score from QuestionOneActivity
        int newScore = currentScore + questionTwoScore;

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
        startActivity(new Intent(QuestionThreeActivity.this, QuestionFourActivity.class));
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
        return new String[]{"Question Three", "Option A", "Option B", "Option C"};
    }

    @Override
    protected int getLayoutResourceId() {
        // shows questionthree.xml
        return R.layout.questionthree;
    }

    @Override
    protected String getCorrectAnswer() {
        // setting correct answer
        return "Tokyo";
    }

    @Override
    protected Class<? extends ActivityMaster> getNextQuestionActivity() {
        return QuestionFourActivity.class;
    }

    @Override
    protected String getScoreKey() {
        return "QUESTION_THREE";
    }
}