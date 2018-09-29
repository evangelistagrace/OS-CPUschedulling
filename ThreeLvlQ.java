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
				arrivalTimeStack1.add(newP);
			}
			else
			{
				Process newP = new Process(p.get(i));
				arrivalTimeStack1.add(newP);
			}
		}
		
		for(int i = 0; i < p.size(); i++){
			totalBurstTime += p.get(i).getBurstTime();
		}
		
		// for(int i = 0; i < q1.size(); i++){
		// 	arrivalTimeStack1.add(q1.get(i));
		// }
		
		// for(int i = 0; i < q2.size(); i++){
		// 	arrivalTimeStack2.add(q2.get(i));
		// }
		
		// for(int i = 0; i < q3.size(); i++){
		// 	arrivalTimeStack3.add(q3.get(i));
		// }
		
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

		if(!arrivalTimeStack1.isEmpty()){
			currentProcess = arrivalTimeStack1.pop();
		}
		else if(!arrivalTimeStack2.isEmpty()){
			currentProcess = arrivalTimeStack2.pop();
		}
		else if(!arrivalTimeStack3.isEmpty()){
			currentProcess = arrivalTimeStack3.pop();
		}
		
		for(int time=0; time <= totalBurstTime; time++){
			if(!arrivalTimeStack1.isEmpty()){
				nextProcess1 = arrivalTimeStack1.peek();
			}
			else {
				nextProcess1 = null;
				currentQueue = 2;
				endTime = totalBurstTime;
				output1.add(new Box(currentProcess, startTime, endTime));
			}
			if(!arrivalTimeStack2.isEmpty()){
				nextProcess2 = arrivalTimeStack1.peek();
			}
			else {
				nextProcess2 = null;
				currentQueue = 3;

			}
			if(!arrivalTimeStack3.isEmpty()){
				nextProcess3 = arrivalTimeStack1.peek();
			}
			else {
				nextProcess3 = null;
			}

			// Check arrival of next process
			if(nextProcess1 != null && nextProcess1.getArrivalTime() == time){
				currentQueue = 1;
				q1.add(arrivalTimeStack1.pop());
			}
			else if(nextProcess2 != null && nextProcess2.getArrivalTime() == time){
				if(nextProcess2.getPriority() < currentProcess.getPriority() && currentQueue == 2)
				{
					// TODO: save current process end time and remaining burst time
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
					// TODO: save current process end time and remaining burst time
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
				else if (currentProcess != null && time != 0 && time % quantum == 0){
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
				if(currentProcess.getElapsedTime() >= currentProcess.getBurstTime()){
					// TOD: save in the output array
					endTime = time;
					output2.add(new Box(currentProcess, startTime, endTime));
					currentProcess = q2.poll();
					startTime = time;
				}
			}
			else if(currentQueue == 3){
				if(currentProcess.getElapsedTime() >= currentProcess.getBurstTime()){
					// TOD: save in the output array
					endTime = time;
					output3.add(new Box(currentProcess, startTime, endTime));
					currentProcess = q3.poll();
					startTime = time;
				}
			}

			if(currentProcess != null){
				currentProcess.incrementElapsedTime();
				
			}
		}
	}
	
	public void displayTimeline(){
		System.out.print("\n\n\n");
		for(Box b: output1){
			System.out.println(b.p.getName() + " " + b.startTime + " " + b.endTime);
		}
		System.out.print("\n\n\n");
		for(Box b: output2){
			System.out.println(b.p.getName() + " " + b.startTime + " " + b.endTime);
		}
		System.out.print("\n\n\n");
		for(Box b: output3){
			System.out.println(b.p.getName() + " " + b.startTime + " " + b.endTime);
		}
	}

}


// if(!arrivalTimeStack1.isEmpty() && !arrivalTimeStack2.isEmpty() && !arrivalTimeStack3.isEmpty()){
// 	if(!arrivalTimeStack1.isEmpty()){
// 		currentProcess = arrivalTimeStack1.peek();
// 		currentQueue = 1;
// 	}
	
// 	else if(!arrivalTimeStack2.isEmpty()){
// 		currentProcess = arrivalTimeStack2.peek();
// 		currentQueue = 2;
// 	}
	
// 	else{
// 		currentProcess = arrivalTimeStack3.peek();
// 		currentQueue = 3;
// 	}
	
// 	if(currentQueue == 1){
// 		if(nextProcess != null && nextProcess.getArrivalTime() == time){
// 			priorityQueue.add(arrivalTimeStack1.pop());
// 		}
		
// 		if(currentProcess != null && (currentProcess.getElapsedTime() >= currentProcess.getBurstTime() || time % quantum == 0)){
// 			endTime = time;
// 			output1.add(new Box(currentProcess, startTime, endTime));
// 			Process tmp = currentProcess;
// 			if(!priorityQueue.isEmpty()){
// 				currentProcess = priorityQueue.poll();
// 			}
// 			if(currentProcess.getElapsedTime() < currentProcess.getBurstTime()){
// 				priorityQueue.add(tmp);
// 			}
// 			startTime = time;
// 		}
// 		if(currentProcess != null){
// 			currentProcess.incrementElapsedTime();
// 		}
// 	}	
	
// 	if(currentQueue == 2 || currentQueue == 3){
// 		if(currentQueue == 3)
// 			arrivalTimeStack2.add(arrivalTimeStack3.pop());
		
// 		if(nextProcess != null && nextProcess.getArrivalTime() == time){
// 			if(nextProcess.getPriority() < currentProcess.getPriority()){
// 				// TODO: save current process end time and remaining burst time
// 				priorityQueue.add(currentProcess);
// 				endTime = time;
// 				if(currentQueue == 2)
// 					output2.add(new Box(currentProcess, startTime, endTime));
				
// 				else
// 					output3.add(new Box(currentProcess, startTime, endTime));
				
// 				currentProcess = arrivalTimeStack2.pop();
// 				startTime = time;
// 			}
// 			else{
// 				priorityQueue.add(arrivalTimeStack2.pop());
// 			}
// 		}
		
// 		if(currentProcess.getElapsedTime() >= currentProcess.getBurstTime()){
// 			// TOD: save in the output array
// 			endTime = time;
// 			if(currentQueue == 2)
// 				output2.add(new Box(currentProcess, startTime, endTime));
			
// 			else
// 				output3.add(new Box(currentProcess, startTime, endTime));
			
// 			currentProcess = priorityQueue.poll();
// 			startTime = time;
// 		}
		
// 		if(currentProcess != null){
// 			currentProcess.incrementElapsedTime();
// 		}
// 	}
// }

// else {
// 	nextProcess = null;
// }
