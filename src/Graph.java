import java.util.*;
import java.util.stream.Stream;

 public abstract class Graph   {

	 protected HashMap<String,Node> nodes;

	 protected ArrayList<Edge> edges;
	 
	 public Graph (ArrayList<Edge> edges,HashMap<String,Node> nodes) {
		 
		 this.edges = edges;
		 this.nodes = nodes;
		 
	 }
	 
	 public  ArrayList<Edge> getEdges() {
		 
		 return new ArrayList<Edge>(edges);
		 	
	 }
	 
	 public abstract void calculateInOutEdges(ArrayList<Edge> edges);
	 
	 public abstract ArrayList<Edge> getIncomingEdges(Node destinationnode);


	 public abstract ArrayList<Edge> getOutgoingEdges(Node sourcenode);
 
	 //public ArrayList<Edges> getEdges() {
		 
	    	//return new ArrayList<Edges>(edges);
	    	
	    //}
	 	 
	 //public HashMap<String,Node> getNodes() {
	    	//return new HashMap<String,Node>(nodes);
	    //}
	 
}