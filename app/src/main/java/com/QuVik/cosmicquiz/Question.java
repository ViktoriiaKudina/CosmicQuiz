package com.QuVik.cosmicquiz;

public class Question {
    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    private int mTextResId;

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    private boolean mAnswerTrue;

    public Question (int textResId, boolean answerTrue){
       mTextResId = textResId;
       mAnswerTrue = answerTrue;
    }
}
