import java.util.*;
import java.util.Scanner;

public class FCFSprio
{
    Stack<Process> arrivalTimeStack = new Stack<>();
    PriorityQueue<Process> priorityQueue = new PriorityQueue<>();
	ArrayList<Box> output = new ArrayList<>();
    int totalBurstTime=0;
    int time=0;
	String title = "FCFS-based Priority Scheduling: ";
	
    public FCFSprio(){}
    
    public void runFCFS(ArrayList<Process> p)
    {
        for(int i=0;i<p.size();i++)
        {
			Process newP = new Process(p.get(i));
			arrivalTimeStack.add(newP);
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
				nextProcess = arrivalTimeStack.peek();
			}
			else {
				nextProcess = null;
			}
			if(nextProcess != null && nextProcess.getArrivalTime() == time)
			{
				if(nextProcess.getPriority() < currentProcess.getPriority())
				{
					// TODO: save current process end time and remaining burst time
					priorityQueue.add(currentProcess);
					endTime = time;
					output.add(new Box(currentProcess, startTime, endTime));
					currentProcess = arrivalTimeStack.pop();
					startTime = time;
				}
				else
				{
					priorityQueue.add(arrivalTimeStack.pop());
				}
			}
			if(currentProcess.getElapsedTime() >= currentProcess.getBurstTime()){
				// TOD: save in the output array
				endTime = time;
				output.add(new Box(currentProcess, startTime, endTime));
				currentProcess = priorityQueue.poll();
				startTime = time;
			}
			if(currentProcess != null){
				currentProcess.incrementElapsedTime();
			}
		}
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
	