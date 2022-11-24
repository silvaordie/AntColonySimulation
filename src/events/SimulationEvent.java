package events;

import java.util.*;

/**
 * Represents an event
 * @author José
 *
 */
public abstract class SimulationEvent implements Event,Comparable<Event>{
	
	protected float time;
	protected static int mevents = 0;
	protected static int eevents = 0;
	static float rho;
	static float eta;
	static float alfa;
	static float beta;
	static float delta;
	/**
	 * Default constructor, sets the Event's end time
	 * @param _time
	 */
	protected SimulationEvent(float _time)
	{
		this.time=_time;
	}
	/**
	 * Abstract method for event simulation
	 */
	public abstract Event[] simulate();
	
	/**
	 * Sets the simulations parameters
	 * @param _alpha Alpha parameter
	 * @param _beta Beta parameter
	 * @param _delta Delta parameter
	 * @param _rho Rho parameter
	 * @param _eta Eta parameter
	 */
	public static void setParams(float _alpha, float _beta, float _delta, float _rho, float _eta)
	{
		alfa = _alpha;
		beta = _beta;
		delta = _delta;
		rho = _rho;
		eta = _eta;
	}
	/**
	 * Returns the Event's end time
	 * @return Event's final instant
	 */
	public float getTime()
	{
		return this.time;
	}
	/**
	 * Returns a event's final time based on it's average duration
	 * @param m Average event duration
	 * @return Event duration
	 */
	public static float expRandom(float m)
	{
		Random random = new Random();
		float next = random.nextFloat();
		return (float)(-m*Math.log(1.0-next));
	}	
	/**
	 * Compares two events
	 */
	@Override
	public int compareTo(Event event)
	{
		if(this.time<event.getTime() )
			return 1;
		else 
			return 0;
	}
}
