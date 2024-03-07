package game;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lwjgl.openal.*;
import scenes.Scene;
import scenes.SceneInitializer;
import scenes.TestLevelInitializer;

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
    private double gameTime;
    private static int FPS_SET;
    private static float GameScale;
	private static int WindowWidth, WindowHeight;
    private static Dimension windowSize;
    private boolean windowLoop;

    private long audioContext, audioDevice;

    private static Scene currentScene;

    public Window() {
        windowLoop = true;
        FPS_SET = 30;
        GameScale = 5.0f;
        WindowWidth = (int) (WINDOW_WIDTH_DEFAULT * GameScale);
        WindowHeight = (int) (WINDOW_HEIGHT_DEFAULT * GameScale);
        windowSize = new Dimension(WindowWidth, WindowHeight);
    }

    private void initClasses() {
        changeScene(new TestLevelInitializer());
    }

    private void update(float dt) {
        currentScene.update(dt);

    }

    private void render(Graphics2D g) {
        currentScene.render(g);
    }

    public static Scene getCurrentScene() {
        return get().currentScene;
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

    private void initWindow() {
        // set window panel
        MouseInputListener mouseListener = new MouseInputListener();
        addKeyListener(new KeyInputListener());
        addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
        setMinimumSize(windowSize);
        setPreferredSize(windowSize);
        setMaximumSize(windowSize);

        // set window frame
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
        alcDestroyContext(audioContext);
        alcCloseDevice(audioDevice);
    }

    public static Window get() {
        if(Window.window == null) {
            Window.window = new Window();
        }
        return Window.window;
    }

    float dt = 1f / UPS_SET;

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET, timePerUpdate = 1000000000.0 / UPS_SET, deltaU = 0, deltaF = 0;
        long previousTime = System.nanoTime(), lastCheck = System.currentTimeMillis();
        int frames = 0, updates = 0;
        while(windowLoop) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if(deltaU >= 1) {
                update(dt);
                updates++;
                deltaU--;
            }
            if(deltaF >= 1) {
                repaint();
                frames++;
                deltaF--;
            }
        }

        destroy();
        System.exit(0);
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


