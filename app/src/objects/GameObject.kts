package src.objects

//import src.core.World
import src.AnimatedSprite
import src.render.LayeredSprite
import src.render.SpriteRenderBuffer
import src.utils.Vector2F

abstract class GameObject(world: World, sprite: AnimatedSprite, position: Vector2F) {
    protected var sprite: AnimatedSprite
    protected var position: Vector2F
    //protected var world: World
    private var startFrame: Boolean
    var active: Boolean

    fun update(dt: Float) {
        if (!startFrame) {
            start()
            startFrame = true
        }
        sprite.update(dt)
    }

    fun render(buffer: SpriteRenderBuffer) {
        if (startFrame) buffer.add(LayeredSprite(sprite.getSprite(), renderLayer, renderPosition))
    }

    fun destroy() {
        world.destroy(this)
    }

    fun getPosition(): Vector2F {
        return position
    }

    val renderPosition: Vector2F
        get() = getPosition()

    fun getSprite(): AnimatedSprite {
        return sprite
    }

    val targetPosition: Vector2F
        get() = getPosition()

    fun setPosition(position: Vector2F) {
        this.position = position
    }

    abstract fun onDestroy()
    protected abstract fun start()
    protected abstract val renderLayer: Int

    init {
        //this.world = world
        this.sprite = sprite
        this.position = position
        startFrame = false
        active = true
    }
}