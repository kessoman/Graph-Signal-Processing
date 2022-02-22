import java.util.ArrayList;
import java.util.HashMap;

public class Pagerank extends GraphFilter {

	protected HashMap<Node, Double > pagerank = new HashMap<Node,Double>();
	protected HashMap<Node, Double > tempPagerank = new HashMap<Node,Double>();

	public void run(Graph graph) {
				
		double initPagerank = 1/(graph.nodes.size()) ;
		int outGoingLinks ; 
		double dumpingFactor = 0.85 ; 
		
		//Initialization
		
		for(Node node : graph.nodes.values()) {
			tempPagerank.put(node, initPagerank);
		}
		
		for(Node firstNode : graph.nodes.values()) {
			for(Node secondNode : graph.nodes.values()) {
				outGoingLinks = 0 ;
				if(firstNode == secondNode) {
					
				}
				else {
					for(Edge edge : graph.getOutgoingEdges(firstNode)) {
						if(edge.destinationnode == secondNode) {
							outGoingLinks += 1 ;
						}
					}
					
					pagerank.put(firstNode, (tempPagerank.get(secondNode)*(1/outGoingLinks))) ;
					
				}
			}
		}
		
		for (Node dumpNode : pagerank.keySet()) {
			pagerank.put(dumpNode, ((1-dumpingFactor)+(dumpingFactor*(pagerank.get(dumpNode)))));
		}
		
	}	

}
