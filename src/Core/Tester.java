package Core;

import Tool.Interval;
import Tool.IntervalTimer;
import Tool.TimePair;

import java.util.ArrayList;

public class Tester {
    public Interval interval = new Interval(0, 100000);     //실험 인자 범위
    private final int testChance = 3000;                    //그래프에 표시되는 좌표 수
    private int testDensity;                                //실험 인자 범위 대비 인자 수 (밀도)
    private IntervalTimer timer = new IntervalTimer();
    private IntervalTimer timer2 = new IntervalTimer();

    private long maxTime = 0;
    private long minTime = 1000000000000000000L;
    private long totalTime = 0;

    public ArrayList<TimePair> timePairs = new ArrayList<>();
    public ArrayList<TimePair> timePairs2 = new ArrayList<>();

    public String title1 = "StringBuffer";
    public String title2 = "StringBuilder";
    public boolean doubleType = true;

    public void test(){
        testDensity = 1;
        int testNum = interval.end - interval.start;
        if(testNum > 2*testChance)
            testDensity = testNum/testChance;
        new Thread(() -> {
            for(int i=interval.start; i<=interval.end; i+=testDensity){
                timer.setStart();
                content(i);
                timer.setEnd();

                if(doubleType) {
                    timer2.setStart();
                    content2(i);
                    timer2.setEnd();
                }

                long elapsedTime = timer.getElapsedTime();
                long elapsedTime2 = timer2.getElapsedTime();
                totalTime += elapsedTime + elapsedTime2;
                if(maxTime < elapsedTime) maxTime = elapsedTime;
                if(minTime > elapsedTime) minTime = elapsedTime;
                if(doubleType){
                    if(maxTime < elapsedTime2) maxTime = elapsedTime2;
                    if(minTime > elapsedTime2) minTime = elapsedTime2;
                }
                timePairs.add(new TimePair(i, elapsedTime));
                timePairs2.add(new TimePair(i, elapsedTime2));

                timer.reset();
                timer2.reset();
            }
            System.out.println("전체 종료: "+totalTime+"ns 소요");
            System.out.println("최소 시간: "+minTime+"ns 소요");
            System.out.println("최대 시간: "+maxTime+"ns 소요");
        }).start();
    }

    public void content(int var){
        StringBuffer str = new StringBuffer();
        for(long i=0;i<var;i++){
            str.append('a');
        }
    }

    public void content2(int var){
        StringBuilder str = new StringBuilder();
        for(long i=0;i<var;i++){
            str.append('a');
        }
    }
}
