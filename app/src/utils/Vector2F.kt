package hu.elte.madtycoon.utils

import kotlin.jvm.JvmOverloads

class Vector2F @JvmOverloads constructor(val x: Float = 0f, val y: Float = 0f) {
    constructor(v: Vector2I) : this(v.x as Float, v.y as Float) {}

    fun add(right: Vector2F): Vector2F {
        return Vector2F(x + right.x, y + right.y)
    }

    fun min(right: Vector2F): Vector2F {
        return Vector2F(x - right.x, y - right.y)
    }

    fun mul(s: Float): Vector2F {
        return Vector2F(x * s, y * s)
    }

    fun length(): Float {
        return Math.sqrt(x * x + y * y)
    }

    fun normalized(): Vector2F {
        val l = length()
        return Vector2F(x / l, y / l)
    }

    fun distance(target: Vector2F): Float {
        return Math.sqrt(Math.pow(x - target.x, 2) + Math.pow(y - target.y, 2))
    }

    val animDirection: Boolean
        get() = if (x > 0) true else false

    @Override
    override fun equals(obj: Object): Boolean {
        return if (obj is Vector2F) distance(obj as Vector2F) < E else false
    }

    @Override
    override fun toString(): String {
        return String.format("(%f, %f)", x, y)
    }

    companion object {
        var E = 0.0025f
        var UP = Vector2F(0, -1)
        var DOWN = Vector2F(0, 1)
        var LEFT = Vector2F(1, 0)
        var RIGHT = Vector2F(-1, 0)
        var ZERO = Vector2F(0, 0)
        var ONE = Vector2F(1, 1)
    }
}