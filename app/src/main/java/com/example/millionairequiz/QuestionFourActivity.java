package com.example.millionairequiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class QuestionFourActivity extends ActivityMaster {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionfour);

        // retrieves score
        int previousScore = YourPreference.getInstance(this).getIntData("QUESTION_THREE", 0);


        // clears previous data
        YourPreference.getInstance(this).removeData("QUESTION_FOUR");

        // initializing buttons
        Button option1Button = findViewById(R.id.option1Button);
        Button option2Button = findViewById(R.id.option2Button);
        Button option3Button = findViewById(R.id.option3Button);

        // listeners for buttons
        option1Button.setOnClickListener(v -> {
            onAnswerSelected("La Plata");
        });

        option2Button.setOnClickListener(v -> {
            onAnswerSelected("Rosario");
        });

        option3Button.setOnClickListener(v -> {
            onAnswerSelected("Buenos Aires");
        });

        // initializing submit button
        Button btnSubmit = findViewById(R.id.btn_submit);

        // listener for submit button
        btnSubmit.setOnClickListener(v -> {
            // go to next activity once submit button is clicked
            navigateToNextQuestion();
        });
    }

    @Override
    protected void checkAnswer(String selectedAnswer) {
        // call ActivityMaster to handle the answer checking
        super.checkAnswer(selectedAnswer);
    }

    @Override
    protected int getScore() {
        // retrieve score stored in SharedPreferences
        return YourPreference.getInstance(this).getIntData(getScoreKey(), 0);
    }

    @Override
    protected void incrementScore(String selectedAnswer) {
        // retrieve the score from the previous question
        int questionThreeScore = YourPreference.getInstance(this).getIntData("QUESTION_THREE", 0);

        // Retrieve the current score from SharedPreferences
        int currentScore = getScore();

        // calculate the new score by adding the score
        int newScore = currentScore + questionThreeScore;

        // save the updated score to SharedPreferences
        saveScore(newScore);

        Log.d("ActivityMaster", "Incrementing score. New score: " + newScore);
    }


    protected void saveScore(int score) {
        // save to SharedPreferences
        String scoreKey = getScoreKey();
        YourPreference.getInstance(this).saveData(scoreKey, score);
    }

    private void navigateToNextQuestion() {
        // go to next activity
        startActivity(new Intent(QuestionFourActivity.this, QuestionFiveActivity.class));
        finish(); // finish this activity
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
        // retrieve question data
        return new String[]{"Question Four", "Option A", "Option B", "Option C"};
    }

    @Override
    protected int getLayoutResourceId() {
        // show questionfour.xml
        return R.layout.questionfour;
    }

    @Override
    protected String getCorrectAnswer() {
        // set correct answer
        return "Buenos Aires";
    }

    @Override
    protected Class<? extends ActivityMaster> getNextQuestionActivity() {
        return QuestionFiveActivity.class;
    }

    @Override
    protected String getScoreKey() {
        return "QUESTION_FOUR";
    }
}