package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.input;

import android.graphics.PointF;
import android.view.MotionEvent;

public class AndroidInput {
    static private AndroidInput input = null;
    static private int oldAction = 0;
    static private int action = 0;
    static private boolean touchOnce;
    static private long downTime = 0;
    static private PointF touchPos = new PointF(0.f,0.f);
    static private PointF oldPos = new PointF(0.f, 0.f);
    static private MotionEvent event;

    private AndroidInput() {

    }

    static public AndroidInput getInput() {
        if (input == null) {
            input = new AndroidInput();
        }

        return input;
    }

    static public void setTouchEvent(MotionEvent event) {
        getInput().event = event;
        input.oldAction = input.action;
        input.action = event.getAction();
        downTime = event.getDownTime();

        switch (action) {
            case MotionEvent.ACTION_MOVE:
                oldPos = new PointF(touchPos.x, touchPos.y);
                touchPos.x = event.getX();
                touchPos.y = event.getY();
                break;

            case MotionEvent.ACTION_DOWN:
                touchPos.x = event.getX();
                touchPos.y = event.getY();
                oldPos = touchPos;
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                touchPos.x = 0.0f;
                touchPos.y = 0.0f;
                oldPos = touchPos;
                break;
        }
    }

    static public boolean touchDown() {
        return (action == MotionEvent.ACTION_DOWN);
    }

    static public boolean multiDown() {
        return (action == MotionEvent.ACTION_POINTER_DOWN);
    }

    static boolean downTrigger() {
        return touchDown() && (oldAction != MotionEvent.ACTION_DOWN);
    }

    static public boolean touchUp() {
        return (action == MotionEvent.ACTION_UP);
    }

    static public boolean multiUp() {
        return (action == MotionEvent.ACTION_POINTER_UP);
    }

    static public boolean upTrigger() {
        return touchUp() && (oldAction != MotionEvent.ACTION_UP);
    }

    static public boolean hasMove() {
        return (action == MotionEvent.ACTION_MOVE);
    }

    static public boolean hasCancel() {
        return (action == MotionEvent.ACTION_CANCEL);
    }

    static public PointF getAmmountMovement() {
        return new PointF(oldPos.x - touchPos.x, oldPos.y - touchPos.y);
    }

    static public PointF getTouchPos() {
        return touchPos;
    }

    static public PointF getTouchPos(int num) {
        return new PointF(event.getX(num), event.getY(num));
    }

    static public int getNumTouch() {
        return event.getPointerCount();
    }
}
