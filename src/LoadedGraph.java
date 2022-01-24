import java.util.*;
import java.util.stream.Stream;


public class LoadedGraph extends Graph {

	public LoadedGraph (ArrayList<Edges> edges,HashMap<String,Node> nodes) {
		
		super (edges,nodes);
		
	}
	
    public  ArrayList<Edges> getEdges() {
		 
		 return edges;
		 	
	 }
	
    public Stream<Edges> getIncomingEdges(Node destinationnode){
    	
    	return getEdges().stream().filter(edge -> edge.getdestination() == destinationnode);
    	
    }
	
}
