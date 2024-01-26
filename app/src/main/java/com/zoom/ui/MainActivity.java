package com.zoom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private TextView textView;
    private long timeElapsed = 0;

    private int click = 0;


    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility","SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        textView = findViewById(R.id.textV);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(webViewClient);
        webView.loadUrl("https://twitter.com/");
//        webView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getAction();
//                float x = event.getX();
//                float y = event.getY();
//                switch (action) {
//                    case MotionEvent.ACTION_DOWN: {
//                        long downTime = SystemClock.uptimeMillis();
//                        long eventTime = SystemClock.uptimeMillis();
//                        MotionEvent downEvent = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, x, y, 0);
//                        textView.dispatchTouchEvent(downEvent);
//                        textView.setText("X : " + event.getX() + " Y : " + event.getY());
//                    }
//                    case MotionEvent.ACTION_MOVE:
//                    case MotionEvent.ACTION_UP: {
//                        // Create and dispatch a MotionEvent for releasing ('up' event)
//                        long downTime = SystemClock.uptimeMillis();
//                        long eventTime = SystemClock.uptimeMillis();
//                        MotionEvent upEvent = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, x, y, 0);
//                        textView.dispatchTouchEvent(upEvent);
//                        textView.setText("X : " + event.getX() + " Y : " + event.getY());
//                    }
//                    break;
//                }
//                return true;
//            }
//        });





    }

    private void autoClickView(){
        click = click + 1;
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis() + 100;
        float x = 0.0f;
        float y = 0.0f;
        int metaState = 0;
        MotionEvent motionEvent = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 1171 , 1550, metaState);
        textView.dispatchTouchEvent(motionEvent);
        Toast.makeText(MainActivity.this,"Clicked : " + click +
                " X : " + motionEvent.getX() + " Y " + motionEvent.getY(),Toast.LENGTH_LONG).show();
    }
    WebViewClient webViewClient = new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(timeElapsed < 5000){
                        timeElapsed = timeElapsed + 1000;
                        autoClickView();
                        Log.d("TAG","Clicked " + click);
                        handler.postDelayed(this,timeElapsed);
                    }
                }
            },1000);
        }
    };
}