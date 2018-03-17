package com.londonappbrewery.quizzler;

/**
 * Created by abinash on 16/3/18.
 */

public class TrueFalse {

    private int mQuestionID;
    private boolean answer;

    public TrueFalse(int questionID, boolean answer) {
        mQuestionID = questionID;
        this.answer = answer;
    }

    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
