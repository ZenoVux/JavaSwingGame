package com.zenovux;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import screen.Screen;
import screen.SplashScr;

public class GameCanvas extends JPanel {

    public static GameCanvas instance;
    public static GameMidlet midlet;
    public static mGraphics mg;
    public static Screen currentScreen;
    private GThread gThread;
    public static boolean isPointerClick;
    public static boolean isPointerDown;
    public static boolean isLoading;
    public static boolean[] keyPressedz = new boolean[33];
    public static boolean[] keyReleasedz = new boolean[33];
    public static boolean[] keyHold = new boolean[33];
    public static long lastTimePress = 0;
    public static int gameTick;
    public static int keyAsciiPress;
    public static int px;
    public static int pxFirst;
    public static int pxLast;
    public static int py;
    public static int pyFirst;
    public static int pyLast;
    public static int w;
    public static int w2d3;
    public static int w3d4;
    public static int wStr;
    public static int wd3;
    public static int wd6;
    public static int h;
    public static int h2d3;
    public static int h3d4;
    public static int hd3;
    public static int hd6;
    public static int hh;
    public static int hw;

    public GameCanvas(GameMidlet midlet) {
        this.midlet = midlet;
        setSize(midlet.getWidth(), midlet.getHeight());
        instance = this;
        MouseInput input = new MouseInput();
        addMouseListener(input);
        addMouseMotionListener(input);
        gThread = new GThread(this);
        gThread.setRunning(true);
        gThread.start();
        mg = new mGraphics();
        initGameCanvas();
        currentScreen = new SplashScr();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        mg.setGraphics(g);
        if (currentScreen != null) {// && !isLoading) {
            currentScreen.paint(mg);
        }
    }

    public void initGameCanvas() {
        w = getWidth();
        h = getHeight();
        hw = w / 2;
        hh = h / 2;
        wd3 = w / 3;
        hd3 = h / 3;
        w2d3 = (w * 2) / 3;
        h2d3 = (h * 2) / 3;
        w3d4 = (w * 3) / 4;
        h3d4 = (h * 3) / 4;
        wd6 = w / 6;
        hd6 = h / 6;
    }

    public void update() {
        if (currentScreen != null) {
            if (!isLoading) {
                currentScreen.update();
            }
            currentScreen.updateKey();
        }

    }

    protected void pointerPressed(int x, int y) {
        isPointerDown = true;
        isPointerClick = true;
        lastTimePress = System.currentTimeMillis();
        pxFirst = x;
        pyFirst = y;
        pxLast = x;
        pyLast = y;
        px = x;
        py = y;
    }

    protected void pointerDragged(int x, int y) {
//        if (Res.abs(x - pxLast) >= 10 || Res.abs(y - pyLast) >= 10) {
//            isPointerClick = false;
//        }
//        curPos++;
//        if (curPos > 3) {
//            curPos = 0;
//        }
//        arrPos[curPos] = new Position(x, y);
        px = x;
        py = y;
    }

    protected void pointerReleased(int x, int y) {
        isPointerDown = false;
        //isPointerJustRelease = true;
        //Screen.keyTouch = -1;
        px = x;
        py = y;
    }

    protected void keyPressed(int keyCode) {
        lastTimePress = System.currentTimeMillis();
        if ((keyCode >= 48 && keyCode <= 57) || ((keyCode >= 65 && keyCode <= 122) || keyCode == 10 || keyCode == 8 || keyCode == 13 || keyCode == 32)) {
            keyAsciiPress = keyCode;
        }
        mapKeyPress(keyCode);
    }

    public void mapKeyPress(int keyCode) {
        GameCanvas.currentScreen.keyPress(keyCode);
        switch (keyCode) {
            case KeyEvent.VK_UP:
                GameCanvas.keyHold[0] = true;
                GameCanvas.keyPressedz[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                GameCanvas.keyHold[1] = true;
                GameCanvas.keyPressedz[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                GameCanvas.keyHold[2] = true;
                GameCanvas.keyPressedz[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                GameCanvas.keyHold[3] = true;
                GameCanvas.keyPressedz[3] = true;
                break;
        }
    }

    protected void keyReleased(int keyCode) {
        keyAsciiPress = 0;
        mapKeyRelease(keyCode);
    }

    public void mapKeyRelease(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                GameCanvas.keyHold[0] = false;
                GameCanvas.keyReleasedz[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                GameCanvas.keyHold[1] = false;
                GameCanvas.keyReleasedz[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                GameCanvas.keyHold[2] = false;
                GameCanvas.keyReleasedz[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                GameCanvas.keyHold[3] = false;
                GameCanvas.keyReleasedz[3] = true;
                break;
        }
    }

    public static void clearKeyPressed() {
        for (int i = 0; i < keyPressedz.length; i++) {
            GameCanvas.keyPressedz[i] = false;
        }
        //GameCanvas.isPointerJustRelease = false;
    }

    public static void clearKeyHold() {
        for (int i = 0; i < keyHold.length; i++) {
            GameCanvas.keyHold[i] = false;
        }
    }

    public class GThread extends Thread {

        private boolean mRun = false;
        private GameCanvas mn;

        public GThread(GameCanvas gameCanvas) {
            this.mn = gameCanvas;
        }

        public void setRunning(boolean z) {
            this.mRun = z;
        }

        @Override
        public void run() {
            while (mRun) {
                long t1 = System.currentTimeMillis();
                GameCanvas.gameTick++;
                if (GameCanvas.gameTick > 10000) {
                    GameCanvas.gameTick = 0;
                }
                mn.update();
                repaint();
                long t2 = System.currentTimeMillis() - t1;
                if (t2 < 10) {
                    try {
                        Thread.sleep(10 - t2);
                    } catch (Exception e) {
                    }
                }
            }
        }

    }
}
