package com.yogeshn.geoquiz;

/**
 * Created by yogesh on 12/08/2014.
 */
public class TrueFalse {

    private int mQuestion;
    private boolean mTrueQuestion;
    private boolean mDidCheat;

    public TrueFalse (int question, boolean trueQuestion, boolean didCheat) {
        this.mQuestion = question;
        this.mTrueQuestion = trueQuestion;
        this.mDidCheat = didCheat;
    }

    public int getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean ismTrueQuestion() {
        return mTrueQuestion;
    }

    public void setmTrueQuestion(boolean mTrueQuestion) {
        this.mTrueQuestion = mTrueQuestion;
    }

    public boolean ismDidCheat() {
        return mDidCheat;
    }

    public void setmDidCheat(boolean mDidCheat) {
        this.mDidCheat = mDidCheat;
    }
}
