import java.util.*;
import java.util.Scanner;

public class RRprio 
{
    Stack<Process> arrivalTimeStack = new Stack<>();
    PriorityQueue<Process> priorityQueue = new PriorityQueue<>();
	ArrayList<Box> output = new ArrayList<>();
    int totalBurstTime=0;
    int time=0;
    int quantum = 0;
    
    public RRprio(){}
    
    public void runRR(ArrayList<Process> p, int q)
    {
		this.quantum = q;
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
			if(!arrivalTimeStack.isEmpty())
			{
                // TODO: consider the special case when 2 or more processes arrive at the same time
				nextProcess = arrivalTimeStack.peek();
			}
			else {
				nextProcess = null;
			}
			if(nextProcess != null && nextProcess.getArrivalTime() == time)
			{
                priorityQueue.add(arrivalTimeStack.pop());

			}
			if(currentProcess != null && (currentProcess.getElapsedTime() >= currentProcess.getBurstTime() || time % quantum == 0)) {
				endTime = time;
                output.add(new Box(currentProcess, startTime, endTime));
                Process tmp = currentProcess;
                if(!priorityQueue.isEmpty()){
                    currentProcess = priorityQueue.poll();
                }
                if(currentProcess.getElapsedTime() < currentProcess.getBurstTime()){
                    priorityQueue.add(tmp);
                }
				startTime = time;
			}
			if(currentProcess != null){
				currentProcess.incrementElapsedTime();
			}
		}

		for(Box t: output)
		{
			System.out.println("thread: " + t.p.getName() + ", start time: " + t.startTime + ", end time: " + t.endTime);
		}
	
    }
}