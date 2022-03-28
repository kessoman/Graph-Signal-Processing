import java.util.*;
import java.util.stream.Stream;

 public interface Graph  {
	 
	 public   ArrayList<Edge> getEdges() ;
	 
	 public  ArrayList<Node> getNodes() ;
	 
	 public  void calculateInOutEdges(ArrayList<Edge> edges, HashMap<String,Node> nodes);
	 
	 public  ArrayList<Edge> getIncomingEdges(Node destinationnode);


	 public  ArrayList<Edge> getOutgoingEdges(Node sourcenode);
 
	 //public ArrayList<Edges> getEdges() {
		 
	    	//return new ArrayList<Edges>(edges);
	    	
	    //}
	 	 
	 //public HashMap<String,Node> getNodes() {
	    	//return new HashMap<String,Node>(nodes);
	    //}
	 
}