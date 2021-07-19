package src.animation;


import src.Main;
import src.core.Engine;
import src.utils.MinHeap;
import src.utils.Vector2F;
import src.utils.Vector2I;

import java.awt.*;

public class SpriteRenderBuffer extends MinHeap<LayeredSprite>
{
    public SpriteRenderBuffer(int capacity) {
        super(capacity);
    }

    public void draw(Graphics g)
    {
        while(!isEmpty())
        {
            LayeredSprite next = rem();
            Vector2I pixelPos = VectorToScreenPosition(next.pos);
            g.drawImage(next.sprite, pixelPos.x, pixelPos.y, null);
        }
    }

    private Vector2I VectorToScreenPosition(Vector2F v)
    {
        int baseX = (int) Math.floor(v.x);
        int baseY = (int) Math.floor(v.y);
        int inX = (int) ((v.x - baseX) * Engine.BLOCK_SIZE);
        int inY = (int) ((v.y - baseY) * Engine.BLOCK_SIZE);
        return new Vector2I(baseX * Engine.BLOCK_SIZE + inX, baseY * Engine.BLOCK_SIZE + inY);
    }
}
