package components.debug;

import components.Component;
import game.KeyInputListener;
import game.Window;

public class DebugKeyTrigger extends Component {
    
    private int key;



    public DebugKeyTrigger(int keyEvent) {
        this.key = keyEvent;
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
        Window.getCurrentScene().laod("physics");
    }

}
