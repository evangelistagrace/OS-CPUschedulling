import java.util.*;
import java.util.Scanner;

public class RRprio 
{
    Stack<Process> arrivalTimeStack = new Stack<>();
    PriorityQueue<Process> priorityQueue = new PriorityQueue<>(new WaitTimeComparator());
	ArrayList<Box> output = new ArrayList<>();
    int totalBurstTime=0;
    int time=0;
    int quantum = 0;
	String title = "Round Robin Priority Scheduling: ";
	
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
		Process tmp = new Process();
		int waitTime = 0;
       
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
			if(currentProcess != null && time != 0 && currentProcess.getElapsedTime() >= currentProcess.getBurstTime()) {
				if(!priorityQueue.isEmpty()){
					endTime = time;
					output.add(new Box(currentProcess, startTime, endTime));
					currentProcess = priorityQueue.poll();
					//currentProcess.setWaitingTime(0);
					startTime = time;
				}
			}
			else if (currentProcess != null && time != 0 && time % quantum == 0){
				endTime = time;
				output.add(new Box(currentProcess, startTime, endTime));
				if(!priorityQueue.isEmpty()){
					currentProcess.setWaitingTime(0);
					tmp = currentProcess;
					currentProcess = priorityQueue.poll();
					//currentProcess.setWaitingTime(0);
					priorityQueue.add(tmp);
				}
				startTime = time;
			}
			if(currentProcess != null){
				currentProcess.incrementElapsedTime();
				
			}
			for(Process proc: priorityQueue)
				{
					waitTime++;
					proc.setWaitingTime(waitTime);
				}

		}
		endTime = totalBurstTime;
		output.add(new Box(currentProcess, startTime, endTime));
	}
	
	// start of displayTimeline
	public void displayTimeline()
	{
		System.out.println();
		System.out.println(title);
		for(int i=0;i<output.size();i++)
		{
			String line = String.format("%8s", "-").replace(' ', '-');
			System.out.print(line);

		
		}
		System.out.println();
		for(int i=0;i<output.size();i++)
		{

			String tab = String.format("%1$2s", " ");
			String pname = String.format("%1$2s %2$-2s",  output.get(i).p.getName(), " ");

			if(i==(output.size()-1))
			{
				System.out.print("|" );
				System.out.print(tab);
				System.out.print(pname);
				System.out.print("|" );
			}
			else{
				System.out.print("|" );
				System.out.print(tab);
				System.out.print(pname);

			}
		
		}

		System.out.println();
		for(int i=0;i<output.size();i++)
		{
			String line = String.format("%8s", "-").replace(' ', '-');
			System.out.print(line);

		
		}
		
		System.out.println();
		for(int i=0;i<output.size();i++)
		{ 
			String timeline1 = String.format("%1$-4s %2$4s",  output.get(i).startTime, output.get(i).endTime);
			String timeline2 = String.format("%1$8s", output.get(i).endTime);

			if(i==0)
			{
				System.out.print(timeline1);
			}
			else{
				System.out.print(timeline2);
			}
				
		}
	}//end of displayTimeline
}



// if(currentProcess != null && time != 0 && (currentProcess.getElapsedTime() >= currentProcess.getBurstTime() || time % quantum == 0)) {
// 	endTime = time;
// 	output.add(new Box(currentProcess, startTime, endTime));
// 	Proc tmp = currentProcess;
// 	if(!priorityQueue.isEmpty()){
// 		currentProcess = priorityQueue.poll();
// 	}
// 	if(currentProcess.getElapsedTime() < currentProcess.getBurstTime()){
// 		priorityQueue.add(tmp);
// 	}
	
// 	startTime = time;
// }