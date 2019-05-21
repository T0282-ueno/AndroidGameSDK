package com.example.kaihatsuyoukanrisha.ueno_testapp;

import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class SampleModel {
    private float rot = 0.0f;
    private FloatBuffer buffer;         // 頂点バッファ
    private FloatBuffer normalBuffer;   // 法線用バッファ
    private float vertex[] = {
            -1.0f, -1.0f, 0.0f,
            1.0f, -1.0f, 0.0f,
            0.0f,  1.0f, 0.0f,
    };
    private float normal[] = {
            1.0f, 0.0f, 1.0f,
            -1.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 1.0f,
    };

    public SampleModel() {
        //頂点登録
        ByteBuffer vb = ByteBuffer.allocateDirect(vertex.length * 4);   //4はfloatの大きさ
        vb.order(ByteOrder.nativeOrder());
        buffer = vb.asFloatBuffer();
        buffer.put(vertex);
        buffer.position(0);

        //法線登録
        ByteBuffer nb = ByteBuffer.allocateDirect(normal.length * 4);
        nb.order(ByteOrder.nativeOrder());
        normalBuffer = nb.asFloatBuffer();
        normalBuffer.put(normal);
        normalBuffer.position(0);
    }

    public void draw(GL10 gl) {
        //初期行列保存
        gl.glPushMatrix();

        float[] worldMtx = new float[16];
        float[] transMtx = new float[16];
        float[] rotateMtx = new float[16];
        float[] scaleMtx = new float[16];

        Matrix.setIdentityM(transMtx, 0);
        Matrix.setIdentityM(rotateMtx, 0);
        Matrix.setIdentityM(scaleMtx, 0);
        Matrix.setIdentityM(worldMtx, 0);
        Matrix.translateM(transMtx, 0, 0.0f, 0.0f, -3.0f);
        Matrix.rotateM(rotateMtx, 0, rot, 0, 1, 0);
        Matrix.scaleM(scaleMtx, 0, 1.0f, 1.0f, 1.0f);
        Matrix.multiplyMM(worldMtx, 0, transMtx, 0, rotateMtx, 0);
        Matrix.multiplyMM(worldMtx, 0, worldMtx, 0, scaleMtx, 0);

        gl.glLoadMatrixf(worldMtx, 0);

        /*gl.glTranslatef(0.0f, 0.0f, -3.0f);
        gl.glRotatef(rot, 0, 1, 0);*/
        rot += 1.0f;

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, buffer);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);

        //float[] matrix = new float[16];
        //gl.glLoadMatrixf(matrix, GL_MODELVIEW_MATRIX);

        //初期行列吐き出し
        gl.glPopMatrix();
    }
}
