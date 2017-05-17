package com.iesvdc.lolo.deruta.component;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.iesvdc.lolo.deruta.R;

public class Dialog {
    Activity m_activity;
    AlertDialog m_dialog;

    public Dialog(Activity activity){
        this.m_activity = activity;
    }

    private void styleDivider(){
        int dividerId = m_activity.getResources().getIdentifier("titleDivider", "id", "android");
        View titleDivider = m_dialog.findViewById(dividerId);
        if(titleDivider != null){
            titleDivider.setBackgroundColor(m_activity.getResources().getColor(R.color.primary_dark));
        }
    }

    private void styleNegativeButton(){
        Button b = m_dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if(b != null){
            b.setTextColor(m_activity.getResources().getColor(R.color.primary));
        }
    }

    private void stylePositiveButton(){
        Button b = m_dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if(b != null){
            b.setTextColor(m_activity.getResources().getColor(R.color.primary_text));
            b.setBackgroundColor(m_activity.getResources().getColor(R.color.primary));
        }
    }

    private void styleNeutralButton(){
        Button b = m_dialog.getButton(DialogInterface.BUTTON_NEUTRAL);
        if(b != null){
            b.setTextColor(m_activity.getResources().getColor(R.color.primary));
        }
    }

    public void show(
            String title,
            String text,
            String positive,
            DialogInterface.OnClickListener positive_listener,
            String negative,
            DialogInterface.OnClickListener negative_listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(m_activity);
        builder.setTitle(title);
        builder.setMessage(text);
        builder.setPositiveButton(positive, positive_listener);
        builder.setNegativeButton(negative, negative_listener);
        builder.setCancelable(false);

        m_dialog = builder.show();

        styleDivider();
        stylePositiveButton();
        styleNegativeButton();
    }

    public void show(
            String title,
            String text,
            String button,
            DialogInterface.OnClickListener button_listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(m_activity);
        builder.setTitle(title);
        builder.setMessage(text);
        builder.setNeutralButton(button, button_listener);
        builder.setCancelable(false);

        styleDivider();
        styleNeutralButton();
    }

    AlertDialog getDialog(){
        return this.m_dialog;
    }
}
