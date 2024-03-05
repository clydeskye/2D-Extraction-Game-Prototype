package observers;

import game.GameObject;
import observers.events.Event;

public interface Observer {
    
    void OnNotify(GameObject go, Event event);

}
