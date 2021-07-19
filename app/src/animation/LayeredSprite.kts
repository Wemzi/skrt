package src.animation

import src.utils.Vector2F
import java.awt.image.BufferedImage

class LayeredSprite(val sprite: BufferedImage, val layer: Int, pos: Vector2F) : Comparable<src.animation.LayeredSprite>
{
    init {
        this.pos = pos
    }

    val pos: Vector2F

    override fun compareTo(`object`: src.animation.LayeredSprite): Int {
        return if (layer == `object`.layer) (Math.floor(pos.y * 10) - Math.floor(`object`.pos.y * 10)).toInt() else layer - `object`.layer
    }
}