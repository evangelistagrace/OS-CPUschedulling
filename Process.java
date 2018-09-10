import java.util.*;

public class Process
{
    Scanner input = new Scanner(System.in);
    String name;
    int numOfProcesses;
    int arrivalTime;
    int burstTime;
    int priority;
    int timeQuantum;
    
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
}
