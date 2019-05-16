package com.example.sherlock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {

    public final static String THIEF = "ru.alexanderklimov.sherlock.THIEF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latout2);
    }

    public void onRadioClick(View v) {
        Intent answerIntent = new Intent();

        switch (v.getId()) {
            case R.id.radioDog:
                answerIntent.putExtra(THIEF, "Сраный песик");
                break;
            case R.id.radioCat:
                answerIntent.putExtra(THIEF, "Ворона");
                break;
            case R.id.radioCrow:
                answerIntent.putExtra(THIEF, "Котик");
                break;
            default:
                break;
        }

        setResult(RESULT_OK, answerIntent);
        finish();
    }
}
