package graphs;
import java.util.*;

/**
 * Represents a bidirectional weighted graph's nodes, which are represented by a numerical value
 * @author José
 *
 */
public class Node{
		
		/**
		 * Node's numeric value
		 */
		private int  id;
		/**
		 * List of the Node's links
		 */
		private LinkedList<Link> adj = new LinkedList<Link>();
		
		/**
		 * Default constructor
		 * @param _id Node's numerical indicator 
		 */
		Node(int _id)
		{
			this.id = _id;
		}
		/**
		 * Creates a link Link connecting the Node(start) to another (end Node)
		 * @param _n Connection's end Node
		 * @param _w Weight of the connection
		 */
		public void newAdj(Node _n, float _w)
		{
			adj.add( new Link( _n , _w ) );
		}
		/**
		 * Returns all the Node's Links
		 * @return LinkedList of Links connections between the node and connected ones
		 */
		public LinkedList<Link> getAdj()
		{
			return this.adj;
		}
		/**
		 * Returns the Node's numerical indicator
		 * @return Node's numerical indicator
		 */
		public int getId()
		{
			return this.id;
		}
		
		/**
		 * Returns the Node's textual description
		 */
		@Override
		public String toString() {
			String str = new String("Node " + Integer.toString(this.id) +  ": {");
			Link temp;
			
			for(int k=0; k<adj.size(); k++)
			{
				temp = adj.get(k);
				str= str + temp.toString(); 
			}	
			str=str + "}\n";
			
			return str;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}
		/**
		 * Checks if two nodes are equal
		 */
		@Override
		public boolean equals(Object obj) {

			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (id == other.id)
				return true;
			else
				return false;
		}
		
}
