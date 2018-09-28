import java.util.*;

public class Proc implements Comparable<Proc>
{
    String name;
    int numOfProcesses;
    int arrivalTime=0;
    int burstTime;
    int priority=1;
    int timeQuantum;
	int elapsedTime = 0;
    
    public Proc(String name, int arrivalTime,int burstTime,int priority, int timeQuantum) //for priority 1 and 2, with time quantum
    {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime =  burstTime;
        this.priority = priority;
        this.timeQuantum = timeQuantum;
        
    }
    public Proc(String name, int arrivalTime,int burstTime,int priority) 
    {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime =  burstTime;
        this.priority = priority;
        
    }

	public Proc(){}
    
    //setter functions
    public void setName(String name)
    {
        this.name = name;
    }
    public void setArrivalTime(int arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }
    
    public void setBurstTime(int burstTime)
    {
        this.burstTime = burstTime;
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
    
    //sort ccording to priority
    public int compareTo(Proc p)
    {
        if((priority - p.getPriority()) == 0)
        {
            return arrivalTime - p.getArrivalTime();
            
        }
        return priority - p.getPriority();
    }
   
   
   
}