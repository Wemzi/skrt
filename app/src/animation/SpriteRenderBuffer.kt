package src.animation

import src.Main
import src.core.Engine
import src.utils.MinHeap
import src.utils.Vector2F
import src.utils.Vector2I

class SpriteRenderBuffer(capacity: Int) : MinHeap<LayeredSprite?>(capacity) {
    fun draw(g: Graphics) {
        while (!isEmpty()) {
            val next: LayeredSprite = rem()
            val pixelPos: Vector2I = VectorToScreenPosition(next.pos)
            g.drawImage(next.sprite, pixelPos.x, pixelPos.y, null)
        }
    }

    private fun VectorToScreenPosition(v: Vector2F): Vector2I {
        val baseX = Math.floor(v.x) as Int
        val baseY = Math.floor(v.y) as Int
        val inX = ((v.x - baseX) * Engine.BLOCK_SIZE) as Int
        val inY = ((v.y - baseY) * Engine.BLOCK_SIZE) as Int
        return Vector2I(baseX * Engine.BLOCK_SIZE + inX, baseY * Engine.BLOCK_SIZE + inY)
    }
}