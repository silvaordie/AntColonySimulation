package simulation;

import java.io.File;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import graphs.*;
import ants.*;
import events.*;

/**
 * "Main" class, interprets the XML file containing the simulation parameters and enviornment and simulates.
 * @author José
 *
 */
public class Main extends DefaultHandler{

	//Auxiliary variables
	private static final String GRAPH = "graph";
	private static final String NODE = "node";
	private static final String WEIGHT = "weight";
	private static final String MOVE = "move";
	private static final String EVAPORATION = "evaporation";
	private static final String SIMULATION = "simulation";
	private static String read_string = "";

	private static int crr_node;
	private static int connecting_node;
	private static int nestNode;
		
	private static Graph graph;
	private static AntInterface [] ants;
	
	private static float finalinst;
	private static float plevel;
	
	private static float alpha;
	private static float beta;
	private static float delta;
	private static float eta;
	private static float rho;
	

	static float parseTag(String tag, Attributes atts)
	{	
		float ret=0;
		try{
			ret = Float.parseFloat(atts.getValue(atts.getIndex(tag)));
		}catch (NumberFormatException | NullPointerException nfe)
		{
			System.exit(-1);
		}
		if(ret<0)
			System.exit(-1);			
		
		return ret;
	}
	static int parseTagInt(String tag, Attributes atts)
	{	
		int ret=0;
		try{
			ret = Integer.parseInt(atts.getValue(atts.getIndex(tag)));
		}catch (NumberFormatException | NullPointerException nfe)
		{
			System.exit(-1);
		}		
		if(ret<0)
		{
			System.exit(-1);			
		}
		
		return ret;
	}	
	
	public void startElement(String uri, String name, String tag, Attributes atts)
	{
		if(tag.equals(GRAPH))
		{
			graph = new Graph(parseTagInt("nbnodes",atts));
			nestNode = parseTagInt("nestnode",atts);
		}
		
		if(tag.equals(NODE))
			crr_node = parseTagInt("nodeidx",atts);

		if(tag.equals(WEIGHT))
			connecting_node = parseTagInt("targetnode",atts);
	
		if(tag.equals(SIMULATION))
		{
			ants= new AntInterface[parseTagInt("antcolsize",atts)];
			
			finalinst = parseTag("finalinst",atts);
			plevel = parseTag("plevel", atts);
		}
			
		if(tag.equals(MOVE))
		{
			alpha = parseTag("alpha",atts);
			beta = parseTag("beta", atts);
			delta = parseTag("delta", atts);	
		}
			
		if(tag.equals(EVAPORATION))
		{
			eta = parseTag("eta", atts);
			rho = parseTag("rho" , atts);
		}
	}
	
	public void endElement(String uri, String name, String tag)
	{
		if(tag.equals(WEIGHT))
		{
			int connection = 0;
			
			try{
				connection = Integer.parseInt(read_string);
			}catch (NumberFormatException | NullPointerException nfe){
				System.exit(-1);	}
			try{
				graph.connect(crr_node, connecting_node, connection);	}
			catch(Graph.NoSuchNodeException | Graph.ExistingLinkException e){
				System.exit(-4);	}
		}
	}
	public void characters(char[] ch, int start, int length){
		read_string=new String(ch,start,length);
	}
	
	public void error(SAXParseException e)throws SAXParseException{
		System.exit(0);
	}
	
	public void fatalError(SAXParseException e) throws SAXParseException{
		System.exit(-4);
	}
	
	public static void main(String args[])
	{
		if(args.length != 2){
			System.exit(-2);
		}
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		try{
			SAXParser parser = factory.newSAXParser();
			parser.parse(new File(args[0]),new Main());
		} catch(ParserConfigurationException e){
			System.exit(-4);
		} catch(SAXException e){
			System.exit(-4);
		} catch(IOException e){
			System.exit(-3);   }
		
 
		try{
			Ant.setParams( graph.getNode(nestNode),graph.getSize() , alpha, beta, plevel , graph.getWeight() );	 }	
		catch(Graph.NoSuchNodeException e){
			System.exit(-4);			}
		
		for(int k=0; k< ants.length ; k++)
				ants[k]= new Ant();
		
		SimulationEvent.setParams(alpha, beta, delta, rho, eta);
		SimulationInterface simulation = new Simulation( ants, finalinst);
		
		String[] a = new String[2]; 
		a[0] = Float.toString(delta);
		a[1]= args[1];
		try {
		simulation.run(a); }
		catch(NumberFormatException e){
			System.exit(-2);	}
		
		System.exit(0);
	}									 
}