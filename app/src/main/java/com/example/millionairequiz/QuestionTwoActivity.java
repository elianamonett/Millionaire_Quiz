package com.example.millionairequiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class QuestionTwoActivity extends ActivityMaster {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questiontwo);

        // retrieve from previous activity
        int previousScore = YourPreference.getInstance(this).getIntData("QUESTION_ONE", 0);

        // clears previous SharedPreferences data
        YourPreference.getInstance(this).removeData("QUESTION_TWO");

        // initializing buttons
        Button option1Button = findViewById(R.id.option4Button);
        Button option2Button = findViewById(R.id.option5Button);
        Button option3Button = findViewById(R.id.option6Button);

        // listeners for buttons
        option1Button.setOnClickListener(v -> {
            onAnswerSelected("Moscow");
        });


        option2Button.setOnClickListener(v -> {
            onAnswerSelected("Saint Petersburg");
        });


        option3Button.setOnClickListener(v -> {
            onAnswerSelected("Marseille");
        });

        // initializing buttons
        Button btnSubmit = findViewById(R.id.btn_submit2);

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
        // Retrieve the score from the previous question (QuestionOneActivity)
        int questionOneScore = YourPreference.getInstance(this).getIntData("QUESTION_ONE", 0);

        // Retrieve the current score from SharedPreferences
        int currentScore = getScore();

        // Calculate the new score by adding the score from QuestionOneActivity
        int newScore = currentScore + questionOneScore;

        // Save the updated score to SharedPreferences
        saveScore(newScore);

        Log.d("ActivityMaster", "Incrementing score. New score: " + newScore);
    }

    protected void saveScore(int score) {
        // Save the score to SharedPreferences
        YourPreference.getInstance(this).saveData(getScoreKey(), score);
    }

    private void navigateToNextQuestion() {
        // goes to next activity
        startActivity(new Intent(QuestionTwoActivity.this, QuestionThreeActivity.class));
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
        return new String[]{"Question Two", "Option A", "Option B", "Option C"};
    }

    @Override
    protected int getLayoutResourceId() {
        //shows matching xml file
        return R.layout.questiontwo;
    }

    @Override
    protected String getCorrectAnswer() {
        return "Moscow";
    }

    @Override
    protected Class<? extends ActivityMaster> getNextQuestionActivity() {
        return QuestionThreeActivity.class;
    }

    @Override
    protected String getScoreKey() {
        return "QUESTION_TWO";
    }
}