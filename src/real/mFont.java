package real;

import com.zenovux.mGraphics;
import java.util.Vector;

public class mFont {

    public static final int ADDMONEY = 6;
    public static final int CENTER = 2;
    public static final int FATAL = 3;
    public static final int FATAL_ME = 8;
    public static final int GREEN = 2;
    public static final int LEFT = 0;
    public static final int MISS = 4;
    public static final int MISS_ME = 7;
    public static final int ORANGE = 5;
    public static final int RED = 0;
    public static final int RIGHT = 1;
    public static final int YELLOW = 1;
    private int height;
    public static mFont tahoma_7b_red;
    public static mFont tahoma_7b_blue;
    public static mFont tahoma_7b_purple;
    public static mFont tahoma_7b_yellow;
    public static mFont tahoma_7b_green;
    public static mFont tahoma_7b_white;
    public static mFont tahoma_7;
    public static mFont tahoma_7_blue1;
    public static mFont tahoma_7_white;
    public static mFont tahoma_7_yellow;
    public static mFont tahoma_7_grey;
    public static mFont tahoma_7_red;
    public static mFont tahoma_7_blue;
    public static mFont tahoma_7_green;
    public static mFont tahoma_8b;
    public static mFont number_yellow;
    public static mFont number_red;
    public static mFont number_green;
    public static mFont number_white;
    public static mFont number_orange;
    public static mFont numberb_yellow;
    public static mFont numberb_red;
    public static mFont numberb_green;
    public static mFont numberb_white;
    public static mFont numberb_orange;

    public mFont() {
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getWidth(String str2) {
//        if (mGraphics.zoomLevel != 1) {
//            return FontSys.gI(this.id).getWidth(str2);
//        }
        int i = 0;
//        for (int i2 = 0; i2 < str2.length(); i2++) {
//            int indexOf = this.strFont.indexOf(str2.charAt(i2));
//            if (indexOf == -1) {
//                indexOf = 0;
//            }
//            i += this.fImages[indexOf][2] + this.space;
//        }
        return i;
    }


    public Vector splitFontVector(String str2, int i) {
        Vector vector = new Vector();
        String str3 = "";
        int i2 = 0;
        while (i2 < str2.length()) {
            if (str2.charAt(i2) == '\n') {
                vector.addElement(str3);
                str3 = "";
            } else {
                str3 = str3 + str2.charAt(i2);
                if (getWidth(str3) > i) {
                    int length = str3.length() - 1;
                    while (length >= 0 && str3.charAt(length) != ' ') {
                        length--;
                    }
                    if (length < 0) {
                        length = str3.length() - 1;
                    }
                    vector.addElement(str3.substring(0, length));
                    i2 = (i2 - (str3.length() - length)) + 1;
                    str3 = "";
                }
                if (i2 == str2.length() - 1 && !str3.trim().equals("")) {
                    vector.addElement(str3);
                }
            }
            i2++;
        }
        return vector;
    }

    public String splitFirst(String str2) {
        String str3 = "";
        boolean z = false;
        for (int i = 0; i < str2.length(); i++) {
            if (!z) {
                String substring = str2.substring(i, str2.length());
                if (compare(substring, "")) {
                    str3 = str3 + str2.charAt(i) + "-";
                } else {
                    str3 = str3 + substring;
                }
                z = true;
            } else if (str2.charAt(i) == ' ') {
                z = false;
            }
        }
        return str3;
    }

    public String[] splitFontArray(String str2, int i) {
        Vector splitFontVector = splitFontVector(str2, i);
        String[] strArr = new String[splitFontVector.size()];
        for (int i2 = 0; i2 < splitFontVector.size(); i2++) {
            strArr[i2] = splitFontVector.elementAt(i2).toString();
        }
        return strArr;
    }

    public boolean compare(String str2, String str3) {
        for (int i = 0; i < str2.length(); i++) {
            if (String.valueOf(str2.charAt(i)).equals(str3)) {
                return true;
            }
        }
        return false;
    }

}
