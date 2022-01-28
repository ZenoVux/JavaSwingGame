package model;

import com.zenovux.GameCanvas;
import com.zenovux.MouseInput;
import com.zenovux.mGraphics;

public class Button {

    public String caption;
    public IActionListener actionListener;
    public Object object;
    public int idAction;
    public int x, y;

    public Button() {
    }

    public Button(String caption, IActionListener actionListener, Object object, int idAction) {
        this.caption = caption;
        this.actionListener = actionListener;
        this.object = object;
        this.idAction = idAction;
    }

    public Button(String caption, IActionListener actionListener, Object object, int idAction, int x, int y) {
        this.caption = caption;
        this.actionListener = actionListener;
        this.object = object;
        this.idAction = idAction;
        this.x = x;
        this.y = y;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
    
    public void paint(mGraphics g) {
        g.setColor(255, 255, 255);
        g.fillRect(this.x, this.y, 100, 25);
        if (MouseInput.isPointerHoldIn(this.x, this.y, 100, 25)) {
            g.setColor(255, 0, 0);
        } else {
            g.setColor(0, 255, 0);
        }
        g.graphics.drawRect(this.x, this.y, 99, 24);
        g.setColor(0, 0, 0);
        g.drawString(caption, x, y + 17);
    }

    public void performAction() {
        if (this.idAction > 0) {
            if (this.actionListener != null) {
                this.actionListener.perform(this.idAction, this.object);
            }
        }
    }

    public void update() {
        if (MouseInput.isPointer(this.x, this.y, 100, 25)) {
            this.performAction();
        }
    }
}
