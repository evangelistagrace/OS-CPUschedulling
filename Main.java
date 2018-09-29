import java.util.*;

public class Main
{

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int numOfProcesses;
        String name;
        int arrivalTime;
        int burstTime;
        int priority;
        int timeQuantum = 0;
        
        ArrayList<Process> process = new ArrayList<>();
        FCFSprio FCFS = new FCFSprio();
		RRprio RR = new RRprio();
        SRTNprio SRTN = new SRTNprio();
        ThreeLvlQ TLQ = new ThreeLvlQ();
        
        System.out.println("------CPU Scheduling Algorithms Simulation-----");
        System.out.println();
        System.out.println("Enter number of processes[3-10]: ");
        numOfProcesses = input.nextInt();
        System.out.println("\nEnter quantum: ");
        timeQuantum = input.nextInt();
        for(int i=0; i<numOfProcesses; i++)
        {
            name = "P" + i;
            System.out.println("Process P" + i);
            System.out.println();
            System.out.print("Enter arrival time: ");
            arrivalTime = input.nextInt();
            System.out.println();
            System.out.print("Enter burst time: ");
            burstTime = input.nextInt();
            System.out.println();
            System.out.print("Enter priority[1-6]: ");
            priority = input.nextInt();
            

            // if((priority==1)||(priority==2))
            // {
            //     System.out.println();
            //     System.out.print("Enter time quantum: ");
            //     timeQuantum = input.nextInt();
            // }
            
            // if(timeQuantum != 0)
            // {
            //     process.add(new Process(name, arrivalTime, burstTime, priority, timeQuantum));
            // }
            // else
            // {
            //     process.add(new Process(name, arrivalTime, burstTime, priority));
            // }
            process.add(new Process(name, arrivalTime, burstTime, priority));
            System.out.println();
            System.out.println();
        }

		// process.add(new Process("P0", 0, 6, 3));
		// process.add(new Process("P1", 1, 4, 3));
		// process.add(new Process("P2", 5, 6, 1));
        // process.add(new Process("P3", 6, 6, 1));
        // process.add(new Process("P4", 7, 6, 5));
		// process.add(new Process("P5", 8, 6, 6));
        
        System.out.println();
        System.out.println();
        
        printTable(numOfProcesses, process);
        
    
		//FCFS
		FCFS.runFCFS(process);
		FCFS.displayTimeline();

		// RR
		RR.runRR(process, timeQuantum);
		RR.displayTimeline();
        
		//3-LEVEL QUEUE
		// ThreeLvlQ.runThreeLvlQ(process);
		// ThreeLvlQ.displayTimeline();
        
		//SRTN
		SRTN.runSRTN(process);
        SRTN.displayTimeline();
        
        //TLQ
        TLQ.runThreeLvlQ(process, timeQuantum);
        TLQ.displayTimeline();

        

        input.close();
    }
    
    //print table for all processes
    public static void printTable(int numOfProcesses, ArrayList<Process> process)
    {
        
        System.out.println("+----------------+----------------+----------------+----------------+");
        System.out.println("|Process         |Burst Time      |Arrival Time    |Priority        |");
        System.out.println("+----------------+----------------+----------------+----------------+");
        for(int i=0;i<numOfProcesses;i++)
        {
        //System.out.println("+----------------+----------------+----------------+----------------+");
          System.out.println("|" + process.get(i).getName() + "              |"  + process.get(i).getBurstTime() + "               |" + process.get(i).getArrivalTime() + "               |" + process.get(i).getPriority() + "               |");
          System.out.println("+----------------+----------------+----------------+----------------+");
          
            
        }
    
    }
}