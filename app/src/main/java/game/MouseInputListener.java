package game;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputListener implements MouseListener, MouseMotionListener {


    private static MouseInputListener instance;

	private final int buttons = 10;
	private int[] buttonsHoldTime = new int[buttons];
	private boolean[] buttonsState = new boolean[buttons];

    public MouseInputListener() {
		
	}

	public static MouseInputListener get() {
        if(MouseInputListener.instance == null) {
            MouseInputListener.instance = new MouseInputListener();
        }
        return MouseInputListener.instance;
    }

	public static boolean isMouseHold(int button) {
		if (button < get().buttonsState.length) {
			return get().buttonsState[button];
		} else {
			return false;
		}
	}

	public static boolean isMousePressed(int button) {
		if (isMouseHold(button)) {
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

	public static boolean isMouseReleased(int button) {
		if (button < get().buttonsState.length) {
			if(get().buttonsHoldTime[button] == -1) {
				get().buttonsHoldTime[button] = 0;
				return true;
			} else
				return false;
		} else
			return false;
	}
	
	public void update() {

	}


	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		get().buttonsState[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		get().buttonsState[e.getButton()] = false;
		get().buttonsHoldTime[e.getButton()] = -1;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	
}
