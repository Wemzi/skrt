package src.animation;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class AnimatedSprite
{
    public final static String IDLE = "idle";
    public final static String WALK = "walk";

    private final HashMap<String, BufferedImage[]> sprites;
    private final float frameLength;
    private String state;
    private float time;
    private int animFrame;
    private boolean rotation;

    public AnimatedSprite(String startState, BufferedImage[] startAnimation, float frameLength)
    {
        this.sprites = new HashMap<String, BufferedImage[]>();
        this.sprites.put(startState, startAnimation);
        this.state = startState;
        this.frameLength = frameLength;
        this.time = 0;
        this.animFrame = 0;
    }

    public void addState(String state, BufferedImage[] animation)
    {
        sprites.put(state, animation);
    }

    public void setRotation(boolean lookToRight)
    {
        rotation = lookToRight;
    }

    public boolean getRotation(){ return rotation; }

    public void setState(String state)
    {
        if(!sprites.containsKey(state))
            throw new IllegalArgumentException("This animated sprite doesn't have this state");

        this.state = state;
    }

    public void update(float dt)
    {
        time+=dt;
        if(time > frameLength)
        {
            animFrame++;
            time = time - frameLength;
        }
    }

    public BufferedImage getSprite()
    {
        BufferedImage[] images = sprites.get(state);
        return applyRotation(images[animFrame % images.length], rotation);
    }s
}
