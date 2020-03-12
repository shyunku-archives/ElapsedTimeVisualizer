package Tool;

import Core.Global;
import Core.Tester;

import java.awt.*;
import java.util.ArrayList;

public class GraphDrawer {
    public enum drawMode {
        LINEAR, EXPONENTIAL,
    };
    private Tester tester = new Tester();
    private Rectangle gridArea = new Rectangle(150, 100, 1250, 620);


    private final int GRID_PERIOD_X = 10;
    private final int GRID_PERIOD_Y = 20;

    private final Color gridAxisColor = new Color(150, 150, 150);
    private final Color gridSubLineColor = new Color(50, 50, 50);
    private final Color gridValueColor = new Color(150, 150, 150);
    private final Color gridMainLineColor = new Color(60, 60, 60);

    private Interval interval;

    public GraphDrawer(){
        interval = tester.interval;
        tester.test();
    }

    public void drawGraph(Graphics2D g){
        drawGrid(g);
    }

    public void drawGrid(Graphics2D g){
        g.setColor(gridAxisColor);

        //y, x-axis
        g.drawLine(gridArea.x, gridArea.y, gridArea.x, (int)gridArea.getMaxY());
        g.drawLine(gridArea.x, (int)gridArea.getMaxY(), (int)gridArea.getMaxX(), (int)gridArea.getMaxY());

        Util.setFontSize(g, 11f);
        int gridGapX = gridArea.height / GRID_PERIOD_X;
        for(int i=0;i<GRID_PERIOD_X;i++){
            long gridTimeVal = (long) ((double)(i* Global.MAX_TIME)/(double)GRID_PERIOD_X);
            Point gridYpoint = new Point(gridArea.x, (int)gridArea.getMaxY() - i* gridGapX);

            g.setColor(gridSubLineColor);
            g.drawLine(gridYpoint.x, gridYpoint.y, gridYpoint.x - 10, gridYpoint.y - 10);

            if(i!=0) {
                g.setColor(gridMainLineColor);
                g.drawLine(gridArea.x, gridYpoint.y, (int) gridArea.getMaxX(), gridYpoint.y);
            }

            g.setColor(gridValueColor);
            Util.drawRightAlignedString(g, gridTimeVal+"", gridYpoint.x - 15, gridYpoint.y - 15);
        }


        for(int i=0;i<=GRID_PERIOD_Y;i++){
            int index = (int)((double)(i*(interval.end - interval.start))/(double)GRID_PERIOD_Y + (double)interval.start);
            int gridGap = (int) ((double)(index - interval.start)*(double)(gridArea.width)/(double)(interval.end - interval.start));
            Point gridXpoint = new Point(gridArea.x + gridGap, (int)gridArea.getMaxY());

            g.setColor(gridSubLineColor);
            g.drawLine(gridXpoint.x, gridXpoint.y, gridXpoint.x - 10, gridXpoint.y + 10);

            g.setColor(gridValueColor);
            Util.drawRightAlignedString(g, index+"", gridXpoint.x - 15, gridXpoint.y + 15);
        }

        g.setColor(Color.WHITE);
        Util.setFontSize(g, 15f);
        g.drawString("실행 변수", 1410, (int) (gridArea.getMaxY()+5));
        Util.drawCenterAlignedString(g, "실행 시간(ns)", 150, 90);
        Util.drawCenterAlignedString(g,"최댓값 "+
                String.format("%.7fs", (double)Global.MAX_TIME/1000000000), 150, 70);


        g.setColor(new Color(233, 117, 1));
        ArrayList<TimePair> pairs = tester.timePairs;
        final int pairLen = pairs.size();
        int[] gxs = new int[pairLen];
        int[] gys = new int[pairLen];
        for(int i=0;i<pairLen;i++){
            TimePair curPair = pairs.get(i);
            int gx = (int) ((double)(curPair.getVar() - interval.start)*(double)(gridArea.width)/(double)(interval.end - interval.start)) + gridArea.x;
            int gy = (int) (gridArea.getMaxY() - (double)(gridArea.height*curPair.getElapsed())/(double)Global.MAX_TIME);
            gxs[i] = gx;
            gys[i] = gy;
        }
        g.drawPolyline(gxs, gys, pairLen);

        g.drawLine(gridArea.x + 30, gridArea.y + 30,gridArea.x + 60, gridArea.y + 30);
        g.drawString(tester.title1, gridArea.x + 70, gridArea.y + 35);


        if(tester.doubleType) {
            g.setColor(new Color(0, 203, 233));
            ArrayList<TimePair> pairs2 = tester.timePairs2;
            final int pairLen2 = pairs2.size();
            int[] gxs2 = new int[pairLen2];
            int[] gys2 = new int[pairLen2];
            for (int i = 0; i < pairLen2; i++) {
                TimePair curPair = pairs2.get(i);
                int gx = (int) ((double) (curPair.getVar() - interval.start) * (double) (gridArea.width) / (double) (interval.end - interval.start)) + gridArea.x;
                int gy = (int) (gridArea.getMaxY() - (double) (gridArea.height * curPair.getElapsed()) / (double) Global.MAX_TIME);
                gxs2[i] = gx;
                gys2[i] = gy;
            }
            g.drawPolyline(gxs2, gys2, pairLen2);

            g.drawLine(gridArea.x + 30, gridArea.y + 50, gridArea.x + 60, gridArea.y + 50);
            g.drawString(tester.title2, gridArea.x + 70, gridArea.y + 55);
        }
    }
}
