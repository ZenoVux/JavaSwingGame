package screen;

import com.zenovux.GameCanvas;
import com.zenovux.mGraphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class SplashScr extends Screen {

    //public SplashScr instance = null;
    public int splashScrStat;
    public int time;

    public SplashScr() {
        splashScrStat = 0;
        time = 100;
        //instance = this;
    }

    @Override
    public void update() {
        if (splashScrStat >= time) {
            GameCanvas.currentScreen = new GameSrc();
        }
        splashScrStat++;
    }

    @Override
    public void paint(mGraphics g) {
        g.setColor(0, 0, 0);
        g.fillRect(0, 0, GameCanvas.w, GameCanvas.h);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/image/logo.png"));
        } catch (Exception e) {
        }
        g.drawImage(img,  GameCanvas.hw, GameCanvas.h / 2, mGraphics.HCENTER | mGraphics.VCENTER);
    }
    
}
