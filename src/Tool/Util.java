package Tool;

import Core.Global;
import com.sun.prism.Graphics;

import java.awt.*;

public class Util {
    public static void drawRightAlignedString(Graphics2D g, String str, int x, int y){
        g.drawString(str, x - getFontWidth(g, str), y);
    }

    public static void drawCenterAlignedString(Graphics2D g, String str, int x, int y){
        g.drawString(str, x - getFontWidth(g, str)/2, y);
    }

    public static void setFontSize(Graphics2D g, float size){
        g.setFont(Global.defaultFont.deriveFont(size));
    }

    public static int getFontWidth(Graphics2D g, String str) {
        return g.getFontMetrics().stringWidth(str);
    }

    public static void smoothRendering(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //text smooth
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //roundRect smooth
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }
}
