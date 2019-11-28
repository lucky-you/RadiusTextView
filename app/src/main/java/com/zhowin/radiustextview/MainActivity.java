package com.zhowin.radiustextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhowin.library.radius.RadiusTextView;


public class MainActivity extends AppCompatActivity {
    RadiusTextView radiusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radiusTextView = findViewById(R.id.radiusTextView);
    }
}
