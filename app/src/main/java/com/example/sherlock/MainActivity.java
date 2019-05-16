package com.example.sherlock;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final private int CHOOSE_THIEF = 0;

    private Button rotate;
    TextView textOrientation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        rotate = findViewById(R.id.rotate);
        textOrientation = findViewById(R.id.textOrientation);
    }

    public void choose(View view) {
        Intent questionIntent = new Intent(MainActivity.this, SecondActivity.class);
        startActivityForResult(questionIntent, CHOOSE_THIEF);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView textView = findViewById(R.id.textViewInfo);

        if (requestCode == CHOOSE_THIEF) {
            if (resultCode == RESULT_OK) {
                textView.setText(data.getStringExtra("ru.alexanderklimov.sherlock.THIEF"));
            } else {
                textView.setText(""); // стираем текст
            }
        }
    }

    public void showOrientation(View view) {
        String orientation = getScreenOrientation();
        String rotation = getRotationOrientation();

        textOrientation.setText(orientation + "   " + rotation);
    }


    private String getScreenOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return "Портретная ориентация";
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            return "Альбомная ориентация";
        else
            return "";
    }

    private String getRotationOrientation() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_0:
                return "Не поворачивали";
            case Surface.ROTATION_90:
                return "на 90 по часовой стрелке";
            case Surface.ROTATION_180:
                return "на 180 гардусов";
            case Surface.ROTATION_270:
                return "на 90 против часовой";
            default:
                return "не понятно";
        }
    }

    /**
     * вызывается после смены ориентации экрана
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Проверяем ориентацию экрана
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            rotate.setText("Альбомная");
            // Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            rotate.setText("портретная");
            // Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Устанавливаем ориентацию экрана и выводим соответствующую надпись на кнопке
     *
     * @param view
     */
    public void rotateScreen(View view) {
        // если текущая ориентация портретная, по делаем альбомную и наоборот
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            rotate.setText("Альбомная");
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            rotate.setText("портретная");
        }
    }

    public void about(View view) {
        Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(aboutIntent);

    }
}
