import java.util.*;

public class ThreeLvlQ
{
    ArrayList<Process> q1 = new ArrayList<Process>();
	ArrayList<Process> q2 = new ArrayList<Process>();
	ArrayList<Process> q3 = new ArrayList<Process>();
	Stack<Process> arrivalTimeStack1 = new Stack<Process>();
	Stack<Process> arrivalTimeStack2 = new Stack<Process>();
	Stack<Process> arrivalTimeStack3 = new Stack<Process>();
	int totalBurstTime = 0;
	int quantum;
		
    public ThreeLvlQprio() {};
	
	public void runThreeLvlQ(ArrayList<Process> p, int q){
		
		this.quantum = q;
		
		for(int i = 0; i < p.size(); i++){
			
			if(p.get(i).getPriority() == 1 || p.get(i).getPriority() == 2)
				q1.add(p.get(i));
			
			else if(p.get(i).getPriority() == 3 || p.get(i).getPriority() == 4)
				q2.add(p.get(i));
			
			else
				q3.add(p.get(i));
		}
		
		for(int i = 0; i < p.size(); i++){
			totalBurstTime += p.get(i).getBurstTime();
		}
		
		for(int i = 0; i < q1.size(); i++){
			arrivalTimeStack1.add(q1.get(i));
		}
		
		for(int i = 0; i < q2.size(); i++){
			arrivalTimeStack2.add(q2.get(i));
		}
		
		for(int i = 0; i < q3.size(); i++){
			arrivalTimeStack3.add(q3.get(i));
		}
		
		Collections.sort(arrivalTimeStack1, new ArrivalTimeComparator());
		Collections.sort(arrivalTimeStack2, new ArrivalTimeComparator());
		Collections.sort(arrivalTimeStack3, new ArrivalTimeComparator());
		
		// Loop begins here
		int startTime = 0;
		int endTime = 0;
		int currentQueue;
		Process currentProcess = arrivalTimeStack1.pop();
		Process nextProcess = new Process();
		PriorityQueue<Process> priorityQueue = new PriorityQueue<>();
		Stack<Process> arrivalTimeStack3 = new Stack<Process>();
		ArrayList<Box> output1 = new ArrayList<>();
		ArrayList<Box> output2 = new ArrayList<>();
		ArrayList<Box> output3 = new ArrayList<>();
		
		for(int time=0;time<=totalBurstTime;time++){
			if(!arrivalTimeStack1.isEmpty() && !arrivalTimeStack2.isEmpty() && !arrivalTimeStack3.isEmpty()){
				if(!arrivalTimeStack1.isEmpty()){
					currentProcess = arrivalTimeStack1.peek();
					currentQueue = 1;
				}
				
				else if(!arrivalTimeStack2.isEmpty()){
					currentProcess = arrivalTimeStack2.peek();
					currentQueue = 2;
				}
				
				else{
					currentProcess = arrivalTimeStack3.peek();
					currentQueue = 3;
				}
				
				if(currentQueue == 1){
					if(nextProcess != null && nextProcess.getArrivalTime() == time){
						priorityQueue.add(arrivalTimeStack1.pop());
					}
					
					if(currentProcess != null && (currentProcess.getElapsedTime() >= currentProcess.getBurstTime() || time % quantum == 0)){
						endTime = time;
						output1.add(new Box(currentProcess, startTime, endTime));
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
				
				if(currentQueue == 2 || currentQueue == 3){
					if(currentQueue == 3)
						arrivalTimeStack2.add(arrivalTimeStack3.pop());
					
					if(nextProcess != null && nextProcess.getArrivalTime() == time){
						if(nextProcess.getPriority() < currentProcess.getPriority()){
							// TODO: save current process end time and remaining burst time
							priorityQueue.add(currentProcess);
							endTime = time;
							if(currentQueue == 2)
								output2.add(new Box(currentProcess, startTime, endTime));
							
							else
								output3.add(new Box(currentProcess, startTime, endTime));
							
							currentProcess = arrivalTimeStack2.pop();
							startTime = time;
						}
						else{
							priorityQueue.add(arrivalTimeStack2.pop());
						}
					}
					
					if(currentProcess.getElapsedTime() >= currentProcess.getBurstTime()){
						// TOD: save in the output array
						endTime = time;
						if(currentQueue == 2)
							output2.add(new Box(currentProcess, startTime, endTime));
						
						else
							output3.add(new Box(currentProcess, startTime, endTime));
						
						currentProcess = priorityQueue.poll();
						startTime = time;
					}
					
					if(currentProcess != null){
						currentProcess.incrementElapsedTime();
					}
				}
			}
			
			else{
				nextProcess = null;
			}
		}
	}
}
