import java.util.*;
import java.util.Scanner;

public class FCFSprio 
{
    Stack<Proc> arrivalTimeStack = new Stack<>();
    PriorityQueue<Proc> priorityQueue = new PriorityQueue<>();
	ArrayList<Box> output = new ArrayList<>();
    int totalBurstTime=0;
    int time=0;
    
    public FCFSprio(){}
    
    public void runFCFS(ArrayList<Proc> p)
    {
		// Scanner in = new Scanner(System.in);
		// String s = "";
        for(int i=0;i<p.size();i++)
        {
            arrivalTimeStack.add(p.get(i));
            totalBurstTime += p.get(i).getBurstTime();
        }
        
		Collections.sort(arrivalTimeStack, new ArrivalTimeComparator());
        
		int startTime = 0;
		int endTime = 0;
		Proc currentProcess = arrivalTimeStack.pop();
		Proc nextProcess = new Proc();
       
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


		System.out.println()
		//display FCFS table
		System.out.println("FCFS-based preemptive priority:");
		for(int i=1;i<output.size();i++)
		{
			System.out.print("-------");
		
		}
		System.out.println();
		for(int i=0;i<output.size();i++)
		{
			if(i==0)
			{
				System.out.print("| " + output.get(i).p.getName());

			}
			else if(i==output.size()-1)
			{
				System.out.print("  | "+output.get(i).p.getName()+ " |");

			}
			else
			{
				System.out.print("  | " + output.get(i).p.getName());

			}
		}

		System.out.println();
		for(int i=1;i<output.size();i++)
		{
			System.out.print("-------");
		
		}
		
		System.out.println();
		for(int i=0;i<output.size();i++)
		{ 
			if(i==0)
			{
				System.out.print(output.get(0).startTime + "    " + output.get(0).endTime);
			}
			else{
				System.out.print("    " + output.get(i).endTime);
			}
				
		}
	
    }
}