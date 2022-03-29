package core;
import java.util.*;
import java.util.stream.Stream;

 public interface Graph  {
	 
	 public   Iterable<Edge> getEdges() ;
	 
	 public  Iterable<Node> getNodes() ;
	 	 
	 public  Iterable<Edge> getIncomingEdges(Node destinationnode);

	 public  Iterable<Edge> getOutgoingEdges(Node sourcenode);
	 
	 public  int getIteratorSize(Iterable iterator);
 
	 //public ArrayList<Edges> getEdges() {
		 
	    	//return new ArrayList<Edges>(edges);
	    	
	    //}
	 	 
	 //public HashMap<String,Node> getNodes() {
	    	//return new HashMap<String,Node>(nodes);
	    //}
	 
}