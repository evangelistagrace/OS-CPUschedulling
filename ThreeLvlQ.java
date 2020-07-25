import java.util.*;

public class ThreeLvlQ
{
    PriorityQueue<Process> q1 = new PriorityQueue<>();
	PriorityQueue<Process> q2 = new PriorityQueue<>();
	PriorityQueue<Process> q3 = new PriorityQueue<>();
	Stack<Process> arrivalTimeStack1 = new Stack<Process>();
	Stack<Process> arrivalTimeStack2 = new Stack<Process>();
	Stack<Process> arrivalTimeStack3 = new Stack<Process>();
	ArrayList<Box> output1 = new ArrayList<>();
	ArrayList<Box> output2 = new ArrayList<>();
	ArrayList<Box> output3 = new ArrayList<>();
	int totalBurstTime = 0;
	int quantum;
	int quantumRun = 0;
		
    public ThreeLvlQ() {};
	
	public void runThreeLvlQ(ArrayList<Process> p, int q){
		
		this.quantum = q;
		
		for(int i = 0; i < p.size(); i++){
			
			if(p.get(i).getPriority() == 1 || p.get(i).getPriority() == 2)
			{
				Process newP = new Process(p.get(i));
				arrivalTimeStack1.add(newP);
			}
			else if(p.get(i).getPriority() == 3 || p.get(i).getPriority() == 4)
			{
				Process newP = new Process(p.get(i));
				arrivalTimeStack2.add(newP);
			}
			else
			{
				Process newP = new Process(p.get(i));
				arrivalTimeStack3.add(newP);
			}
		}
		
		for(int i = 0; i < p.size(); i++){
			totalBurstTime += p.get(i).getBurstTime();
		}
		
		
		Collections.sort(arrivalTimeStack1, new ArrivalTimeComparator());
		Collections.sort(arrivalTimeStack2, new ArrivalTimeComparator());
		Collections.sort(arrivalTimeStack3, new ArrivalTimeComparator());
		
		// Loop begins here
		int startTime = 0;
		int endTime = 0;
		int currentQueue = 0;
		Process nextProcess1 = new Process();
		Process nextProcess2 = new Process();
		Process nextProcess3 = new Process();
		Process currentProcess = new Process();
		Process tmp = new Process();

		if(!arrivalTimeStack1.isEmpty() && arrivalTimeStack1.peek().getArrivalTime() == 0){
			currentProcess = arrivalTimeStack1.pop();
			currentQueue = 1;
		}
		else if(!arrivalTimeStack2.isEmpty() && arrivalTimeStack2.peek().getArrivalTime() == 0){
			currentProcess = arrivalTimeStack2.pop();
			currentQueue = 2;
		}
		else if(!arrivalTimeStack3.isEmpty() && arrivalTimeStack3.peek().getArrivalTime() == 0){
			currentProcess = arrivalTimeStack3.pop();
			currentQueue = 3;
		}
		
		for(int time=0; time <= totalBurstTime; time++){
			if(!arrivalTimeStack1.isEmpty()){
				nextProcess1 = arrivalTimeStack1.peek();
			}
			else if(currentProcess != null && arrivalTimeStack1.isEmpty() && q1.isEmpty() && (currentProcess.getPriority() == 1 || currentProcess.getPriority() == 2)  && currentProcess.getElapsedTime() >= currentProcess.getBurstTime()) 
			{
				nextProcess1 = null;
				currentQueue = 2;
				endTime = time;
				output1.add(new Box(currentProcess, startTime, endTime));
				startTime = time;
				if(!q2.isEmpty()){
					currentProcess = q2.poll();
				}
			}
			else {
				nextProcess1 = null;
			}

			if(!arrivalTimeStack2.isEmpty() ){
				nextProcess2 = arrivalTimeStack2.peek();
			}
			else if (currentProcess != null && arrivalTimeStack2.isEmpty() && q2.isEmpty() && (currentProcess.getPriority() == 3 || currentProcess.getPriority() == 4)  && currentProcess.getElapsedTime() >= currentProcess.getBurstTime())
			{
				nextProcess2 = null;
				currentQueue = 3;
				endTime = time;
				output2.add(new Box(currentProcess, startTime, endTime));
				startTime = time;
				if(!q3.isEmpty()){
					currentProcess = q3.poll();
				}

			}
			else {
				nextProcess2 = null;
			}

			if(!arrivalTimeStack3.isEmpty()){
				nextProcess3 = arrivalTimeStack3.peek();
			}
			else {
				nextProcess3 = null;
			}

			// Check arrival of next process
			if(nextProcess1 != null && nextProcess1.getArrivalTime() == time){
				if(currentQueue == 2){
					currentQueue = 1;
					endTime = time;
					output2.add(new Box(currentProcess, startTime, endTime));
					if(currentProcess.getElapsedTime() < currentProcess.getBurstTime()){
						q2.add(currentProcess);
					}
					startTime = time;
					currentProcess = arrivalTimeStack1.pop();
					quantumRun = 0;
				}
				else if(currentQueue == 3){
					currentQueue = 1;
					endTime = time;
					output3.add(new Box(currentProcess, startTime, endTime));
					if(currentProcess.getElapsedTime() < currentProcess.getBurstTime()){
						q3.add(currentProcess);
					}
					startTime = time;
					currentProcess = arrivalTimeStack1.pop();
					quantumRun = 0;
				}
				else {
					q1.add(arrivalTimeStack1.pop());
				}
			}
			else if(nextProcess2 != null && nextProcess2.getArrivalTime() == time){
				if(currentQueue == 3){
					currentQueue = 2;
					endTime = time;
					output3.add(new Box(currentProcess, startTime, endTime));
					if(currentProcess.getElapsedTime() < currentProcess.getBurstTime()){
						q3.add(currentProcess);
					}
					startTime = time;
					currentProcess = arrivalTimeStack2.pop();
				}
				if(nextProcess2.getPriority() < currentProcess.getPriority() && currentQueue == 2)
				{
					q2.add(currentProcess);
					endTime = time;
					output2.add(new Box(currentProcess, startTime, endTime));
					currentProcess = arrivalTimeStack2.pop();
					startTime = time;
				}
				else
				{
					q2.add(arrivalTimeStack2.pop());
				}

			}
			else if(nextProcess3 != null && nextProcess3.getArrivalTime() == time){
				if(nextProcess3.getPriority() < currentProcess.getPriority() && currentQueue == 3)
				{
					q3.add(currentProcess);
					endTime = time;
					output3.add(new Box(currentProcess, startTime, endTime));
					currentProcess = arrivalTimeStack3.pop();
					startTime = time;
				}
				else
				{
					q3.add(arrivalTimeStack3.pop());
				}
			}
			
			// Check queue run and execute process
			if(currentQueue == 1){
				if(currentProcess != null && time != 0 && currentProcess.getElapsedTime() >= currentProcess.getBurstTime()) {
					if(!q1.isEmpty()){
						endTime = time;
						output1.add(new Box(currentProcess, startTime, endTime));
						currentProcess = q1.poll();
						currentProcess.setWaitingTime(0);
						startTime = time;
					}
				}
				else if (currentProcess != null && quantumRun != 0 && quantumRun % quantum == 0){
					if(!q1.isEmpty() && currentProcess.getPriority() >= q1.peek().getPriority())
					{
						endTime = time;
						output1.add(new Box(currentProcess, startTime, endTime));
						if(!q1.isEmpty())
						{
							currentProcess.setWaitingTime(0);
							tmp = currentProcess;
							currentProcess = q1.poll();
							currentProcess.setWaitingTime(0);
							q1.add(tmp);
						}
						startTime = time;
					}
				}
			}
			else if(currentQueue == 2){
				if(currentProcess != null && currentProcess.getElapsedTime() >= currentProcess.getBurstTime()){
					// TOD: save in the output array
					endTime = time;
					output2.add(new Box(currentProcess, startTime, endTime));
					currentProcess = q2.poll();
					startTime = time;
				}
			}
			else if(currentQueue == 3){
				if(currentProcess != null && currentProcess.getElapsedTime() >= currentProcess.getBurstTime()){
					// TOD: save in the output array
					endTime = time;
					output3.add(new Box(currentProcess, startTime, endTime));
					currentProcess = q3.poll();
					startTime = time;
				}
			}

			if(currentProcess != null){
				currentProcess.incrementElapsedTime();
				quantumRun++;
				
			}
		}
	}
	
	public void displayTimeline(){
		System.out.println("\n\n\nRound Robin queue: ");
		for(Box b: output1){
			System.out.println(b.p.getName() + " " + b.startTime + " " + b.endTime);
		}
		System.out.println("\n\n\nFCFS queue: ");
		for(Box b: output2){
			System.out.println(b.p.getName() + " " + b.startTime + " " + b.endTime);
		}
		System.out.print("\n\n\nSRTN queue: ");
		for(Box b: output3){
			System.out.println(b.p.getName() + " " + b.startTime + " " + b.endTime);
		}
	}

}
