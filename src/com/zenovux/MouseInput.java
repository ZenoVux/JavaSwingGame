package com.zenovux;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class MouseInput extends MouseInputAdapter {

    public static int px, py;
    public static boolean isPointerDown;

    @Override
    public void mousePressed(MouseEvent e) {
        isPointerDown = true;
        GameCanvas.instance.pointerPressed(e.getX() / mGraphics.zoomLevel,
                e.getY() / mGraphics.zoomLevel);
        //System.out.println("0 - " + GameCanvas.px + " - " + GameCanvas.py);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isPointerDown = false;
        GameCanvas.instance.pointerReleased(e.getX() / mGraphics.zoomLevel,
                e.getY() / mGraphics.zoomLevel);
        //System.out.println("1 - " + GameCanvas.px + " - " + GameCanvas.py);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        GameCanvas.instance.pointerDragged(e.getX() / mGraphics.zoomLevel,
                e.getY() / mGraphics.zoomLevel);
        //System.out.println("2 - " + GameCanvas.px + " - " + GameCanvas.py);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        px = e.getX();
        py = e.getY();
    }

    public static boolean isPointer(int x, int y, int w, int h) {
        return isPointerDown && (px >= x && px <= x + w && py >= y && py <= y + h);
    }

    public static boolean isPointerHoldIn(int x, int y, int w, int h) {
        return (px >= x && px <= x + w && py >= y && py <= y + h);
    }
}
