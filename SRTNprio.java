import java.util.*;

public class SRTNprio
{
	Stack<Process> arrivalTimeStack = new Stack<>();
	Stack<Process> burstTimeStack = new Stack<>();
	PriorityQueue<Process> burstTimeQueue = new PriorityQueue<>(new BurstTimeComparator());
	ArrayList<Box> output = new ArrayList<>();
    int totalBurstTime=0;
	int time=0;
	String title = "SRTN-based Priority Scheduling: ";
	public SRTNprio(){}

	public void runSRTN(ArrayList<Process> process)
	{
		ArrayList<Process> p = new ArrayList<>(process);
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
			if(!arrivalTimeStack.isEmpty())
			{
				nextProcess = arrivalTimeStack.peek();
			}
			else {
				nextProcess = null;
			}
			if((nextProcess != null) && (nextProcess.getArrivalTime() == time))
			{
				if((currentProcess != null) && (nextProcess.getBurstTime() < currentProcess.getBurstTime()))
				{
					//todo:calculate and assign remaining burst time for preempted process and add it to queue
					currentProcess.resetBurstTime(currentProcess.getElapsedTime());
					currentProcess.setElapsedTime(0);
					burstTimeQueue.add(currentProcess);
					endTime = time;
					output.add(new Box(currentProcess, startTime, endTime));
					currentProcess = arrivalTimeStack.pop();
					startTime = time;
				}
				else if(currentProcess != null && nextProcess.getBurstTime() == currentProcess.getBurstTime())
				{
					if(nextProcess.getPriority() < currentProcess.getPriority()){
						currentProcess.resetBurstTime(currentProcess.getElapsedTime());
						currentProcess.setElapsedTime(0);
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
			if(currentProcess != null && currentProcess.getElapsedTime() >= currentProcess.getBurstTime()){ //if current process finishes executing save in output array
				endTime = time;
				output.add(new Box(currentProcess, startTime, endTime));
				currentProcess = burstTimeQueue.poll();
				startTime = time;
			}
			if(currentProcess != null){
				currentProcess.incrementElapsedTime();
			}
			
		}

	}//end of SRTN


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
		System.out.println();
		System.out.println();
	}//end of displayTimeline
    
}