package Core;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        this.setSize(Global.windowSize);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000/144);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                    Global.frameCount++;
                }
            }
        });

        mainThread.start();
    }
}
