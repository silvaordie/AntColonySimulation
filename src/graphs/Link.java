package graphs;
import java.util.*;
/**
 * Representation of the Graphs bidirectional edges with respective weight, pheromone level and the connection's end node
 * @author José
 *
 */
public class Link {
	private float weight;
	private float pheromone=0;
	Node node;
	
	/**
	 * Default constructor
	 * @param _n Connection's end node
	 * @param _w Weight of the connection
	 */
	Link( Node _n , float _w )
	{
		this.weight = _w;
		this.node = _n;
	}	
	
	/**
	 * Updates the edge's pheromone level in both directions 
	 * @param _value How much the pheromone level will increase
	 * @param no Connection's initial node
	 */
	public void updatePh(float _value, Node no)
	{
		this.pheromone += _value;
		if(this.pheromone<0)
			this.pheromone = 0;
		
		LinkedList<Link> adj = node.getAdj();
		Link temp;
		
		for(int k=0; k<adj.size(); k++)
		{	
			temp = adj.get(k);
			
			if(temp.node.equals(no))
			{ 
				temp.pheromone += _value;
				if(temp.pheromone < 0)
					temp.pheromone = 0;
				
				return;
			}
		}
	}
	
	/**
	 * Returns the connection's end Node
	 * @return Connection's start node
	 */
	public Node getNode()
	{
		return this.node;
	}
	
	/**
	 * Evaporates the edge's pheromones (decreases the pheromone level)
	 * @param _value How much the pheromone level will decrease
	 * @param no Connection's start node
	 */
	public void evaporatePh(float _value, Node no)
	{
		this.pheromone -= _value;
		if(this.pheromone<0)
			this.pheromone = 0;
		
		LinkedList<Link> adj = node.getAdj();
		Link temp;
		
		for(int k=0; k<adj.size(); k++)
		{	
			temp = adj.get(k);
			
			if(temp.node.equals(no))
			{ 
				temp.pheromone -= _value;
				if(temp.pheromone < 0)
					temp.pheromone = 0;
				
				return;
			}
		}
	}
	
	/**
	 * Returns the connection's pheromone level
	 * @return Connection's pheromone level
	 */
	public float getPh()
	{
		return this.pheromone;
	}
	
	/**
	 * Returns the connection's weight
	 * @return Connection's weight
	 */
	public float getWeight()
	{
		return this.weight;
	}
	
	@Override
	public String toString() {
		return "[" + Integer.toString(node.getId()) + ", " + Float.toString(weight) + ", "  + Float.toString(pheromone) + "]  ";
	}	
}
