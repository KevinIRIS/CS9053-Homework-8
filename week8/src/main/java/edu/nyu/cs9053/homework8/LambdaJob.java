package edu.nyu.cs9053.homework8;

public  class LambdaJob {

    private final int startTime;
    private final int endTime;

    public LambdaJob(int startTime, int endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public int getStartTime(){
        return startTime;
    }
    public int getEndTime(){
        return endTime;
    }
}
