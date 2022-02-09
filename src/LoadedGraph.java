import java.util.*;
import java.util.stream.Stream;


public class LoadedGraph extends Graph {
	
	HashMap<Node,ArrayList<Edges>> ingoingedges = new HashMap<Node,ArrayList<Edges>>();
	HashMap<Node,ArrayList<Edges>> outgoingedges = new HashMap<Node,ArrayList<Edges>>();
  
	ArrayList<Edges> inedges = new ArrayList<Edges>();
	ArrayList<Edges> outedges = new ArrayList<Edges>();

	
	public LoadedGraph (ArrayList<Edges> edges,HashMap<String,Node> nodes) {
		
		super (edges,nodes);
		
	}
	
	public void calculateInOutEdges(ArrayList<Edges> edges,HashMap<String,Node> nodes) {
		
		for(Node key : nodes.values()) {
			
			for(Edges edge : edges) {
				
				if(key.nodename == edge.sourcenode.nodename) {
					
					outedges.add(edge);
					outgoingedges.put(key, outedges);
					
				}
				
				else if(key.nodename == edge.destinationnode.nodename) {
					
					inedges.add(edge);
					ingoingedges.put(key, inedges);
					
				}
			}
			
		}
		
	}
	
    public  ArrayList<Edges> getEdges() {
		 
		 return edges;
		 	
	 }
	
    public ArrayList<Edges> getIncomingEdges(Node destinationnode){
    	
         return ingoingedges.get(destinationnode);
    	
    }
	
	 public  ArrayList<Edges> getOutgoingEdges(Node sourcenode){
		 
         return outgoingedges.get(sourcenode);
		 
	 }

    
}
