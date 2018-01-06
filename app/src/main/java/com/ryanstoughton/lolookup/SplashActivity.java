package com.ryanstoughton.lolookup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import static android.os.SystemClock.sleep;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sleep(1000);
        Intent intent = new Intent(this, LookupActivity.class);
        startActivity(intent);
        finish();
    }
}