package com.atilmohamedamine.gltp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView manAmountInput, womanAmountInput;

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
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        if (extras == null)
            startActivity(new Intent(ResultActivity.this, MainActivity.class));

        manAmountInput = (TextView) findViewById(R.id.manAmount);
        womanAmountInput = (TextView) findViewById(R.id.womanAmount);

        manAmountInput.setText(String.valueOf(extras.getFloat("manAmount")));
        womanAmountInput.setText(String.valueOf(extras.getFloat("womanAmount")));
    }

    public void back(View view) {
        startActivity(new Intent(ResultActivity.this, MainActivity.class));
    }
}