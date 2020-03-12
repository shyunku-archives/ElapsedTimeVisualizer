package Core;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Starter {
    public static void main(String[] args){
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Global.defaultFont = (Font.createFont(Font.TRUETYPE_FONT, new File("Resources\\RIXGOM.TTF")));
            ge.registerFont(Global.defaultFont);
        } catch (IOException | FontFormatException e) {
            //Handle exception
            e.printStackTrace();
        }

        MainFrame mainFrame = new MainFrame();
        MainPanel mainPanel = new MainPanel();

        mainFrame.add(mainPanel);
    }
}
