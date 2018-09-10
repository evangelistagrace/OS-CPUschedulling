import java.util.*;

public class Main
{
    static int[] priority = new int[10];
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int numOfProcesses;
        int[] arrivalTime = new int[10];
        int[] burstTime = new int[10];
        
        int[] timeQuantum = new int[10];
        
        System.out.println("------CPU Scheduling Algorithms Simulation-----");
        System.out.println("Enter number of processes: ");
        numOfProcesses = input.nextInt();
        
        for(int i=0; i<numOfProcesses; i++)
        {
            System.out.println("Process [" + i + "]");
            System.out.println();
            System.out.print("Enter arrival time: ");
            arrivalTime[i] = input.nextInt();
            System.out.println();
            System.out.print("Enter burst time: ");
            burstTime[i] = input.nextInt();
            System.out.println();
            System.out.print("Enter priority: ");
            priority[i] = input.nextInt();
            System.out.println();
            
            if((priority[i]==1)||(priority[i]==2))
            {
                System.out.println("Enter time quantum: ");
                timeQuantum[i] = input.nextInt();
            }

        }
        System.out.println();
        System.out.println();
        
        
    }
}