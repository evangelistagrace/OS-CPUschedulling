import java.util.*;

public class FCFSprio 
{
    Stack<Process> arrivalTimeStack = new Stack<>();
    PriorityQueue<Process> priorityQueue = new PriorityQueue<>();
    int totalBurstTime=0;
    int time=0;
    
    public FCFSprio(){}
    
    public void runFCFS(ArrayList<Process> p)
    {
     
        for(int i=0;i<p.size();i++)
        {
            arrivalTimeStack.add(p.get(i));
            totalBurstTime += p.get(i).getBurstTime();
        }
        
        Collections.sort(arrivalTimeStack,new ArrivalTimeComparator());
        
       
        for(int i=0;i<=totalBurstTime;i++)
        {
           for(int j=0;j<arrivalTimeStack.size();j++)
		   {
			   while(time<=arrivalTimeStack.get(j).getBurstTime())
			   {
				   if(arrivalTimeStack.get(j+1).getArrivalTime()==time)
				   {
					   if(arrivalTimeStack.get(j+1).getPriority()<arrivalTimeStack.get(j).getPriority()) //if next process has lower priority than current process,
					   																						//add current process to queue
					   {
						   priorityQueue.add(arrivalTimeStack.get(j));	
						   arrivalTimeStack.pop();
					   }
				   }
				   time++;
			   }
		   }
            
        }
    }
}