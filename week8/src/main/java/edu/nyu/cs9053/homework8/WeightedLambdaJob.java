package edu.nyu.cs9053.homework8;


public class WeightedLambdaJob extends LambdaJob{
    private final int weight;

    public WeightedLambdaJob(int startTime, int endTime, int weight){
        super(startTime,endTime);
        this.weight = weight;
    }
    public int getWeight(){
        return weight;
    }

}
