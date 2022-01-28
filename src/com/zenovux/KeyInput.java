package com.zenovux;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput extends KeyAdapter implements KeyListener {
    
    @Override
    public void keyPressed(KeyEvent e) {
        GameCanvas.instance.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        GameCanvas.instance.keyReleased(e.getKeyCode());
    }
    
}
