import java.util.*;
import java.util.stream.Stream;

 public abstract class Graph   {

	 protected HashMap<String,Node> nodes;

	 protected ArrayList<Edges> edges;
	 
	 public Graph (ArrayList<Edges> edges,HashMap<String,Node> nodes) {
		 
		 this.edges = edges;
		 this.nodes = nodes;
		 
	 }
	 
	 public  ArrayList<Edges> getEdges() {
		 
		 return new ArrayList<Edges>(edges);
		 	
	 }
	 
	 public abstract Stream<Edges> getIncomingEdges(Node destinationnode);
	 
	 //public ArrayList<Edges> getEdges() {
		 
	    	//return new ArrayList<Edges>(edges);
	    	
	    //}
	 	 
	 //public HashMap<String,Node> getNodes() {
	    	//return new HashMap<String,Node>(nodes);
	    //}
	 
}