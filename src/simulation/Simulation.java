package simulation;

import java.util.*;

import ants.AntInterface;
import events.EvAntMove;
import events.EvPrint;
import events.Event;
import graphs.Link;
import pec.*;

/**
 * Class responsible for the simulation of the desired problem 
 * @author José
 *
 */
class Simulation implements SimulationInterface{
	
	/**
	 * Ant colony
	 */
	private AntInterface [] ants;
	/**
	 * Pending Event Container
	 */
	private PEC pec;
	
	/**
	 * Simulation's final instant
	 */
	private float finalinst;

	/**
	 * Default constructor, receives the ant colony and the simulation's inal instant
	 * @param _ants
	 * @param _finalinst
	 */
	Simulation(AntInterface[] _ants, float _finalinst)
	{
		this.ants = _ants;
		this.finalinst = _finalinst;
	}
	/**
	 * Runs the simulation
	 */
	public void run(String args[])
	{		
		this.pec = new PEC(finalinst);
		//Adds a event per colony ant concerning it's initial move
		Event[] events = new Event[ants.length];
		for(int k=0; k< ants.length ; k++)
		{
			Link move = ants[k].getMove();
			events[k]=new EvAntMove( EvAntMove.expRandom((move.getWeight())*Float.parseFloat(args[0])) , ants[k], move);
		}
		this.pec.addEvPEC(events);
		//Adds to the PEC all the display events
		events = new Event[Integer.parseInt(args[1])];
		float t=0;
		for(int k=0; k<events.length; k++)
			events[k] = new EvPrint( t+=finalinst/events.length , k+1) ;
		this.pec.addEvPEC(events);
		
		//Removes the first event to be simulated
		Event currentEvent = this.pec.nextEvPEC();
		float currenTime = 0;
		while(currenTime < finalinst)
		{
			//Current simulation time
			currenTime=currentEvent.getTime();
			//Adds to the pec, the events that need to be simulated next	
			this.pec.addEvPEC( currentEvent.simulate() );				
			//Prepares the next event to be simulated
			try {
			currentEvent = this.pec.nextEvPEC(); }
			catch(NoSuchElementException e) {
				System.exit(-5);		}			
		}
	}
	
}
