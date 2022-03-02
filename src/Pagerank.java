import java.util.ArrayList;
import java.util.HashMap;

public class Pagerank extends GraphFilter {

	//protected HashMap<Node, Double > pagerank = new HashMap<Node,Double>();
	//protected HashMap<Node, Double > tempPagerank = new HashMap<Node,Double>();
	protected double dumpingFactor = 0.85 ; 


	public void Run(Graph graph) {
		
	    HashMap<Node, Double > pageRankOdd = new HashMap<Node,Double>();
		HashMap<Node, Double > pageRankEven = new HashMap<Node,Double>();
		double initPagerank = 1/(graph.nodes.size()) ;
		int iterationStep = 1 ;
		//int currentStep = iterationStep & 2 ;
		//int outGoingLinks ; 
		//double dumpingFactor = 0.85 ; 
		
		//Initialization
		
		for(Node node : graph.nodes.values()) {
			pageRankEven.put(node, initPagerank);
		}
		
		while(iterationStep<=20) {
					
		 if((iterationStep % 2) == 0) {	
		 	
		   for(Node firstNode : graph.nodes.values()) {
		 	 			 
		     for(Edge tempEdge : graph.getOutgoingEdges(firstNode)) {
		 	 	 
		 	   pageRankEven.put(firstNode, (1-dumpingFactor)+(dumpingFactor*(pageRankOdd.get(tempEdge.getdestination())*(1/graph.getOutgoingEdges(firstNode).size()))));
		 		 
		   	 }
		     
		   }
		   
		  }
		 
		 else {
		 
		   for(Node firstNode : graph.nodes.values()) {
	 	   		 
		   	 for(Edge tempEdge : graph.getOutgoingEdges(firstNode)) {
		  		 
			   pageRankOdd.put(firstNode, (1-dumpingFactor)+(dumpingFactor*(pageRankEven.get(tempEdge.getdestination())*(1/graph.getOutgoingEdges(firstNode).size()))));
		    
		     }
		   
		   }			
					
		}
			
		 
		iterationStep = iterationStep + 1 ;
		 
		}
	}	
}
