import java.util.*;
import java.util.Scanner;


public class RR
{
    ArrayList<Process> processStack = new ArrayList<>(); //sorted by arrival time
    ArrayList<Process> output = new ArrayList<>(); 
   // PriorityQueue priorityList = new PriorityQueue<>(); //sorted aaccording to priority (default)
    PriorityQueue waitingList = new PriorityQueue<>();  //FCFS priority
    int totalBurstTime=0;
    int timeQuantum = 0;

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

        Iterator processStackIterator = processStack.iterator();
        Iterator waitingListIterator = waitingList.iterator();
        //Iterator priorityList = priorityList.iterator();

        for(int i=0;i<=totalBurstTime;i++)
        {   
            timeCounter = 0; //timeCounter is reseted to 0 after expiry of time quantum
            for(timeCounter<=timeQuantum) //execute current process within time quantum
            {

                if(currentProcess.getElapsedTime()>=currentProcess.getBurstTime()) //if process has finished executing
                {
                    //remove process from output
                }
                else
                {
                    //increase elapsed time for current process
                }
                
                timeCounter++;
            }
            else //after expiry of time quantum
            {
                //save current process elapsed time
                //reset current process bursttime
                //reset current process elapsed time to 0
                //preempt current process and add into waiting list

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


            }
        }
        

    }
}