package com.example.millionairequiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class QuestionTenActivity extends ActivityMaster {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionten);

        // retrieve from previous activity
        int previousScore = YourPreference.getInstance(this).getIntData("QUESTION_NINE", 0);

        // clears previous SharedPreferences data
        YourPreference.getInstance(this).removeData("QUESTION_TEN");

        // initializing buttons
        Button option1Button = findViewById(R.id.option1Button);
        Button option2Button = findViewById(R.id.option2Button);
        Button option3Button = findViewById(R.id.option3Button);

        // listeners for buttons
        option1Button.setOnClickListener(v -> {
            onAnswerSelected("Oslo");
        });


        option2Button.setOnClickListener(v -> {
            onAnswerSelected("Bergen");
        });


        option3Button.setOnClickListener(v -> {
            onAnswerSelected("Trondheim");
        });

        // initializing buttons
        Button btnSubmit = findViewById(R.id.btn_submit);

        // listener for buttons
        btnSubmit.setOnClickListener(v -> {
            // goes to next activity once submit button is clicked
            navigateToNextActivity();
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
        // retrieve the scores from the previous questions
        int questionOneScore = YourPreference.getInstance(this).getIntData("QUESTION_ONE", 0);
        int questionTwoScore = YourPreference.getInstance(this).getIntData("QUESTION_TWO", 0);
        int questionThreeScore = YourPreference.getInstance(this).getIntData("QUESTION_THREE", 0);
        int questionFourScore = YourPreference.getInstance(this).getIntData("QUESTION_FOUR", 0);
        int questionFiveScore = YourPreference.getInstance(this).getIntData("QUESTION_FIVE", 0);
        int questionSixScore = YourPreference.getInstance(this).getIntData("QUESTION_SIX", 0);
        int questionSevenScore = YourPreference.getInstance(this).getIntData("QUESTION_SEVEN", 0);
        int questionEightScore = YourPreference.getInstance(this).getIntData("QUESTION_EIGHT", 0);
        int questionNineScore = YourPreference.getInstance(this).getIntData("QUESTION_NINE", 0);

        // retrieve the current score from SharedPreferences
        int currentScore = getScore();

        // calculate the new score by adding the scores
        int newScore = currentScore + questionOneScore + questionTwoScore + questionThreeScore + questionFourScore
                + questionFiveScore + questionSixScore + questionSevenScore + questionEightScore + questionNineScore;

        // save the updated score to SharedPreferences
        saveScore(newScore);

        Log.d("ActivityMaster", "Incrementing score. New score: " + newScore);
    }

    protected void saveScore(int score) {
        // save the score to SharedPreferences
        YourPreference.getInstance(this).saveData(getScoreKey(), score);
    }

    private void navigateToNextActivity() {
        int totalScore = getScore();

        if (totalScore >= 1000000) {
            // goes to WinningActivity if total score is at least 1000000
            startActivity(new Intent(QuestionTenActivity.this, WinningActivity.class));
        } else {
            // goes to LosingActivity otherwise
            startActivity(new Intent(QuestionTenActivity.this, LosingActivity.class));
        }

        finish(); // finisher
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
        return new String[]{"Question Ten", "Option A", "Option B", "Option C"};
    }

    @Override
    protected int getLayoutResourceId() {
        //shows matching xml file
        return R.layout.questionten;
    }

    @Override
    protected String getCorrectAnswer() {
        return "Oslo";
    }

    @Override
    protected Class<? extends ActivityMaster> getNextQuestionActivity() {
        return QuestionSevenActivity.class;
    }

    @Override
    protected String getScoreKey() {
        return "QUESTION_TEN";
    }
}