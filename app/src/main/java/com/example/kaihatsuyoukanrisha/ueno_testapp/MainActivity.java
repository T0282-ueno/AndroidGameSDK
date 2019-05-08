package com.example.kaihatsuyoukanrisha.ueno_testapp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView earth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        earth = findViewById(R.id.testImage);
        //WindowManager windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
        //Display display = windowManager.getDefaultDisplay();
        //Point point = new Point();
        //display.getSize(point);
        //LinearLayout layoutSize = findViewById(R.id.layout);
        //earth.setX(point.x * 0.5f);
        //earth.setY(point.y * 0.5f);
        //earth.setX(layoutSize.getWidth() * 0.5f);
        //earth.setY(layoutSze.getHeight() * 0.5f);

        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //earth.setImageResource(R.drawable.alien_ufo);
        earth.setX(dm.widthPixels * 0.5f);
    }

    public void changeActivity(View view){
        Intent intent = new Intent(this, testActivity.class );
        startActivity(intent);
    }
}
