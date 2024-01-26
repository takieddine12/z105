package com.zoom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private TextView textView;
    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility","SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        textView = findViewById(R.id.textV);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://twitter.com/");
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float x = event.getX();
                float y = event.getY();
                switch (action) {
                    case MotionEvent.ACTION_DOWN: {
                        long downTime = SystemClock.uptimeMillis();
                        long eventTime = SystemClock.uptimeMillis();
                        MotionEvent downEvent = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, x, y, 0);
                        textView.dispatchTouchEvent(downEvent);
                        textView.setText("X : " + event.getX() + " Y : " + event.getY());
                    }
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP: {
                        // Create and dispatch a MotionEvent for releasing ('up' event)
                        long downTime = SystemClock.uptimeMillis();
                        long eventTime = SystemClock.uptimeMillis();
                        MotionEvent upEvent = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, x, y, 0);
                        textView.dispatchTouchEvent(upEvent);
                        textView.setText("X : " + event.getX() + " Y : " + event.getY());
                    }
                    break;
                }
                return true;
            }
        });
    }
}