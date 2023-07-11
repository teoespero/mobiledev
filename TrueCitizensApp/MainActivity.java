package teoespero.truecitizenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import teoespero.truecitizenapp.databinding.ActivityMainBinding;
import teoespero.truecitizenapp.model.Question;

/**
 * This app is to demonstrate the use of arrays by implementing the US Citizenship Civics test.
 * @author Teo Espero (BS Software Development, WGU)
 * @version 1.0
 * @since 07/11/2023
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;

    private Question[] questionsBank = new Question[]{
        // instantiate questions
        new Question(R.string.question_amendments, false),
        new Question(R.string.question_constitution, true),
        new Question(R.string.question_declaration, true),
        new Question(R.string.question_independence_rights, true),
        new Question(R.string.question_religion, true),
        new Question(R.string.question_government, false),
        new Question(R.string.question_government_feds, false),
        new Question(R.string.question_government_senators, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        updateQuestion();
        binding.nextButton.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questionsBank.length;
            updateQuestion();
        });

        binding.previousButton.setOnClickListener(view -> {
            if (currentQuestionIndex > 0){
                currentQuestionIndex = (currentQuestionIndex - 1) % questionsBank.length;
                updateQuestion();
            }

        });

        binding.trueButton.setOnClickListener(view ->{
            checkAnswer(true);
        });

        binding.falseButton.setOnClickListener(view ->{
            checkAnswer(false);
        });
    }

    private void updateQuestion() {
        binding.questionTextView.setText(questionsBank[currentQuestionIndex].getAnswerResId());

    }

    private void checkAnswer(boolean userChoseCorrect){
        boolean answerIsCorrect = questionsBank[currentQuestionIndex].isAnswerTrue();
        int messageId;

        if (answerIsCorrect == userChoseCorrect){
            messageId = R.string.correct_answer;
        }else {
            messageId = R.string.wrong_answer;
        }

        Snackbar.make(binding.imageView, messageId, Snackbar.LENGTH_LONG).show();
    }
}