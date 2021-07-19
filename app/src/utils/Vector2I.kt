package src.utils

import kotlin.jvm.JvmOverloads

class Vector2I @JvmOverloads constructor(val x: Int = 0, val y: Int = 0) {
    constructor(v: Vector2F) : this(v.x as Int, v.y as Int) {}

    fun add(right: Vector2I): Vector2I {
        return Vector2I(x + right.x, y + right.y)
    }

    fun mul(s: Int): Vector2I {
        return Vector2I(x * s, y * s)
    }

    operator fun div(s: Int): Vector2I {
        return Vector2I(x / s, y / s)
    }

    @Override
    override fun equals(obj: Object): Boolean {
        if (obj is Vector2I) {
            val o = obj as Vector2I
            return x == o.x && y == o.y
        }
        return false
    }

    @Override
    override fun toString(): String {
        return String.format("(%o, %o)", x, y)
    }

    companion object {
        var UP = Vector2I(0, -1)
        var DOWN = Vector2I(0, 1)
        var LEFT = Vector2I(1, 0)
        var RIGHT = Vector2I(-1, 0)
        var ZERO = Vector2I(0, 0)
        var ONE = Vector2I(1, 1)
    }
}