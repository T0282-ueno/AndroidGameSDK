package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.opengl;

import android.content.Context;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.core.GameHandler;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.Camera;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform.Rect;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRenderer extends GLSurfaceView implements GLSurfaceView.Renderer {
    private final float[] CLEAR_COLOR = {0.5f, 0.5f, 1.0f, 1.0f};
    private final float ALPHA_FUNC = 0.1f;

    private GameHandler handler;
    private float lightpos[] = {0.0f, 0.0f, 4.0f, 0.0f};
    private Point displaySize;
    private Context context;

    public GLRenderer(final Context context, GameHandler handler) {
        super(context);
        this.context = context;
        setRenderer(this);
        this.handler = handler;
    }

    public void uninit() {
        handler = null;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //クリア色
        gl.glClearColor(CLEAR_COLOR[0], CLEAR_COLOR[1], CLEAR_COLOR[2], CLEAR_COLOR[3]);

        // デプステスト
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glDepthMask(true);

        //裏ポリゴンを描画しない
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);

        // ライティングをON
        gl.glEnable(GL10.GL_LIGHTING);
        // 光源を有効にして位置を設定
        gl.glEnable(GL10.GL_LIGHT0);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightpos, 0);

        // スムースシェーディング
        gl.glShadeModel(GL10.GL_SMOOTH);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        displaySize = new Point(width, height);

        handler.setGL10(gl);
        handler.init();

        // アルファテスト
        gl.glEnable(GL10.GL_ALPHA_TEST);
        gl.glAlphaFunc(GL10.GL_GEQUAL, ALPHA_FUNC);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        handler.draw(gl);
    }

    public void beginRendering(GL10 gl, Camera c) {
        //ビューポート
        Rect rect = c.getViewport();
        gl.glViewport(rect.left, rect.top, rect.right, rect.bottom);

        //バッファクリア
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glClearDepthf(1.0f);

        //プロジェクション
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadMatrixf(c.getProjectionMatrix(), 0);

        //カメラ
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadMatrixf(c.getViewMatrix(), 0);
    }

    public void endRendering() {

    }

    public final Point getDisplaySize() {
        return displaySize;
    }
}
