import java.util.*;

public class SRTNprio
{
	Stack<Process> arrivalTimeStack = new Stack<>();
	Stack<Process> burstTimeStack = new Stack<>();
	PriorityQueue<Process> burstTimeQueue = new PriorityQueue<>(new BurstTimeComparator());
	ArrayList<Box> output = new ArrayList<>();
    int totalBurstTime=0;
	int time=0;
	
	public SRTNprio(){}

	public void runSRTN(ArrayList<Process> p)
	{
		for(int i=0;i<p.size();i++)
        {
            arrivalTimeStack.add(p.get(i));
            totalBurstTime += p.get(i).getBurstTime();
		}
		
		Collections.sort(arrivalTimeStack, new ArrivalTimeComparator());  //arrange proccesses in order of ascending arrival time

		int startTime = 0;
		int endTime = 0;
		Process currentProcess = arrivalTimeStack.pop();  //take the process with the earliest arrival for the first process
		Process nextProcess = new Process();

		// for(int i=0;i<arrivalTimeStack.size();i++)  //place remaining processes inside queue
        // {
        //     burstTimeStack.add(arrivalTimeStack.get(i));
		// }
		

		for(int time=0;time<=totalBurstTime;time++)
        {
			if(!burstTimeStack.isEmpty())
			{
				nextProcess = arrivalTimeStack.peek();
			}
			else {
				nextProcess = null;
			}
			if(nextProcess != null && nextProcess.getArrivalTime() == time)
			{
				if(currentProcess != null && nextProcess.getBurstTime() < currentProcess.getBurstTime())
				{
					//todo:calculate and assign remaining burst time for preempted process and add it to queue
					currentProcess.setBurstTime(currentProcess.getElapsedTime());
					burstTimeQueue.add(currentProcess);
					endTime = time;
					output.add(new Box(currentProcess, startTime, endTime));
					currentProcess = arrivalTimeStack.pop();
					startTime = time;
				}
				else if(currentProcess != null && nextProcess.getBurstTime() >= currentProcess.getBurstTime())
				{
					if(nextProcess.getPriority() < currentProcess.getPriority()){
						currentProcess.setBurstTime(currentProcess.getElapsedTime());
						burstTimeQueue.add(currentProcess);
						endTime = time;
						output.add(new Box(currentProcess, startTime, endTime));
						currentProcess = arrivalTimeStack.pop();
						startTime = time;
					}
				}
				else
				{
					burstTimeQueue.add(arrivalTimeStack.pop());
				}
			}
			else if(currentProcess != null && currentProcess.getElapsedTime() >= currentProcess.getBurstTime()){ //if current process finishes executing save in output array
				endTime = time;
				output.add(new Box(currentProcess, startTime, endTime));
				currentProcess = burstTimeQueue.poll();
				startTime = time;
			}
			if(currentProcess != null){
				currentProcess.incrementElapsedTime();
			}
			
		}

		//display FCFS table
		System.out.println();
		System.out.println("SRTN-based preemptive priority:");
		for(int i=1;i<output.size();i++)
		{
			System.out.print("--------");
		
		}
		System.out.println();
		for(int i=0;i<output.size();i++)
		{
			if(i==0)
			{
				System.out.print("|   " + output.get(i).p.getName());

			}
			else if(i==output.size()-1)
			{
				System.out.print("   | "+output.get(i).p.getName()+ "   |");

			}
			else
			{
				System.out.print("   |   " + output.get(i).p.getName());

			}
		}

		System.out.println();
		for(int i=1;i<output.size();i++)
		{
			System.out.print("--------");
		
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