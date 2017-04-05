packagemain.java.edu.nyu.cs9053.homework8;


import java.util.*;

public class LambdaWeightedScheduler {
    private  final List<WeightedLambdaJob> jobList;

    public LambdaWeightedScheduler(){
        this.jobList = new ArrayList<>();
    }
    public LambdaWeightedScheduler(List<WeightedLambdaJob> jobList){
        if(jobList == null){
            throw new IllegalArgumentException();
        }
        this.jobList = jobList;
    }

    public void addJob(WeightedLambdaJob job){
        jobList.add(job);
    }

    public List<WeightedLambdaJob> getOriginalList(){
        return jobList;
    }
    private int findNotConflictMaxIndex(int[] comaptableMax,int index){
        WeightedLambdaJob job = jobList.get(index);
        int maxSoFar =  0;
        int preIndex = index;
        for(int i = index-1; i >= 0; i--){
            WeightedLambdaJob currentJob = jobList.get(i);
            if(currentJob.getEndTime() <= job.getStartTime() && comaptableMax[i] > maxSoFar){
                maxSoFar = comaptableMax[i];
                preIndex = i;
            }
        }
        return preIndex;
    }
    private void sort(){
        jobList.sort(new Comparator<WeightedLambdaJob>() {
            @Override
            public int compare(WeightedLambdaJob o1, WeightedLambdaJob o2) {
                return o1.getEndTime() - o2.getEndTime();
            }
        });
    }
    public List<WeightedLambdaJob> getMaxWeightedList(){
        if(jobList.isEmpty()){
            return new ArrayList<WeightedLambdaJob>();
        }
        sort();
        int[] indexList = new int[jobList.size()];
        int[] compatibleMax = new int[jobList.size()];
        for(int i =0 ; i < jobList.size(); i++){
            int previousIndex = findNotConflictMaxIndex(compatibleMax,i);
            if(previousIndex != i){
                compatibleMax[i]  = compatibleMax[previousIndex] + jobList.get(i).getWeight();
                indexList[i] = previousIndex;
            }else{
                compatibleMax[i] = jobList.get(i).getWeight();
                indexList[i] = i;
            }

        }
        int maxIndex = findMaxIndex(compatibleMax);
        List<WeightedLambdaJob> maxWeightedList = new ArrayList<>();
        if(maxIndex == -1){
            return maxWeightedList;
        }
        while (maxIndex != indexList[maxIndex]){
            maxWeightedList.add(jobList.get(maxIndex));
            maxIndex = indexList[maxIndex];
        }
        maxWeightedList.add(jobList.get(maxIndex));
        return maxWeightedList;
    }

    private int findMaxIndex(int[] compatibleMax){
        if(compatibleMax == null){
            throw new NullPointerException();
        }
        int maxIndex = -1;
        int maxSoFar = Integer.MIN_VALUE;
        for(int i = 0; i < compatibleMax.length; i++){
            if(compatibleMax[i] > maxSoFar){
                maxIndex = i;
                maxSoFar = compatibleMax[i];
            }
        }
        return maxIndex;
    }
    public void printSchedule(Collection<WeightedLambdaJob> jobsListtoPrint){
        Iterator<WeightedLambdaJob> iterator=jobsListtoPrint.iterator();
        WeightedLambdaJob weightedjob;
        System.out.println("WeightedJobsSchedule:");
        while(iterator.hasNext()){
            weightedjob=iterator.next();
            System.out.println("Start time: "+weightedjob.getStartTime()+
                    "End time:"+weightedjob.getEndTime()+"Weight:"+weightedjob.getWeight());
        }
    }

}
