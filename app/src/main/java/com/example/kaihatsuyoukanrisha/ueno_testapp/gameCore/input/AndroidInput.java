package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.input;

import android.graphics.PointF;
import android.view.MotionEvent;

public class AndroidInput {
    static private AndroidInput input = null;
    static private int[] idIndex = {-1, -1, -1};
    static private int oldAction = 0;
    static private int action = 0;
    static private boolean touchOnce;
    static private long downTime = 0;
    static private PointF[] touchPos = {
            new PointF(0.f,0.f),
            new PointF(0.f,0.f),
            new PointF(0.f,0.f)};

    static private PointF[] oldPos = {
            new PointF(0.f, 0.f),
            new PointF(0.f,0.f),
            new PointF(0.f,0.f)};

    static private MotionEvent event;

    private AndroidInput() {

    }

    static public AndroidInput getInput() {
        if (input == null) {
            input = new AndroidInput();
        }

        return input;
    }

    //タッチされた順番に配列に値が格納されていく
    static public void setTouchEvent(MotionEvent event) {
        getInput().event = event;
        input.oldAction = input.action;
        input.action = event.getActionMasked();
        downTime = event.getDownTime();
        int actionIndex = event.getActionIndex();
        int pointerId = event.getPointerId(actionIndex);

        switch (action) {
            case MotionEvent.ACTION_MOVE:
                moveIndex(pointerId);
                break;

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                addIndex(pointerId);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_POINTER_UP:
                releaseIndex(pointerId);
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

    static public PointF getAmountMovement() {
        return new PointF(oldPos[0].x - touchPos[0].x, oldPos[0].y - touchPos[0].y);
    }

    static public PointF getAmountMovement(int index) {
        return new PointF(oldPos[index].x - touchPos[index].x, oldPos[index].y - touchPos[index].y);
    }

    static public PointF getTouchPos() {
        return touchPos[0];
    }

    static public PointF getTouchPos(int index) {
        return touchPos[index];
    }

    static public int getNumTouch() {
        return event.getPointerCount();
    }

    static private void releaseIndex(int pointerId) {
        for (int i = 0; i < idIndex.length; i++) {
            if (idIndex[i] == pointerId) {
                touchPos[i].x = 0.0f;
                touchPos[i].y = 0.0f;
                oldPos[i] = touchPos[i];
                idIndex[i] = -1;
            }
        }
    }

    static private void addIndex(int pointerId) {
        for (int i = 0; i < idIndex.length; i++) {
            if (idIndex[i] == -1) {
                idIndex[i] = pointerId;
                touchPos[i].x = event.getX();
                touchPos[i].y = event.getY();
                oldPos[i] = touchPos[i];
            }
        }
    }

    static private void moveIndex(int pointerId) {
        for (int i = 0; i < idIndex.length; i++) {
            if (idIndex[i] >= 0) {
                int index = event.findPointerIndex(idIndex[i]);
                oldPos[i] = new PointF(touchPos[i].x, touchPos[i].y);
                touchPos[i].x = event.getX(index);
                touchPos[i].y = event.getY(index);
            }
        }
    }
}
