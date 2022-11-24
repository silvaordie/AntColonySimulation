package events;

import ants.*;

/**
 * Event responsible for printing in the terminal the simulation's information
 * @author José
 *
 */
public class EvPrint implements Event,Comparable<Event>{
	
	/**
	 *  Time at which information will be displayed
	 */
	private float time=0;
	/**
	 * Observation number
	 */
	private int obs=0;
	/**
	 * Default constructor, receives the time to display the information and the observation number
	 * @param _t Print instant
	 * @param _ob Observation number
	 */
	public EvPrint(float _t, int _ob)
	{
		this.time = _t;
		this.obs = _ob;
	}
	/**
	 * Prints the simulation's information to the terminal
	 */
	public Event[] simulate() 
	{
		AntInterface ant = new Ant();
		System.out.println("\nObservation " + this.obs + ":");
		System.out.println("		Present instant: " + this.time);
		System.out.println("		Number of move events: " + EvAntMove.getCount());
		System.out.println("		Number of evaporation events: " + EvPhEvaporation.getCount());
		if(ant.getWeight()<999999999)
			System.out.println("		Hamiltonian cycle: " + ant.toString());
		
		return null;	
	}
	/**
	 * Returns the time information will be displayed
	 */
	public float getTime()
	{
		return this.time;
	}
	/**
	 * Compares the final instant of two events
	 */
	@Override
	public int compareTo(Event event)
	{
		if(this.time<event.getTime())
			return 1 ;
		else return 0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(time);
		return result;
	}
	/** 
	 * Check if two events are equal (Always false for EvPrint events)
	 */
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	
	

}