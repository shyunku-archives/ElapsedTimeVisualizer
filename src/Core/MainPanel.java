package Core;

import Tool.GraphDrawer;
import Tool.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MainPanel extends JPanel {
    private GraphDrawer drawer = new GraphDrawer();

    public MainPanel(){
        this.setSize(Global.windowSize);
        this.setLocation(0, 0);
        this.setVisible(true);

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Global.mousePos = e.getPoint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Global.mousePos = e.getPoint();
            }
        });

        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                final double rate = 1.25;
                if(e.getWheelRotation() > 0){
                    if(Global.MAX_TIME < 1000000000000L)
                        Global.MAX_TIME *= rate;
                }else{
                    if(Global.MAX_TIME > 50)
                        Global.MAX_TIME /= rate;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics gd) {
        super.paintComponent(gd);
        Graphics2D g = (Graphics2D)gd;
        Util.smoothRendering(g);

        g.setColor(new Color(30, 30, 30));
        g.fillRect(0, 0, Global.windowSize.width, Global.windowSize.height);


        Util.setFontSize(g, 15f);
        g.setColor(Color.WHITE);
        g.drawString("FrameCount: "+Global.frameCount, 10, 20);
        g.drawString("MousePos: "+String.format("(%d, %d)", Global.mousePos.x, Global.mousePos.y), 10, 35);

        drawer.drawGraph(g);
    }
}
