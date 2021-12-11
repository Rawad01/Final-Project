package com.lau.finalprojectmedical_report;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findViewById(R.id.signout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Preferences.getInstance(getApplicationContext()).logout();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Preferences.getInstance(this).isLoggedIn()) {
            finish();
            Preferences.getInstance(getApplicationContext()).logout();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}