package components.debug;

import components.Component;
import game.KeyInputListener;
import game.Window;

public class DebugKeyTrigger extends Component {
    
    private int key;
    private String filename;

    public DebugKeyTrigger(int keyEvent, String filename) {
        this.key = keyEvent;
        this.filename = filename;
    }

    @Override
    public void update(float dt) {
        if (KeyInputListener.isKeyPressed(key)) {
            trigger();
        }
    }

    public void trigger() {
        System.out.println("Resetting");
        Window.getCurrentScene().destroy();
        Window.getCurrentScene().laod(filename);
    }

}
