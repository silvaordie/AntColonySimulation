package ants;

import java.util.LinkedList;

import graphs.Link;
import graphs.Node;
/**
 * Interface that defines a ant's behavior
 * @author José
 *
 */
public interface AntInterface{
	/**
	 * Returns the ant's next edge to move
	 * @return Edge the ant should move to
	 */
	public abstract Link getMove();
	/**
	 * Move's the ant to the next node through the given link, checks if a Hamiltonian cycle was found during the move
	 * @param link Link the ant should move through
	 * @return True if a Hamiltonian cycle was found
	 */
	public abstract boolean moveAnt(Link link);
	/**
	 * Returns a path traversed
	 * @return Path traversed
	 */
	public abstract LinkedList<Link> getCycle();
	/**
	 * Returns the ant's initial node
	 * @return Ant's initial node
	 */
	public abstract Node getFirst();
	/**
	 * Returns a path's value
	 * @return Value characterizing a path
	 */
	public abstract float getWeight();
}
