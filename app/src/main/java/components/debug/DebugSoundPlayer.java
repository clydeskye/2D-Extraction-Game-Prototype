package components.debug;

import components.Component;
import game.KeyInputListener;
import game.Sound;

public class DebugSoundPlayer extends Component {
    
    private Sound sound;
    private int keyBind;

    public DebugSoundPlayer() {

    }

    public DebugSoundPlayer(Sound sound, int keyBind) {
        this.sound = sound;
        this.keyBind = keyBind;
    }
   
    @Override
    public void update(float dt) {
        if (KeyInputListener.isKeyPressed(keyBind)) {
            if (!sound.isPlaying()) {
                sound.play();
            } else {
                sound.stop();
            }
        }
    }

    public void setParameters(Sound sound, int keyBind) {
        this.sound = sound;
        this.keyBind = keyBind;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }
}
