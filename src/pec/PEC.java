
package pec;
import events.*;

import java.util.*;

/**
 * Responsible for stochastic event handling
 * @author José
 *
 */
public class PEC {
	/**
	 * Pending events, sorted by final instant
	 */
	private LinkedList<Event> events = new LinkedList<Event>();
	/**
	 * Simulation's final time
	 */
	private float finalinst;
	/**
	 * Default constructor, receives the simulation's final instant 
	 * @param _finalinst Simulation's final instant
	 */
	public PEC(float _finalinst)
	{
		this.finalinst = _finalinst;
	}
	
	/**
	 * Adds a new event do the to event queue, where the first event in queue is the one with a lower time value
	 * @param _events Events to add to the queue
	 */
	public void addEvPEC(Event[] _events)
	{
		//For all the existing events to simulate
		for(int v=0; _events!=null && v<_events.length; v++)
		{
			boolean found = false;
			//Checks if the event already exists in the queue
			for(int k=0; k<this.events.size(); k++)
				if(events.get(k).equals(_events[v]) || _events[v].getTime()>this.finalinst)
					found = true;
			//If the event doesn't exist, adds it to the queue
			if(!found)
				this.events.add(_events[v]);
		}
		//Sorts the pending events
		Collections.sort(this.events, new Comparator<Event>(){
			@Override
			public int compare(Event event2, Event event1){
				return Float.floatToIntBits(event2.getTime()-event1.getTime());
			}});
	}
	
	/**
	 * Fetches the next event to simulate by removing it from the queue
	 * @return Next event to simulate
	 */
	public Event nextEvPEC()
	{
		try
		{
			return this.events.removeFirst();
		}catch(NoSuchElementException e)
		{
			System.exit(-2);
			return null;
		}
	}
}
