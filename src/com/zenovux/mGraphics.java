package com.zenovux;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class mGraphics {

    public Graphics2D graphics = null;
    public static int zoomLevel = 1;
    public int translateX = 0;
    public int translateY = 0;
    public static int HCENTER = 1;
    public static int VCENTER = 2;
    public static int LEFT = 4;
    public static int RIGHT = 8;
    public static int TOP = 16;
    public static int BOTTOM = 32;
    public static final int TRANS_MIRROR = 2;
    public static final int TRANS_MIRROR_ROT180 = 1;
    public static final int TRANS_MIRROR_ROT270 = 4;
    public static final int TRANS_MIRROR_ROT90 = 7;
    public static final int TRANS_NONE = 0;
    public static final int TRANS_ROT180 = 3;
    public static final int TRANS_ROT270 = 6;
    public static final int TRANS_ROT90 = 5;

    public void setGraphics(Graphics g) {
        graphics = (Graphics2D) g;
    }

    public Graphics2D getGraphics() {
        return graphics;
    }

    public void setColor(int rgb) {
        int num = rgb & 255;
        int num2 = rgb >> 8 & 255;
        int num3 = rgb >> 16 & 255;
        float b = (float) num / 256f;
        float g = (float) num2 / 256f;
        float r = (float) num3 / 256f;
        graphics.setColor(new Color(r, g, b, 1f));
    }

    public void setColor(int r, int g, int b) {
        graphics.setColor(new Color(r, g, b));
    }

    public void fillRect(int x, int y, int w, int h) {
        x *= zoomLevel;
        y *= zoomLevel;
        graphics.fillRect(x, y, w, h);
    }

    public void drawImage(BufferedImage img, int x, int y) {
        x *= zoomLevel;
        y *= zoomLevel;
        graphics.drawImage(img, x, y, null);
    }

    public void drawImage(BufferedImage img, int x, int y, int anchor) {
        x *= zoomLevel;
        y *= zoomLevel;
        _drawRegion(img, 0, 0, img.getWidth(), img.getHeight(), 0, x, y, anchor);
    }

    public void drawRegion(BufferedImage image, float x0, float y0, int w0, int h0, int transform, int x, int y, int anchor) {
        x *= zoomLevel;
        y *= zoomLevel;
        x0 *= zoomLevel;
        y0 *= zoomLevel;
        w0 *= zoomLevel;
        h0 *= zoomLevel;
        _drawRegion(image, x0, y0, w0, h0, transform, x, y, anchor);
    }

    public void _drawRegion(BufferedImage image, float x0, float y0, int w0, int h0, int transform, int x, int y, int anchor) {
        float cx = 0f;
        float cy = 0f;
        if ((anchor & mGraphics.HCENTER) == mGraphics.HCENTER) {
            cx -= w0 / 2f;
        }
        if ((anchor & mGraphics.VCENTER) == mGraphics.VCENTER) {
            cy -= h0 / 2f;
        }
        if ((anchor & mGraphics.RIGHT) == mGraphics.RIGHT) {
            cx -= w0;
        }
        if ((anchor & mGraphics.BOTTOM) == mGraphics.BOTTOM) {
            cy -= h0;
        }
        x += (int) cx;
        y += (int) cy;
        switch (transform) {
            case 1:
                image = rotate(image, 180);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }
        image = image.getSubimage((int) x0, (int) y0, w0, h0);
        graphics.drawImage(image, x, y, w0, h0, null);
    }

    public static BufferedImage rotate(BufferedImage bimg, double angle) {

        int w = bimg.getWidth();
        int h = bimg.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(Math.toRadians(angle), w / 2, h / 2);
        graphic.drawImage(bimg, null, 0, 0);
        graphic.dispose();
        return rotated;
    }

    public void drawString(String str, int x, int y) {
//        try {
//            String fName = "/Fonts/UTM Azuki.ttf";
//            InputStream is = mGraphics.class.getResourceAsStream(fName);
//            Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20);
//            graphics.setFont(font);
//        } catch (Exception e) {
//        }

        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD));
        Font font = graphics.getFont();
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        FontMetrics fm = img.getGraphics().getFontMetrics(font);
        int width = fm.stringWidth(str);
        graphics.drawString(str, (x + 50) - (width / 2), y);
        if (GameCanvas.gameTick % 10 != 0) {
            graphics.drawString("|", (x + 51) + (width / 2), y);
        }
    }

    public static int getImageHeight(BufferedImage img) {
        return img.getHeight();
    }

    public static int getImageWidth(BufferedImage img) {
        return img.getWidth();
    }

}
