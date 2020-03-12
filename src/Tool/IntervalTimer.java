package Tool;

public class IntervalTimer {
    private long startFlag = 0;
    private long endFlag = 0;

    public void setStart(){
        startFlag = System.nanoTime();
    }

    public void setEnd(){
        endFlag = System.nanoTime();
    }

    public void reset(){
        startFlag = endFlag = 0;
    }

    public long getElapsedTime(){
        return endFlag - startFlag;
    }
}
