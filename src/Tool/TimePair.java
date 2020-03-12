package Tool;

public class TimePair {
    private int var;
    private long elapsed;

    public TimePair(int var, long elapsed) {
        this.var = var;
        this.elapsed = elapsed;
    }

    public int getVar() {
        return var;
    }

    public void setVar(int var) {
        this.var = var;
    }

    public long getElapsed() {
        return elapsed;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }
}
