package com.katrina.colorpickerdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by jm7463pn on 9/27/16.
 */

//ColorPickerDialog.java

public class ColorPickerDialogFragment extends DialogFragment {

    //This data is the text to be displayed in the dialog's list
    final CharSequence[] COLOR_CHOICES = {"Red", "Green", "Blue"};

    //Parallel array with the color values, as an int Value constnats
    final int[] COLOR_VALUES = {Color.RED, Color.GREEN, Color.BLUE};

    //Interface for the listener
    interface ColorDialogSelectionListener {
        void colorSelected(int color);
    }

    private ColorDialogSelectionListener mListener;

    //New instance - no arguments, but good practice to use anyway
    public static ColorPickerDialogFragment newInstance() {
        ColorPickerDialogFragment fragment = new ColorPickerDialogFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ColorDialogSelectionListener) {
            mListener = (ColorDialogSelectionListener) activity;
        } else {
            throw new RuntimeException(activity.toString() + " must implement ColorDialogSelectionListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose a background color")
                .setItems(COLOR_CHOICES, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //which is the index of the item selected from the list
                        //the parallel array is used to return a color value
                        //return the data from the index of this array
                        mListener.colorSelected(COLOR_VALUES[which]);
                    }
                });
        return builder.create();
    }


}
