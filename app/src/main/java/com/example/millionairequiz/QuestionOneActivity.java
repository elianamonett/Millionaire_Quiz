package com.example.millionairequiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//ideally this extends the activitymaster and allows the app the evaluate the answer
// ideally user answer will be stored in sharedpreferences
// all other activity classes follow suit

public class QuestionOneActivity extends ActivityMaster {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionone);

        // clears previous data
        YourPreference.getInstance(this).removeData("QUESTION_ONE");

        // initializing buttons
        Button option1Button = findViewById(R.id.option1Button);
        Button option2Button = findViewById(R.id.option2Button);
        Button option3Button = findViewById(R.id.option3Button);

        // setting listeners
        option1Button.setOnClickListener(v -> {
            onAnswerSelected("Paris");
        });


        option2Button.setOnClickListener(v -> {
            onAnswerSelected("Nice");
        });


        option3Button.setOnClickListener(v -> {
            onAnswerSelected("Marseille");
        });

        // initializing button
        Button btnSubmit = findViewById(R.id.btn_submit);

        // setting listener
        btnSubmit.setOnClickListener(v -> {
            // goes to next question after the submit button is clicked
            navigateToNextQuestion();
        });
    }

    @Override
    protected void checkAnswer(String selectedAnswer) {
        // extends activitymaster to handle checking the answer
        super.checkAnswer(selectedAnswer);
    }

    public void handleChoiceButtonClick(View view) {
        Button clickedButton = (Button) view;
        String selectedAnswer = clickedButton.getText().toString();
    }

    protected void onAnswerSelected(String selectedAnswer) {
        // evaluates answer and updates UI
        checkAnswer(selectedAnswer);
    }

    protected void saveScore(int score) {
        // Save the score to SharedPreferences
        YourPreference.getInstance(this).saveData(getScoreKey(), score);
    }

    private void navigateToNextQuestion() {
        // goes to next question
        startActivity(new Intent(QuestionOneActivity.this, QuestionTwoActivity.class));
        finish(); // finisher
    }

    @Override
    protected int getScore() {
        return 0;
    }

    @Override
    protected void incrementScore(String selectedAnswer) {
        // Check if the selected answer is correct
        if (selectedAnswer.equals(getCorrectAnswer())) {
            // Increment the score
            int currentScore = getScore();
            int newScore = currentScore + 1; // Incrementing score by 1 for each correct answer
            saveScore(newScore);
        }
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
        return new String[]{"Question One", "Option A", "Option B", "Option C"};
    }

    @Override
    protected int getLayoutResourceId() {
        // displays appropriate xml file
        return R.layout.questionone;
    }

    @Override
    protected String getCorrectAnswer() {
        return "Paris";
    }

    @Override
    protected Class<? extends ActivityMaster> getNextQuestionActivity() {
        return QuestionTwoActivity.class;
    }

    @Override
    protected String getScoreKey() {
        return "QUESTION_ONE";
    }
}