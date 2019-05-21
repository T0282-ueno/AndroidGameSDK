package com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.transform;

public class Vec3 {
    public float x, y, z;

    public Vec3() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;

    }

    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3 multiplicatio(Vec3 vec) {
        return new Vec3(x * vec.x, y * vec.y, z * vec.z);
    }

    public static Vec3 multiplivation(Vec3 vec1, Vec3 vec2) {
        return new Vec3(vec1.x * vec2.x, vec1.y * vec2.y, vec1.z * vec2.z);
    }
}
