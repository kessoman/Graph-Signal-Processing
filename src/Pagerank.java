import java.util.ArrayList;
import java.util.HashMap;
import java.math.*;

public class Pagerank extends GraphFilter {

	//protected HashMap<Node, Double > pagerank = new HashMap<Node,Double>();
	//protected HashMap<Node, Double > tempPagerank = new HashMap<Node,Double>();
	protected double dumpingFactor = 0.85 ; 
	protected double mse = 0 ;            
    protected GraphSignal testSignal ;


	public GraphSignal Run(Graph graph, GraphSignal testSignal) {
		
		//GraphSignal tempSignal = new GraphSignal(pageRankOdd) ;
	    HashMap<Node, Double > pageRankOdd = new HashMap<Node,Double>();
		GraphSignal tempSignal = new GraphSignal(pageRankOdd) ;
		//HashMap<Node, Double > pageRankEven = new HashMap<Node,Double>();
		double initPagerank = 1/(graph.nodes.size()) ;
		int iterationStep = 1 ;
		double result = 0 ;
		//int currentStep = iterationStep & 2 ;
		//int outGoingLinks ; 
		//double dumpingFactor = 0.85 ; 
		
		//Initialization
		
		for(Node node : graph.nodes.values()) {
			testSignal.setNodescore(node, initPagerank);
			//pageRankEven.put(node, initPagerank);
		}
		
		while(iterationStep<=20) {
					
		 if((iterationStep % 2) == 0) {	
		 	
		   for(Node firstNode : graph.nodes.values()) {
		 	 			 
		     for(Edge tempEdge : graph.getOutgoingEdges(firstNode)) {
		 	 	
		    	 testSignal.setNodescore(firstNode, (1-dumpingFactor)+(dumpingFactor*(tempSignal.getNodeScore(tempEdge.getdestination())*(1/graph.getOutgoingEdges(firstNode).size()))));
		 	   //pageRankEven.put(firstNode, (1-dumpingFactor)+(dumpingFactor*(pageRankOdd.get(tempEdge.getdestination())*(1/graph.getOutgoingEdges(firstNode).size()))));
		 		 
		   	 }
		     
		   }
		   
		  }
		 
		 else {
		 
		   for(Node firstNode : graph.nodes.values()) {
	 	   		 
		   	 for(Edge tempEdge : graph.getOutgoingEdges(firstNode)) {
		  		 
		   		 tempSignal.setNodescore(firstNode, (1-dumpingFactor)+(dumpingFactor*(testSignal.getNodeScore(tempEdge.getdestination())*(1/graph.getOutgoingEdges(firstNode).size()))));
			   //pageRankOdd.put(firstNode, (1-dumpingFactor)+(dumpingFactor*(pageRankEven.get(tempEdge.getdestination())*(1/graph.getOutgoingEdges(firstNode).size()))));
		    
		     }
		   
		   }			
					
		}
			
		if(iterationStep == 1) {
			continue;
		}
		
		else {
			
			for(Node mseNode : testSignal.tempMap.keySet()) {
				result = result + Math.pow((tempSignal.getNodeScore(mseNode)-testSignal.getNodeScore(mseNode)), 2);
			}
			
			mse = result / (testSignal.tempMap.size());
			
			if (mse < 1e-6) {

				break ;
				
			}
		}
		 
		iterationStep = iterationStep + 1 ;
		 
		}
		
		return testSignal ;
		
	}	
}
