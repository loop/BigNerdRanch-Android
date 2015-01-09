package com.yogeshn.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

/**
 * Created by yogesh on 17/08/2014.
 */
public class ChoiceDialogFragment extends DialogFragment {

    public static final int CHOICE_DATE = 1;
    public static final int CHOICE_TIME = 2;
    public static final String EXTRA_CHOICE = "com.yogeshn.criminalintent.choice" ;

    private int mChoice = 0;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.date_or_time);
        builder.setPositiveButton(R.string.date, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mChoice = CHOICE_DATE;
                sendResult(mChoice);
                sendResult(Activity.RESULT_OK);
            }
        });
        builder.setNegativeButton(R.string.time, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mChoice = CHOICE_TIME;
                sendResult(mChoice);
                sendResult(Activity.RESULT_OK);
            }
        });

        return builder.create();
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent i = new Intent();
        i.putExtra(EXTRA_CHOICE, mChoice);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }
}
