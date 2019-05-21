package com.example.kaihatsuyoukanrisha.ueno_testapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder surfaceHolder;
    private Thread thread;
    private Bitmap bitmapImage;

    public TestSurfaceView(Context context) {
        super(context);
        this.surfaceHolder = this.getHolder();
        initSurfaceHolder();
    }

    public TestSurfaceView(Context contest, SurfaceView sv){
        super(contest);
        this.surfaceHolder = sv.getHolder();
        initSurfaceHolder();
    }

    private void initSurfaceHolder(){
        this.surfaceHolder.addCallback(this);
        Resources res = this.getContext().getResources();
        bitmapImage = BitmapFactory.decodeResource(res, R.drawable.fantasy_flatwoods_monster);
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread = null;
    }

    @Override
    public void run() {
        Canvas c = this.surfaceHolder.lockCanvas();
        c.drawColor(Color.CYAN);
        c.drawBitmap(this.bitmapImage, 0,500,null);
        this.surfaceHolder.unlockCanvasAndPost(c);
    }
}
