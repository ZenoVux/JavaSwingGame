package screen;

import com.zenovux.GameCanvas;
import com.zenovux.mGraphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import model.Button;
import model.IActionListener;

public class GameSrc extends Screen implements IActionListener {

    private int x, y;
    private Button[] btns;
    private String text = "Testz";

    public GameSrc() {
        btns = new Button[2];
        btns[0] = new Button(text, this, null, 2002);
        btns[1] = new Button("Test", this, null, 2003, 100, 100);
    }

    @Override
    public void update() {
        for (Button btn : btns) {
            btn.update();
        }

    }

    @Override
    public void paint(mGraphics g) {
        g.setColor(0, 0, 0);
        g.fillRect(0, 0, GameCanvas.w, GameCanvas.h);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/image/100x100.png"));
        } catch (Exception e) {
        }
        g.drawImage(img, x, y, mGraphics.HCENTER | mGraphics.VCENTER);
        g.setColor(255, 255, 255);
        g.drawString("" + GameCanvas.gameTick, 0, 10);
        for (Button btn : btns) {
            btn.paint(g);
        }
//        try {
//            Image image = new Robot().createScreenCapture(new Rectangle(0, 0, 1920, 1080));
//            BufferedImage image = new Robot().createScreenCapture(new Rectangle(0, 0, 1920, 1080));
//            g.getGraphics().drawImage(image, -1, -1, 1024, 600, null);
//        } catch (Exception e) {
//        }
    }

    @Override
    public void updateKey() {
        if (GameCanvas.keyHold[0] || GameCanvas.keyHold[0]) {
            y -= 5;
        }
        if (GameCanvas.keyHold[1] || GameCanvas.keyHold[1]) {
            y += 5;
        }
        if (GameCanvas.keyHold[2] || GameCanvas.keyHold[2]) {
            x -= 5;
        }
        if (GameCanvas.keyHold[3] || GameCanvas.keyHold[3]) {
            x += 5;
        }
        if (GameCanvas.keyAsciiPress != 0) {
            text += (char) GameCanvas.keyAsciiPress;
            btns[0].setCaption(text);
        }
        GameCanvas.clearKeyHold();
    }

    @Override
    public void perform(int idAction, Object obj) {
        switch (idAction) {
            case 2002:
                GameCanvas.currentScreen = new SplashScr();
                break;
            case 2003: {
//                try {
//                    BufferedImage image = new Robot().createScreenCapture(new Rectangle(0, 0, 1920, 1080)).getScaledInstance(1280, 720, 0);
//                    ImageIO.write(image, "png", new File("screenshot.png"));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
            break;
        }
        System.out.println("idAction: " + idAction);
    }

}
