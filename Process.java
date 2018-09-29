import java.util.*;

public class Process implements Comparable<Process>
{
    String name;
    int arrivalTime=0;
    int burstTime;
    int priority=1;
    int timeQuantum;
    int elapsedTime = 0;
    int waitingTime = 0;
    
    public Process(String name, int arrivalTime,int burstTime,int priority, int timeQuantum) //for priority 1 and 2, with time quantum
    {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime =  burstTime;
        this.priority = priority;
        this.timeQuantum = timeQuantum;
        
    }
    public Process(String name, int arrivalTime,int burstTime,int priority) 
    {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime =  burstTime;
        this.priority = priority;
    }

    public Process(Process p){
        this.name = p.getName();
        this.arrivalTime = p.getArrivalTime();
        this.burstTime = p.getBurstTime();
        this.priority = p.getPriority();
        this.timeQuantum = p.getTimeQuantum();
        this.elapsedTime = 0;
        this.waitingTime = 0;
    }

	public Process(){}
    
    //setter functions
    public void setName(String name)
    {
        this.name = name;
    }
    public void setArrivalTime(int arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }
    
    public void resetBurstTime(int burstTime)
    {
        this.burstTime -= burstTime;
    }
    
    public void setPriority(int priority)
    {
        this.priority = priority;
    }
    
    public void setTimeQuantum(int timeQuantum)
    {
        this.timeQuantum = timeQuantum;
    }

    public void incrementElapsedTime()
    {
        this.elapsedTime++;
    }

    public void setElapsedTime(int elapsedTime)
    {
        this.elapsedTime = elapsedTime;
    }
    public void setWaitingTime(int waitingTime)
    {
        this.waitingTime = waitingTime;
    }
    public void incrementWaitingTime()
    {
        this.waitingTime++;
    }
    
    //getter functions
    public String getName()
    {
        return name;
    }
    
    public int getArrivalTime()
    {
        return arrivalTime;
    }
    
    public int getBurstTime()
    {
        return burstTime;
    }
    
    public int getPriority()
    {
        return priority;
    }
    
    public int getTimeQuantum()
    {
        return timeQuantum;
    }

	public int getElapsedTime()
	{
		return elapsedTime;
    }
    public int getWaitingTime()
    {
        return waitingTime;
    }
    
    //sort ccording to priority
    public int compareTo(Process p)
    {
        if((priority - p.getPriority()) == 0)
        {
            return arrivalTime - p.getArrivalTime();
            
        }
        return priority - p.getPriority();   
    }

}