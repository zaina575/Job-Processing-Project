import java.util.*;
import java.io.*;

public class TimeShare {


public static void main(String [] args) throws IOException,CloneNotSupportedException{
	Queue inputQ = new Queue(); //Queue object for input
	Queue activeQ = new Queue(); //Queue object for active jobs
	Queue finalQ = new Queue(); //Queue object for final calculations

	start(args,inputQ,activeQ,finalQ);//call to start method that adds data to the inputQ queue from a valid file 
}

//Precondition: The inputQ queue should not have any values stored in it.
//Postcondition: The inputQ queue will be initialized with a valid external file sent at the command line.
//method that adds data to the inputQ queue from a valid external file
public static void start(String [] args, Queue inputQ, Queue activeQ, Queue finalQ) throws CloneNotSupportedException,IOException{
  try{  //checks to see if it was run with a command line argument  
	if(args.length <1){
                System.out.println("You have made an error. Please use the command line to input a .dat file.");//error message
                System.exit(1);//exits the whole program
                }

                Scanner in = new Scanner(new FileInputStream(args[0])); //scanner that reads the file at index zero of the args array
            
		while(in.hasNext()){ //will keep reading the file till the end
                String jobname = in.next();//stores the jobname
                int arrival = in.nextInt();//stores the arrival time
                int run = in.nextInt();//stores the run time

                Job new1 = new Job(jobname,arrival,run); //constructor to create a Job object by passing in the variables

                inputQ.enqueue(new1); //adding the new job object to the inputQ queue

                }
		 processjobs(inputQ,activeQ,finalQ); //call to processjobs method passing in the three queues once a valid external
		 //file used

           }
	//if user does not input a valid file name at the command line error message will be thrown and will exit the whole program
        catch (IOException e){
                System.out.println(e.getMessage());
        }
}
//Precondition: The inputQ queue will be initialized with a valid external file sent at the command line and activeQ will be empty.
//Postcondition: Every job in inputQ will be processed one by one. THe method will keep running until both inputQ and activeQ are
//both empty.
public static void processjobs(Queue inputQ, Queue activeQ, Queue finalQ) throws CloneNotSupportedException{
int clock = 1; //integer that represents the clock
int idle = 0; //integer to store the time that the CPU was idle
int usage = 0; //integer to store the time that the CPU was used
Job nextJob; //Job variable to store the nextJob in the inputQ
Job currentJob; //Job variable to store the currentJob in the activeQ
//do while loop that runs until both inputQ and activeQ are empty
do{
//if the input Q is not empty, run this
if (! inputQ.isEmpty() )
 {
                nextJob = (Job)inputQ.front(); //gets the front job of the inputQ queue
                if (nextJob.getArrivalTime() == clock) //if the front job's arrival time equals clock 
                {
                       activeQ.enqueue(nextJob); //add job to the activeQ queue
                        inputQ.dequeue();//remove job from inputQ queue
                } 
 }
//if the activeQ is not empty, run this
if(!activeQ.isEmpty()){
		 currentJob =(Job)activeQ.front(); //gets the front job of the activeQ queue
//if currentJob's run time equals the clock minus the currentJob's start time and the currentJob has been processing then run this
		 if(currentJob.getRunTime() == (clock-currentJob.getStartTime())&& (currentJob.getStartTime() != -1)){
			currentJob.setTurnTime(currentJob.getRunTime() + currentJob.getWaitTime());//update turnaround time
			finalQ.enqueue(currentJob);//add the currentJob to the final queue
			activeQ.dequeue();//remove job from activeQ queue
		}}
//if activeQ is not empty, run this
if(!activeQ.isEmpty()){
	        currentJob =(Job) activeQ.front(); //gets the front job of the activeQ queue
//if currentJob's arrival time is less than clock and the currentJob has not been processed then run this
		if((currentJob.getArrivalTime() <= clock)&& (currentJob.getStartTime() == -1)){
			currentJob.setStartTime(clock); //change the startTime to the clock time
			currentJob.setWaitTime(currentJob.getStartTime() - currentJob.getArrivalTime()); //change the wait time
	}}
//if activeQ is empty and the inputQ is not empty run this
if((activeQ.isEmpty())&&(!inputQ.isEmpty())){
idle++;}//increment idle variable
//if activeQ is not empty
if((!activeQ.isEmpty())){
usage++;}//increment the usage variable

clock++;} while(!inputQ.isEmpty() || (!activeQ.isEmpty()));

print(finalQ, idle, usage);//calling the print method after all jobs have been processed
}

//Precondition: The finalQ queue will be intialized after all the jobs are processed.
//Postcondition: It will print out the summary report of the jobs that were processed.
public static void print(Queue finalQ, int idle, int usage) throws CloneNotSupportedException{
       Queue copyQ = (Queue) finalQ.clone();   // Clone the queue for printing
       //print summary report
       System.out.println("\t"+"Job Control Analysis : Summary Report");
       System.out.println("\t"+"-------------------------------------");
       System.out.println("\t"+"job id" + "\t" + "arrival" + "\t" + "start" + "\t" + "run" + "\t" + "wait" + "\t" + "turnaround");
       System.out.println("\t"+"      " + "\t" + "time" + "\t" + "time" + "\t" + "time" + "\t" + "time" + "\t" + "time");
       System.out.println("\t"+"------" + "\t" + "-------"+ "\t"+ "-----" + "\t" + "----" + "\t" + "----" + "\t" + "----------");
        while (! copyQ.isEmpty()) // Print the queue

        {

                Job outputJob = (Job) copyQ.front();   // Pull off front job

                System.out.println("\t" + outputJob);  // Print the job using the toString in the Job class

                copyQ.dequeue();  // Remove the front job

        }
	System.out.println("\t"+"Average Wait Time => " + twoDecimals(calcAverageWait(finalQ))); //prints out average wait time
	System.out.println("\t"+"        CPU Usage => " + twoDecimals((usage))); //prints CPU Usage time
	System.out.println("\t"+"         CPU Idle => "  + twoDecimals((idle))); //prnints CPU Idle time
	System.out.println("\t"+"    CPU Usage (%) => " + twoDecimals(calcCPUUsage(usage,idle))+ "%"); //prints the CPUUsage as a %
}
//Precondition: The finalQ queue will be intialized after all jobs are processed.
//Postcondition: It will compute the average wait time of all jobs in the finalQ queue.
public static double calcAverageWait(Queue finalQ) throws CloneNotSupportedException{
	Queue copyQ = (Queue) finalQ.clone();//Clone the queue for calculating the average wait time
	double average = 0;//double variable that stores the average wait time
	double sum  = 0;//sum of all the wait times
	double count = 0;//stores the number of jobs in the queue
	//run until the queue is empty
	while(! copyQ.isEmpty()){
	Job output = (Job) copyQ.front();//get the front job
	sum+=output.getWaitTime();//add the wait time of the front job to sum variable
	count++;//increment count
	copyQ.dequeue();//remove front job
}
	average = sum/count;//calculate the average
	return average;//return the average wait time
}
//Precondition: All the jobs that were in the original inputQ were all processed and the usage and idle variables are stored from
//when all jobs were processed.
//Precondition: The cpu usage as a percentage will be calculated.
public static double calcCPUUsage(double usage, double idle){
	double cpuUsage = (usage/(usage + idle))*100;//calculates the cpu usage as a percentage
	return cpuUsage;//returns CPU usage

}
//Precondition: A valid double value is sent as a parameter.
//Postcondition: It will send back a string that converts the value to only two decimal places.
public static String twoDecimals(double amount) {
    long roundedAmount = Math.round(amount * 100);//rounded amount for the variable
    long before = roundedAmount / 100;//before the decimal place number
    long after = roundedAmount % 100;//after the decimal place number
//if after is less than or equal to 9 run this, else run the other statement
    if (after <= 9)
      return before + ".0" + after;
    else
      return before + "." + after;
  }
}

