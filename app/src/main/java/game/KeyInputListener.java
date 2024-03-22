package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class KeyInputListener implements KeyListener {

    private static KeyInputListener instance;
	private final int buttons = 550;
	private int[] buttonsHoldTime = new int[buttons];
	private boolean[] buttonsState = new boolean[buttons];


    public KeyInputListener() {

    }

    public static KeyInputListener get() {
        if(KeyInputListener.instance == null) {
            KeyInputListener.instance = new KeyInputListener();
        }
        return KeyInputListener.instance;
    }

	public static boolean isKeyHold(int button) {
		if (button < get().buttonsState.length) {
			return get().buttonsState[button];
		} else {
			return false;
		}
	}

	public static boolean isKeyPressed(int button) {
		if (isKeyHold(button)) {
			if(get().buttonsHoldTime[button] == -1) {
				get().buttonsHoldTime[button] = 0;
			}
			get().buttonsHoldTime[button]++;
			if (get().buttonsHoldTime[button] == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean isKeyReleased(int button) {
		if (button < get().buttonsState.length) {
			if(get().buttonsHoldTime[button] == -1) {
				get().buttonsHoldTime[button] = 0;
				return true;
			} else
				return false;
		} else
			return false;
	}


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
		get().buttonsState[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
		get().buttonsState[e.getKeyCode()] = false;
		get().buttonsHoldTime[e.getKeyCode()] = -1;
    }

    public void update() {
        
    }

	public static void endFrame() {
        Arrays.fill(get().buttonsState, false);
		Arrays.fill(get().buttonsHoldTime, 0);
    }
}
