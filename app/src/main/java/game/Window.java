package game;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lwjgl.openal.*;

import physics2d.Physics2D;
import scenes.*;
import utils.AssetPool;

import static org.lwjgl.openal.ALC10.ALC_DEFAULT_DEVICE_SPECIFIER;
import static org.lwjgl.openal.ALC10.alcCloseDevice;
import static org.lwjgl.openal.ALC10.alcCreateContext;
import static org.lwjgl.openal.ALC10.alcDestroyContext;
import static org.lwjgl.openal.ALC10.alcGetString;
import static org.lwjgl.openal.ALC10.alcMakeContextCurrent;
import static org.lwjgl.openal.ALC10.alcOpenDevice;
import static org.lwjgl.openal.ALC11.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class Window extends JPanel implements Runnable {
    
    public final static int UPS_SET = 60;
	public final static int WINDOW_WIDTH_DEFAULT = 320, WINDOW_HEIGHT_DEFAULT = 180;	

    private static Window window;
    private JFrame jFrame;
    private Thread thread;
    private static int FPS_SET;
    private static float GameScale;
	private static int WindowWidth, WindowHeight;
    private static Dimension windowSize;
    private boolean windowLoop;
    private float dt, absDt = 1f / (float) UPS_SET;

    private long audioContext, audioDevice;

    private static Scene currentScene;

    public Window() {
        windowLoop = true;
        FPS_SET = 60;
        GameScale = 5.0f;
        WindowWidth = (int) (WINDOW_WIDTH_DEFAULT * GameScale);
        WindowHeight = (int) (WINDOW_HEIGHT_DEFAULT * GameScale);
        windowSize = new Dimension(WindowWidth, WindowHeight);
    }

    private void initClasses() {
        changeScene(new PhysTestLevelInitializer());
    }

    private void update(float dt) {
        currentScene.update(dt);
    }

    private void render(Graphics2D g) {
        currentScene.render(g);
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static void changeScene(SceneInitializer sceneInitializer) {
        if(currentScene != null) {
            currentScene.destroy();
        }
        currentScene = new Scene(sceneInitializer);
        // currentScene.laod();
        currentScene.init();
        currentScene.start();
    }

    public void runWindow() {
        initAudio();
        initPanel();

        initClasses();
        
        initWindow();
        setFocusable(true);
        startLoop();
    }

    private void initAudio() {
        String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
        audioDevice = alcOpenDevice(defaultDeviceName);

        int[] attributes = {0};
        audioContext = alcCreateContext(audioDevice, attributes);
        alcMakeContextCurrent(audioContext);

        ALCCapabilities alcCapabilities = ALC.createCapabilities(audioDevice);
        ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);

        if (!alCapabilities.OpenAL11) {
            assert false : "Audio library not supported.";
        }
    }

    private void initPanel() {
        MouseInputListener mouseListener = new MouseInputListener();
        KeyInputListener keyListener = new KeyInputListener();
        setMinimumSize(windowSize);
        setPreferredSize(windowSize);
        setMaximumSize(windowSize);
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
    }

    private void initWindow() {
        jFrame = new JFrame("Dungeon Game Proj.");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                windowLoop = false;
            }
        });
        jFrame.add(this);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void setGameResolutionScale(float scale) {
        float temp = Math.round(scale);
        if (temp >= 3f && scale <= 6f) {
            GameScale = scale;
            WindowWidth = (int) (WINDOW_WIDTH_DEFAULT * GameScale);;
            WindowHeight = (int) (WINDOW_HEIGHT_DEFAULT * GameScale);
            windowSize = new Dimension(WindowWidth, WindowHeight);
            setMaximumSize(windowSize);
            setMinimumSize(windowSize);
            setPreferredSize(windowSize);
            jFrame.pack();
            jFrame.setLocationRelativeTo(null);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render((Graphics2D) g);
    }

    private void startLoop() {
        thread = new Thread(this);
        thread.start();
    }
    
    private void destroy() {
        currentScene.destroy();
        AssetPool.clear();
        alcDestroyContext(audioContext);
        alcCloseDevice(audioDevice);
    }

    public static Window get() {
        if(Window.window == null) {
            Window.window = new Window();
        }
        return Window.window;
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET, timePerUpdate = 1000000000.0 / UPS_SET, deltaU = 0, deltaF = 0;
        long previousTime = System.nanoTime(), lastCheck = System.currentTimeMillis(), previousUTime = System.nanoTime();
        // int frames = 0, updates = 0;
        while(windowLoop) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            // if(System.currentTimeMillis() - lastCheck >= 1000) {
            //     lastCheck = System.currentTimeMillis();
            //     System.out.println("FPS: " + frames + " | UPS: " + updates + " | DT: " + dt + " (" + absDt + ")");
            //     frames = 0;
            //     updates = 0;
            // }
            if(deltaU >= 1) {
                long currentUTime = System.nanoTime();
                dt =  (float) (currentUTime - previousUTime) / 1000000000.0f;
                update(dt);
                // updates++;
                previousUTime = currentUTime;
                deltaU--;
            }
            if(deltaF >= 1) {
                repaint();
                // frames++;
                deltaF--;
            }
        }

        destroy();
        System.exit(0);
    }

    public static Physics2D getPhysics() {
        return currentScene.getPhysics();
    }

    public static int Width() {
        return (int) get().WindowWidth;
    }
    
    public static int Height() {
        return (int) get().WindowHeight;
    }

    public static Dimension Size() {
        return get().windowSize;
    }
    
    public static float Scale() {
        return get().GameScale;
    }
}


