import java.util.*;


public class ThreeLevelQueue
{
	Stack<Process> arrivalTimeStack = new Stack<>();
	Queue<Process> queue1 = new LinkedList<>();
	Queue<Process> queue2 = new LinkedList<>();
	Queue<Process> queue3 = new LinkedList<>();
	ArrayList<Box> output1 = new ArrayList<>();
	int totalBurstTime=0;
	int totalBurstTimeQ1 = 0;
	int totalBurstTimeQ2 = 0;
	int totalBurstTimeQ3 = 0;
    int time=0;
    int quantum; 
	String title = "Three Level Queue: ";
	
    public ThreeLevelQueue(){}
    
    public void runThreeLevelQueue(ArrayList<Process> p, int q)
    {
		this.quantum = quantum;

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
		Process tmp = new Process();
		int waitTime = 0;

		for(Process process: arrivalTimeStack)
		{
			if((process.getPriority() == 1) && (process.getPriority() == 2))
			{
				queue1.add(process);
				totalBurstTimeQ1++;
			}
			else if((process.getPriority() == 3) && (process.getPriority() == 4))
			{
				queue2.add(process);
				totalBurstTimeQ2++;
			}
			else if((process.getPriority() == 5) && (process.getPriority() == 6))
			{
				queue3.add(process);
				totalBurstTimeQ3++;
			}
		}

		for(int time=0;time<=totalBurstTimeQ1;time++) //Q1
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
            	queue1.add(arrivalTimeStack.pop());
			}
			if(currentProcess != null && time != 0 && currentProcess.getElapsedTime() >= currentProcess.getBurstTime()) {
				if(!queue1.isEmpty()){
					endTime = time;
					output1.add(new Box(currentProcess, startTime, endTime));
					currentProcess = queue1.poll();
					currentProcess.setWaitingTime(0);
					startTime = time;
				}
			}
			else if (currentProcess != null && time != 0 && time % quantum == 0){
				if(!queue1.isEmpty() && currentProcess.getPriority() >= queue1.peek().getPriority())
				{
					endTime = time;
					output1.add(new Box(currentProcess, startTime, endTime));
					if(!queue1.isEmpty())
					{
						currentProcess.setWaitingTime(0);
						tmp = currentProcess;
						currentProcess = queue1.poll();
						currentProcess.setWaitingTime(0);
						queue1.add(tmp);
					}
					startTime = time;
				}
			}
			if(currentProcess != null){
				currentProcess.incrementElapsedTime();
				
			}
			
		}
		for(Process proc: queue1)
		{
			proc.incrementWaitingTime();
		}
		endTime = totalBurstTime;
		output1.add(new Box(currentProcess, startTime, endTime));


		for(int time=0;time<=totalBurstTimeQ2;time++) //Q2
		{
			
		}

		for(int time=0;time<=totalBurstTimeQ3;time++) //Q3
		{
			
		}
	}

}