
@SuppressWarnings("unchecked")
class Job
{
        public String jobName;
        public int arrivalTime;
        public int startTime;
        public int runTime;
        public int waitTime;
        public int turnTime;

	public Job()
	{
		jobName = "";
		arrivalTime = 0;  startTime = 0;  runTime = 0;
		waitTime = 0;  turnTime = 0;
	}

	public Job (String jobName, int arrivalTime, int runTime)
	{
		this.jobName = jobName;
		this.arrivalTime = arrivalTime;
		this.runTime = runTime;
		this.startTime = -1;
	}

	public String toString()
	{
		return (jobName + "\t" + 
	                arrivalTime + "\t" +
			startTime + "\t" +
	                runTime + "\t" + 
                        waitTime + "\t" +
                        turnTime);
	}
	//get method for job name
	public String getJobName(){
	return jobName; } //returns job name

	//get method for arrival time
	public int getArrivalTime(){
	return arrivalTime;//returns arrival time
	}
	//get method for start time 
	public int getStartTime(){
	return startTime;//return start time
	}
	//get method for run time 
	public int getRunTime(){
	return runTime;//return run time 
	}
	//get method for wait time 
	public int getWaitTime(){
	return waitTime;//return wait time 
	}
	//get method for turn time 
	public int getTurnTime(){
	return turnTime;//return turn time 
	}	
	//set method for job name
	public void setJobName(String name){
	this.jobName = name;//change job name
	}
	//set method for arrival time 
	public void setArrivalTime(int arrival){
	this.arrivalTime = arrival;//change arrival time
	}		
	//set method for start time 
	public void setStartTime(int start){
	this.startTime = start;//change start time 
	}
	//set method for run time
	public void setRunTime(int run){
	this.runTime = run;//change run time
	}
	//set method for wait time
	public void setWaitTime(int wait){
	this.waitTime = wait; //change wait time
	}
	//set method for turn time 
	public void setTurnTime(int turn){
	this.turnTime = turn;//change turn time 
	}
}
