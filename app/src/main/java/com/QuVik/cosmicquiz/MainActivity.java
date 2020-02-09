package com.QuVik.cosmicquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_sun, false),
            new Question(R.string.question_andromeda, false),
            new Question(R.string.question_milky_way, true),
            new Question(R.string.question_planets, false),
            new Question(R.string.question_moon, false),
            new Question(R.string.question_giant_planets, true),
            new Question(R.string.question_jupiter, false),
            new Question(R.string.question_mercury, false),
            new Question(R.string.question_atmosphere, true),
            new Question(R.string.question_neptune, true),
    };

    private int mCurrentIndex = 0;

    private int toastColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toastColor = getResources().getColor(R.color.colorPrimary);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick (View v){
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
                        mNextButton.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick (View v){
                                mCurrentIndex = (mCurrentIndex + 1)% mQuestionBank.length;
                                updateQuestion();
                                updatePreviousButtonState();
                            }
                        });

        mPreviousButton = (Button) findViewById(R.id.previous_button);
                        mPreviousButton.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick (View v){
                                if (mCurrentIndex == 0 || mCurrentIndex >= mQuestionBank.length) {
                                    return;
                                }
                                mCurrentIndex -= 1;
                                updateQuestion();
                                updatePreviousButtonState();
            }
        });

        updateQuestion();
        updatePreviousButtonState();
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void updatePreviousButtonState() {
        if (mCurrentIndex == 0) {
            mPreviousButton.setEnabled(false);
        } else if (!mPreviousButton.isEnabled()) {
            mPreviousButton.setEnabled(true);
        }
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if(userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;

        }else{
            messageResId = R.string.incorrect_toast;
        }
        Toast toastMessage = Toast.makeText(this, messageResId,Toast.LENGTH_SHORT);

        View messageView = toastMessage.getView();
        // setBackgroundResource()
        messageView.setBackgroundColor(toastColor);

        toastMessage.show();
    }

}
