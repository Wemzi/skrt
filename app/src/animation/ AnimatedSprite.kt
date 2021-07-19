package src.animation

import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage
import java.util.HashMap

class AnimatedSprite(
    startState: String,
    startAnimation: Array<BufferedImage>,
    frameLength: Float
) {
    private val sprites: HashMap<String, Array<BufferedImage>>
    private val frameLength: Float
    private var state: String
    private var time: Float
    private var animFrame: Int

    var rotation = false
    fun addState(state: String?, animation: Array<BufferedImage>) {
        sprites.put(state, animation)
    }

    fun setState(state: String) {
        if (!sprites.containsKey(state)) throw IllegalArgumentException("This animated sprite doesn't have this state")
        this.state = state
    }

    fun update(dt: Float) {
        time += dt
        if (time > frameLength) {
            animFrame++
            time = time - frameLength
        }
    }

    val sprite: BufferedImage
        get() {
            val images: Array<BufferedImage> = sprites.get(state)
            return applyRotation(images[animFrame % images.size], rotation)
        }

    companion object {
        const val IDLE = "idle"
        const val WALK = "walk"
    }

    init {
        sprites = HashMap<String, Array<BufferedImage>>()
        sprites.put(startState, startAnimation)
        state = startState
        this.frameLength = frameLength
        time = 0f
        animFrame = 0
    }
}