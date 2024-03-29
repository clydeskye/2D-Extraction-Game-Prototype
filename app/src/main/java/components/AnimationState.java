package components;

import java.util.ArrayList;
import java.util.List;

import utils.AssetPool;

public class AnimationState {
    public String title;

    public List<Frame> animationFrames = new ArrayList<>();

    private static Sprite defaultSprite = new Sprite();
    private transient float timeTracker = 0.0f;
    private transient int currentSprite = 0;
    public boolean doesLoop = false;

    public AnimationState() {
        
    }

    public void addFrame(Sprite sprite, float frameTime) {
        animationFrames.add(new Frame(sprite, frameTime));
    }

    public void addFrameList(List<Sprite> sprites, float frameTime) {
        for (Sprite sprite : sprites) {
            addFrame(sprite, frameTime);
        }
    }

    public void setFrames(List<Sprite> sprites, float frameTime) {
        if (!animationFrames.isEmpty()) {
            animationFrames.clear();
        }
        for (Sprite sprite : sprites) {
            addFrame(sprite, frameTime);
        }
    }

    public void setLoop(boolean doesLoop) {
        this.doesLoop = doesLoop;
    }

    public void update(float dt) {
        if(currentSprite < animationFrames.size()) {
            timeTracker -= dt;
            if (timeTracker <= 0) {
                if (currentSprite != animationFrames.size() - 1 || doesLoop) {
                    currentSprite = (currentSprite + 1) % animationFrames.size();
                }
                timeTracker = animationFrames.get(currentSprite).frameTime;
            }
        }
    }

    public Sprite getCurrentSprite() {
        if (currentSprite < animationFrames.size()) {
            return animationFrames.get(currentSprite).sprite;
        }
        
        return defaultSprite;
    }

    public void refreshTextures() {
        for (Frame frame : animationFrames) {
            frame.sprite.setTexture(AssetPool.getTexture(frame.sprite.getTexture().getFilepath()));
        }
    }
}
