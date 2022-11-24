package graphs;
import java.util.*;


/** 
 *  Class for handling weighted graphs represented by numerical Nodes, each one with a list of linked Nodes.
 * @author José
 *
 */
public class Graph{
	/**
	 * Graphs nodes
	 */
	private Node[] nodes;
	
	/**
	 * Exception thrown when trying to access a non existing node
	 * @author José
	 *
	 */
	public class NoSuchNodeException extends Exception{		

		private static final long serialVersionUID = -2185481871526802408L;

		/**
		 * Default constructor 
		 * @param x Node trying to be accessed
		 */
	    public NoSuchNodeException(String x) 
	    {
	    	super(x);
	    }
	}
	/**
	 * Exception thrown when trying to create an already existing link 
	 * @author José
	 *
	 */
	public class ExistingLinkException extends Exception{

		private static final long serialVersionUID = -631523123111692329L;
		
		/**
		 * Default constructor
		 * @param x Lunk tryng to be created
		 */
		public ExistingLinkException(String x)
		{
			super(x);
		}
		
	}
	
	/**
	 * Default constructor, for graph initialization
	 * @param _nNodes Number of nodes in the graph
	 */
	public Graph(int _nNodes)
	{
		nodes=new Node[_nNodes];
	}

	/**
	 * Connects a pair of nodes present in the graph
	 * @param _1 Numerical identifier of first node
	 * @param _2 Numerical identifier of the second node
	 * @param _w Weight of the connection
	 * @throws NoSuchNodeException Warns if one of the nodes does not belong to the graph
	 * @throws ExistingLinkException warns if the connection already exists
	 */
	public void connect(int _1, int _2, float _w ) throws NoSuchNodeException, ExistingLinkException
	{
		//If the nodes do not belong to the graph
		if(_1>nodes.length || _1<0 )
			throw new NoSuchNodeException(Integer.toString(_1));
		if(_2>nodes.length || _2<0)
			throw new NoSuchNodeException(Integer.toString(_2));
		
		Node no1;
		Node no2;
		//If the nodes do not exist, create new ones
		if(nodes[_1-1] == null)
			nodes[_1-1] = new Node(_1);
		if(nodes[_2-1]==null)
			nodes[_2-1] = new Node(_2);
		//Returns the existing connections of the the nodes trying to connect
		no1 = nodes[_1-1];
		no2 = nodes[_2-1];
		LinkedList<Link> adj1 = nodes[_1-1].getAdj();
		LinkedList<Link> adj2 = nodes[_2-1].getAdj();
		//Checks i the two nodes are already connected, if so throws ExistingLinkException
		for(int k=0; k<adj1.size(); k++)
			for(int v=0; v<adj2.size(); v++)
			{
				Node one = adj1.get(k).getNode();
				Node two =adj2.get(v).getNode();
				
				if( (one!=null && two !=null) && ( one.equals(nodes[_2-1]) || two.equals(nodes[_1-1]) ) )
				{
					throw new ExistingLinkException(Integer.toString(_1)+Integer.toString(_2));
				}
			}
		
		//If they are not connected, connect both nodes to each other
		nodes[_1-1].newAdj(no2, _w);
		nodes[_2-1].newAdj(no1, _w);			
	}
	
	/**
	 * Returns the node with the specified id (number)
	 * @param _id Node identifier (Node's number)
	 * @return The node
	 * @throws NoSuchNodeException Warns if trying to access unnexisting node
	 */
	public Node getNode(int _id) throws NoSuchNodeException
	{
		if(_id > this.nodes.length || _id<0)
			throw new NoSuchNodeException(Integer.toString(_id));
		
		return nodes[_id-1];
	}
	
	/**
	 * Returns the number of nodes in the graph
	 * @return Number of nodes in the graph
	 */
	public int getSize()
	{
		return nodes.length;
	}
	
	/** 
	 * Returns the total weight of the graph's connections
	 * @return Sum of all the connections' weight
	 */
	public float getWeight()
	{
		float sum = 0;
		//sums all the connections of a single node
		for(int k=0; k<this.nodes.length; k++)
		{
			LinkedList<Link> links = nodes[k].getAdj();
			
			for(int v=0; v<links.size(); v++)
			{
				Link link = links.get(v);
				sum += link.getWeight();
			}
		}
		//Since the graph is bidirectional, each link gets summed twice
		return sum/2;
	}
	
	/**
	 * Returns the textual description of the graph
	 */
	@Override
	public String toString() {
		String str = new String ("Graph:\n");
		Node temp;
		
		for(int k=0; k< nodes.length; k++)
		{
			temp= nodes[k];
			
			str = str + temp.toString();
		}
		
		return str;
	}
	
	
}