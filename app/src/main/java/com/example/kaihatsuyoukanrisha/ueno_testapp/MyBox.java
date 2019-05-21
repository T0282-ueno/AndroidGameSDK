package com.example.kaihatsuyoukanrisha.ueno_testapp;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class MyBox {
    private FloatBuffer vertexBuff;

    public MyBox() {
        float vertices[] = {
                // 前
                -0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                -0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,

                // 後
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                -0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,

                // 左
                -0.5f, -0.5f, 0.5f,
                -0.5f, -0.5f, -0.5f,
                -0.5f, 0.5f, 0.5f,
                -0.5f, 0.5f, -0.5f,

                // 右
                0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, -0.5f,

                // 上
                -0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                -0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,

                // 底
                -0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f
        };

        ByteBuffer buff = ByteBuffer.allocate(vertices.length * 4);
        buff.order(ByteOrder.nativeOrder());
        vertexBuff = buff.asFloatBuffer();
        vertexBuff.put(vertices);
        vertexBuff.position(0);
    }

    public void draw(GL10 gl){
        /*gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuff);

        // Front
        gl.glNormal3f(0, 0, 1.0f);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        // Back
        gl.glNormal3f(0, 0, -1.0f);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);

        // Left
        gl.glNormal3f(-1.0f, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);

        // Right
        gl.glNormal3f(1.0f, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);

        // Top
        gl.glNormal3f(0, 1.0f, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);

        // Right
        gl.glNormal3f(0, -1.0f, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);*/

    }
}