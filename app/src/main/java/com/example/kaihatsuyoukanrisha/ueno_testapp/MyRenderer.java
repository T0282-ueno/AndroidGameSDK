package com.example.kaihatsuyoukanrisha.ueno_testapp;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class MyRenderer implements GLSurfaceView.Renderer {
    float lightpos[] = {0.0f, 0.0f, 4.0f, 0.0f};
    private GL10 gl;
    private MyBox box = new MyBox();
    private Triangle triangle = new Triangle();
    private SampleModel model = new SampleModel();

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        this.gl = gl;

        //クリア色
        gl.glClearColor(0.5f, 0.5f, 1.0f, 1.0f);

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
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        //カメラの設定　画角, アスペクト比, near, far
        //GLU.gluPerspective(gl, 50f, (float)width / height, 0.01f, 100f);

        // 画面比（アスペクト比）を計算
        float ratio = (float) width / height;

        // 透視投影に使う値を計算
        float size = 0.01f / (float) Math.tan(45.0f / 2.0);
        gl.glFrustumf(-size, size, -size / ratio, size / ratio, 0.01f, 100.0f);

        //gl.glViewport(0, 0, width, height);
        //float ratio = (float) width / height;
        //gl.glMatrixMode(GL10.GL_PROJECTION);
        //gl.glLoadIdentity();
        //gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //GLES20.glClearColor(0.5f, 0.5f, 1.0f, 1.0f);
        //GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glClearDepthf(1.0f);
        //gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        //gl.glTranslatef(0.0f, 0.0f, -3.0f);
        //GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        gl.glLoadIdentity();

        //box.draw(gl);
        //triangle.draw();
        model.draw(gl);
    }

    public GL10 getGL() { return gl; }
}
