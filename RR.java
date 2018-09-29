import java.util.*;
import java.util.Scanner;


public class RR
{
    Stack<Process> processStack = new Stack<>(); //sorted by arrival time
    ArrayList<Box> output = new ArrayList<>(); 
   // PriorityQueue priorityList = new PriorityQueue<>(); //sorted aaccording to priority (default)
    PriorityQueue<Process> waitingList = new PriorityQueue<>(new WaitTimeComparator());  //FCFS priority
    int totalBurstTime=0;
    int timeQuantum = 0;
    String title = "RR Preemptive Scheduling: ";
    public RR(){}

    public void runRR(ArrayList<Process> p, int timeQuantum)
    {
        this.timeQuantum = timeQuantum;

        for(int i=0;i<p.size();i++)
        {
            processStack.add(p.get(i));
            totalBurstTime += p.get(i).getBurstTime();
        }

        Collections.sort(processStack, new ArrivalTimeComparator()); //sort incoming processes according to arrival time

        int startTime = 0;
        int endTime = 0;
        int timeCounter = 0;
		Process currentProcess = processStack.pop();
        Process nextProcess = new Process();

        Iterator<Process> processStackIterator = processStack.iterator();
        Iterator<Process> waitingListIterator = waitingList.iterator();
        //Iterator priorityList = priorityList.iterator();



        for(int time=0;time<=totalBurstTime;time++)
        {   
            timeCounter = 0; //timeCounter is reseted to 0 after expiry of time quantum
            while(timeCounter <= timeQuantum) //execute current process within time quantum
            {

                if(currentProcess.getElapsedTime()>=currentProcess.getBurstTime()) //if process has finished executing
                {
                    //add process to output
                    endTime = time;
                    output.add(new Box(currentProcess, startTime, endTime));
                    startTime = time;
                }
                else
                {
                    //increase elapsed time for current process
                    currentProcess.incrementElapsedTime();
                    timeCounter++;
                }
                

            }
            //select new process after expiry of time quantum
    
            //save current process elapsed time
            //reset current process bursttime
            //reset current process elapsed time to 0
            //preempt current process and add into waiting list

            currentProcess.resetBurstTime(currentProcess.getElapsedTime());
            currentProcess.setElapsedTime(0);
            waitingList.add(currentProcess);
            endTime = time;
            output.add(new Box(currentProcess, startTime, endTime));
            startTime = time;

            //elect new process

            //based on current time
            //compare processes in process list that have yet to be executed
            //with processes in waiting list
            
            
            //if processlist.next.getarrivaltime == currenttime
                //iterate waiting list
                //if waitinglist.next .getPriority <= processlist.next.getpriority
                    //waitinglist.next.setwwaitingtime = 0; //reset waiting time of elected process
                    //nextprocess = waitinglist.poll();
                    //waitinglist.add(processlist.next);
                    //increase waiting time for remainging processes in waitinglist

                //else if waitinglist.next .getPriority >  processlist.next.getpriority
                    //nextprocess = processlist.next
                    //waitingtime++ //increase waiting time for remainging processes in waitinglist
            //else
                //iterate waiting list
                //waitinglist.next.setwwaitingtime = 0; //reset waiting time of elected process
                //nextprocess = waitinglist.poll();
                //waitingtime++ //increase waiting time for remainging processes in waitinglist

            if(processStackIterator.hasNext())
            {
                System.out.println("hello");
                if(processStackIterator.next().getArrivalTime()==time)
                {
                    if(waitingListIterator.hasNext())
                    {
                        if(waitingListIterator.next().getPriority() <= processStackIterator.next().getPriority())
                        {
                            waitingListIterator.next().setWaitingTime(0); //reset waiting time for elected process
                            currentProcess = (Process)waitingList.poll();
                           
                            for(Process proc: waitingList)
                            {
                                proc.incrementWaitingTime();
                            }

                            waitingList.add(processStackIterator.next());
                            processStack.pop();

                        }
                        else if(processStackIterator.next().getPriority() < waitingListIterator.next().getPriority())
                        {
                            currentProcess = processStack.pop();
                            for(Process proc: waitingList)
                            {
                                proc.incrementWaitingTime();
                            }
                        }
                        
                        
                    }
                }
            }

            
        }
        

    }//end of RR

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