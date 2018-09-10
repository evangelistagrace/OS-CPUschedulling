import java.util.*;

public class Main
{

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int numOfProcesses;
        int[] arrivalTime = new int[10];
        int[] burstTime = new int[10];
        int[] priority = new int[10];
        int[] timeQuantum = new int[10];
        
        System.out.println("------CPU Scheduling Algorithms Simulation-----");
        System.out.println();
        System.out.println("Enter number of processes: ");
        numOfProcesses = input.nextInt();
        System.out.println();
        for(int i=0; i<numOfProcesses; i++)
        {
            System.out.println("Process P" + i);
            System.out.println();
            System.out.print("Enter arrival time: ");
            arrivalTime[i] = input.nextInt();
            System.out.println();
            System.out.print("Enter burst time: ");
            burstTime[i] = input.nextInt();
            System.out.println();
            System.out.print("Enter priority: ");
            priority[i] = input.nextInt();
            
            if((priority[i]==1)||(priority[i]==2))
            {
                System.out.println();
                System.out.print("Enter time quantum: ");
                timeQuantum[i] = input.nextInt();
            }

            System.out.println();
            System.out.println();
        }
        
        System.out.println();
        System.out.println();
        
        printTable(numOfProcesses, burstTime, arrivalTime, priority);
    }
    
    public static void printTable(int numOfProcesses, int[] burstTime, int[] arrivalTime, int[] priority)
    {
        
        System.out.println("+----------------+----------------+----------------+----------------+");
        System.out.println("|Process         |Burst Time      |Arrival Time    |Priority        |");
        System.out.println("+----------------+----------------+----------------+----------------+");
        for(int i=0;i<numOfProcesses;i++)
        {
        //System.out.println("+----------------+----------------+----------------+----------------+");
          System.out.println("|P" + i + "              |"  + burstTime[i] + "               |" + arrivalTime[i] + "               |" + priority[i] + "               |");
          System.out.println("+----------------+----------------+----------------+----------------+");
          
            
        }
    
    }
}