import java.util.*;

public class FCFSprio 
{
    Stack<Process> arrivalTimeStack = new Stack<>();
    PriorityQueue<Process> priorityQueue = new PriorityQueue<>();
	ArrayList<Thread> output = new ArrayList<>();
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
        
        Collections.sort(arrivalTimeStack, new ArrivalTimeComparator());
        
		int startTime = 0;
		int endTime = 0;
		Process currentProcess = arrivalTimeStack.pop();
		Process nextProcess = new Process();
       
        for(int time=0;time<=totalBurstTime;time++)
        {
			nextProcess = arrivalTimeStack.peek();
			if(nextProcess.getArrivalTime() == time && nextProcess != null)
			{
				
				if(nextProcess.getPriority() <= currentProcess.getPriority())
				{
					// TODO: save current process end time and remaining burst time
					endTime = time;
					output.add(new Thread(currentProcess, startTime, endTime));
					currentProcess = arrivalTimeStack.pop();
					startTime = time;
				}
				else
				{
					priorityQueue.add(arrivalTimeStack.pop());
				}
			}
			if(currentProcess.getElapsedTime() == currentProcess.getBurstTime()){
				// TOD: save in the output array
				endTime = time;
				output.add(new Thread(currentProcess, startTime, endTime));
				currentProcess = priorityQueue.poll();
				startTime = time;
			}
		}

		for(int i=0;i<output.size();i++)
		{
			System.out.println("threads: ");
		}
	
    }
}