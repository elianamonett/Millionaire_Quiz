package com.example.millionairequiz;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class ActivityMaster extends AppCompatActivity {

    protected abstract int getScore();

    protected abstract void incrementScore(String selectedAnswer);

    protected abstract int getCurrentQuestionIndex();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        setupQuestion();
    }

    protected abstract void setupQuestion();

    protected abstract String[] getQuestionData(int questionIndex);

    protected abstract int getLayoutResourceId();

    protected abstract String getCorrectAnswer();

    protected abstract Class<? extends ActivityMaster> getNextQuestionActivity();

    protected abstract String getScoreKey();

    protected void onAnswerSelected(String selectedAnswer) {
        checkAnswer(selectedAnswer);
    }

    protected void checkAnswer(String selectedAnswer) {
        boolean isCorrect = selectedAnswer.equals(getCorrectAnswer());

        if (isCorrect) {
            Toast.makeText(this, "Correct answer!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect answer!", Toast.LENGTH_SHORT).show();
        }
    }
}
