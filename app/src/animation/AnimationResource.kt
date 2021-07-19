package src.animation

import javax.imageio.ImageIO

class AnimationResource private constructor() {
    private val resources: HashMap<String, Array<BufferedImage>>
    private var loadedCount = 0
    private var animsCount = 0
    val isLoaded: Boolean
        get() = loadedCount > 0

    @Throws(RuntimeException::class)
    fun load() {
        val root = File("res/game")
        if (!root.isDirectory()) throw RuntimeException("Cant read game files.")
        for (`object` in root.listFiles()) {
            if (!`object`.isDirectory()) throw IllegalStateException(
                String.format(
                    "Cant read object files (%s)",
                    `object`.getName()
                )
            )
            loadObject(`object`)
        }
        System.out.println(
            String.format(
                "[Resources] %o sprite loaded into %o animation",
                loadedCount,
                animsCount
            )
        )
    }

    fun get(path: String?): Array<BufferedImage> {
        return if (resources.containsKey(path)) resources.get(path) else throw IllegalStateException(
            "This animation doesn't exits"
        )
    }

    private fun loadObject(`object`: File) {
        for (anim in `object`.listFiles()) {
            if (!anim.isDirectory()) throw IllegalStateException(
                String.format(
                    "Cant read animation files (%s)",
                    anim.getName()
                )
            )
            loadAnim(anim, `object`)
            animsCount++
        }
    }

    private fun loadAnim(anim: File, `object`: File) {
        val frameFiles: Array<File> = anim.listFiles()
        val frames: Array<BufferedImage?> = arrayOfNulls<BufferedImage>(frameFiles.size)
        var i = 0
        for (frame in frameFiles) {
            if (!frame.isFile()) throw IllegalStateException(
                String.format(
                    "Cant open anim frame (%s)",
                    frame.getName()
                )
            )
            try {
                frames[i++] = ImageIO.read(frame)
                loadedCount++
            } catch (exception: IOException) {
                throw IllegalStateException(
                    String.format(
                        "Anim frame is corrupted (%s)",
                        frame.getName()
                    )
                )
            }
        }
        resources.put(String.format("%s_%s", `object`.getName(), anim.getName()), frames)
    }

    companion object {
        var Instance = AnimationResource()
    }

    init {
        resources = HashMap<String, Array<BufferedImage>>()
    }
}