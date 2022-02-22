package com.atilmohamedamine.gltp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText totalInput, manCountInput, womanCountInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.
                    LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setContentView(R.layout.activity_main);

        totalInput = (EditText) findViewById(R.id.totalInput);
        manCountInput = (EditText) findViewById(R.id.manCountInput);
        womanCountInput = (EditText) findViewById(R.id.womanCountInput);
    }

    public void send(View view) {
        if(totalInput.getText().toString().isEmpty() ||
           manCountInput.getText().toString().isEmpty() ||
           womanCountInput.getText().toString().isEmpty()) {
            Toast.makeText(view.getContext(), "يرجى ملئ جميع الفراغات", Toast.LENGTH_SHORT).show();
            return;
        }

        float total = Float.parseFloat(totalInput.getText().toString());
        int manCount = Integer.parseInt(manCountInput.getText().toString());
        int womanCount = Integer.parseInt(womanCountInput.getText().toString());

        if(total == 0f) {
            Toast.makeText(view.getContext(), "لا يمكن أن يكون المجموع صفرا", Toast.LENGTH_SHORT).show();
            return;
        }

        if(manCount == 0 && womanCount == 0) {
            Toast.makeText(view.getContext(), "لا يمكن أن يكون عدد الذكور و الإناث صفرا", Toast.LENGTH_SHORT).show();
            return;
        }

        float manAmount = 0, womanAmount = 0;
        if(manCount == 0) {
            if(womanCount == 1)
                womanAmount = total / 2;
            else
                womanAmount = (total  * 2 / 3) / womanCount;
        } else {
            if (womanCount != 0) {
                womanAmount = (total / (manCount * 2 + womanCount));
                manAmount = womanAmount * 2;
            } else {
                manAmount = total / manCount;
            }
        }


        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("manAmount", manAmount);
        intent.putExtra("womanAmount", womanAmount);
        startActivity(intent);
    }
}