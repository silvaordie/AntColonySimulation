package events;

import java.util.LinkedList;

import ants.*;
import graphs.Link;

/**
 * Event that concerns a single Ant Move
 * @author José
 *
 */
public class EvAntMove extends SimulationEvent{
	
	private AntInterface ant;
	private boolean cycle = false;
	private Link next_node;
	
	/**
	 * Default constructor, receives the Event's end time, the Ant which is desired to move the link connecting to the Node the Ant should move to
	 * @param _time Event's end time
	 * @param _ant Event's Ant
	 * @param _next_node Link connecting to the Ant's next Node
	 */
	public EvAntMove(float _time, AntInterface _ant, Link _next_node)
	{
		super(_time);
		this.ant = _ant;
		this.next_node = _next_node;
	}
	
	/**
	 * Returns the number of Movement Events simulated
	 * @return Number of Movement event simulations
	 */
	public static int getCount()
	{
		return mevents;
	}
	/**
	 * Simulates the Ant Movement
	 */
	public SimulationEvent[] simulate()
	{
		mevents++;
		//move's the ant
		cycle = this.ant.moveAnt(next_node);
		
		LinkedList<SimulationEvent> events = new LinkedList<SimulationEvent>();
		//If a cycle was found, adds Evaporation Events to the edges of the cycle
		if(cycle)
		{
			LinkedList<Link> loop = this.ant.getCycle();
			Link link;
			Link aux;
			
			for(int k=0; k<loop.size(); k++)
			{
				link = loop.get(k);
				if(k==0)
					aux = loop.get(loop.size()-1);
				else
					aux = loop.get(k-1);
				
				events.add( new EvPhEvaporation(this.getTime() + expRandom(eta), link, aux.getNode()) )	;		
			}			
		}
		//Adds the another Ant move to the events to simulate
		Link move = this.ant.getMove();
		events.add( new EvAntMove(this.getTime() + expRandom(delta * move.getWeight()), this.ant, move ) );
	
		return events.toArray(new SimulationEvent[0]);
	}
	
	/**
	 * Checks if by moving, the Ant found an Hamiltonian cycle
	 * @return True if the Ant has found a Hamiltonian cycle by moving
	 */
	public boolean foundCycle()
	{
		return cycle;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ant == null) ? 0 : ant.hashCode());
		return result;
	}

	/**
	 * Checks if the event is equal to another one (Always false for EvAntMove)
	 */
	@Override
	public boolean equals(Object obj) {
		return false;
	}

	
}
