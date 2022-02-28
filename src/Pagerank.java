import java.util.ArrayList;
import java.util.HashMap;

public class Pagerank extends GraphFilter {

	//protected HashMap<Node, Double > pagerank = new HashMap<Node,Double>();
	//protected HashMap<Node, Double > tempPagerank = new HashMap<Node,Double>();
	protected double dumpingFactor = 0.85 ; 


	public void Run(Graph graph) {
		
	    HashMap<Node, Double > pagerank = new HashMap<Node,Double>();
		//HashMap<Node, Double > tempPagerank = new HashMap<Node,Double>();
		double initPagerank = 1/(graph.nodes.size()) ;
		int iterationstep = 1 ;
		//int outGoingLinks ; 
		//double dumpingFactor = 0.85 ; 
		
		//Initialization
		
		for(Node node : graph.nodes.values()) {
			pagerank.put(node, initPagerank);
		}
		
		while(iterationstep<=20) {
			
		 for(Node firstNode : graph.nodes.values()) {
			 			 
			 for(Edge tempEdge : graph.getOutgoingEdges(firstNode)) {
				 
				 pagerank.put(firstNode, (1-dumpingFactor)+(dumpingFactor*(pagerank.get(tempEdge.getdestination())*(1/graph.getOutgoingEdges(firstNode).size()))));
				 
			 }
			//for outgoing edge get destinaition node kai etsi kanw ypologismoys
			//na fygei h for gia to source node kai h twrinh ton edges kai to outgoinglinks antikatastash me getoutgoingedges
			//oles oi prakseis mazi (px dumping factor na mhn ginetai ektos),na kataxwrw sto hashmap to teliko apotelesma
							
					//for(Edge edge : graph.getOutgoingEdges(firstNode)) {
						//outGoingLinks = 0 ; 
						//if(edge.destinationnode.nodename == secondNode.nodename) {
							//outGoingLinks = outGoingLinks+ 1 ;
						//}
						//pagerank.put(firstNode, (tempPagerank.get(secondNode)*(1/outGoingLinks))) ;

					
					
					//pagerank.put(firstNode, (tempPagerank.get(secondNode)*(1/outGoingLinks))) ;
					
				//}
			}
		 
		 iterationstep = iterationstep + 1 ;
		 
		}
		
		//for (Node dumpNode : pagerank.keySet()) {
			//pagerank.put(dumpNode, ((1-dumpingFactor)+(dumpingFactor*(pagerank.get(dumpNode)))));
		//}
		
	}	

}
