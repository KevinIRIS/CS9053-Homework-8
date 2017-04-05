package edu.nyu.cs9053.homework8;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LambdaScheduler{

    private  final  List<LambdaJob> jobList;

    public LambdaScheduler(){

        this.jobList = new LinkedList<>();
    }

    public LambdaScheduler(List<LambdaJob> jobList){

        this.jobList = jobList;
    }

    public  void addJob(LambdaJob job){
       jobList.add(job);
    }
    public List<LambdaJob> getOriginalJobList(){

        return jobList;
    }

    private void sort(){
        if(jobList == null){
            throw new IllegalArgumentException();
        }

        jobList.sort(new Comparator<LambdaJob>() {
            @Override
            public int compare(LambdaJob o1, LambdaJob o2) {
                return o1.getEndTime() - o2.getEndTime();
            }
        });
    }
    public List<LambdaJob> getMaxCompatibleList(){
        sort();

        int currentEndTime = Integer.MIN_VALUE;

        List<LambdaJob> newJobList = new ArrayList<>();

        for(LambdaJob job : jobList){
            if(job.getStartTime() >= currentEndTime){
                newJobList.add(job);
                currentEndTime = job.getEndTime();
            }
        }

        return newJobList;
    }

}
