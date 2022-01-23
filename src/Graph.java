import java.util.*;

 public abstract class Graph   {

	 protected HashMap<String,Node> nodes;

	 protected List<Edges> edges;
	 
	 public Graph (List<Edges> edges,HashMap<String,Node> nodes) {
		 
		 this.edges = edges;
		 this.nodes = nodes;
		 
	 }
	 
	 //public ArrayList<Edges> getEdges() {
		 
	    	//return new ArrayList<Edges>(edges);
	    	
	    //}
	 	 
	 //public HashMap<String,Node> getNodes() {
	    	//return new HashMap<String,Node>(nodes);
	    //}
	 
}