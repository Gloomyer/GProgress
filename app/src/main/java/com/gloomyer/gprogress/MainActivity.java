package com.gloomyer.gprogress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GProgress mGProgress;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGProgress = (GProgress) findViewById(R.id.gProgress);
    }

    public void getProgress(View v) {
        Toast.makeText(this, "progress:" + mGProgress.getProgress()
                + ",totalProgress:" + mGProgress.getTotal(), Toast.LENGTH_SHORT).show();
    }

    public void subtraction(View v) {
        progress--;
        mGProgress.setProgress(progress);
    }

    public void add(View v) {
        progress++;
        mGProgress.setProgress(progress);
    }
}
