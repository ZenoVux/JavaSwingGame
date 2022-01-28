
package com.zenovux;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class GameMidlet extends JFrame implements KeyListener  {
    
    public static GameMidlet instance = null;
    public static GameCanvas gameCanvas = null;

    public GameMidlet() {
        setSize(1024, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        instance = this;
        if (gameCanvas == null) {
            gameCanvas = new GameCanvas(this);
        }
        add(gameCanvas);
        addKeyListener(this);
    }
    
    public static void main(String[] args) {
        new GameMidlet().setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameCanvas.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gameCanvas.keyReleased(e.getKeyCode());
    }
}
