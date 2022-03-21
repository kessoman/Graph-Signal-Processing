import java.util.ArrayList;
import java.util.HashMap;
import java.math.*;

public class Pagerank extends GraphFilter {

	protected double dumpingFactor = 0.85 ;
	//protected double initPagerank = 1/(graph.nodes.size()) ;
	protected double mse = 0 ;            
    protected GraphSignal testSignal ;


	public GraphSignal run(Graph graph, GraphSignal testSignal) {
		
	    HashMap<Node, Double > pageRankOdd = new HashMap<Node,Double>();
		GraphSignal tempSignal = new GraphSignal(pageRankOdd) ;
		double graphSize = graph.nodes.size();
		double initPagerank = 1 / graphSize ;
		int iterationStep = 1 ;
		
		//Initialization
				
		for(Node node : graph.nodes.values()) {
			testSignal.setNodeScore(node, initPagerank);
			System.out.println(node.nodename);
		}
		
		while (iterationStep <= 2) {
			
			System.out.println(iterationStep);
            //tempSignal.tempMap.putAll(testSignal.tempMap);
			tempSignal.copyMaps(testSignal);
			if (iterationStep >= 2) {
				System.out.println("Temp2");
				for (Node nullNode : tempSignal.getkeySet()) {
					System.out.println(tempSignal.getNodeScore(nullNode));
				}
			}

			for (Node firstNode : graph.nodes.values()) {

				double tempSum = 0;

				for (Edge tempEdge : graph.getIncomingEdges(firstNode)) {

					//tempSignal.tempMap.getOrDefault(tempEdge.getSource(), initPagerank);
					
                    //System.out.println(tempSignal.getNodeScore(tempEdge.getSource()));
					tempSum = tempSum + tempSignal.getNodeScore(tempEdge.getSource());
					tempSum = tempSum /	(graph.getOutgoingEdges(tempEdge.getSource()).size());
					// testSignal.setNodeScore(firstNode, ((1-dumpingFactor /
					// graph.nodes.size()))+(dumpingFactor * tempSum));

				}
				testSignal.setNodeScore(firstNode,
						((1 - dumpingFactor) / graph.nodes.size()) + (dumpingFactor * tempSum));	
			}
			
			System.out.println("Test");
			for (Node nullNode : testSignal.getkeySet()) {
				  System.out.println(testSignal.getNodeScore(nullNode));
			  }
			
			//System.out.println("Check");

			if (iterationStep > 1) {

				double result = 0;
				
				System.out.println("Result");

				for (Node mseNode : testSignal.getkeySet()) {
					//ystem.out.println(testSignal.getNodeScore(mseNode));
					//System.out.println(tempSignal.getNodeScore(mseNode));
					result = result
							+ Math.pow((tempSignal.getNodeScore(mseNode) - testSignal.getNodeScore(mseNode)), 2);
				    System.out.println(result);
				}


				mse = result / (testSignal.getSize());

				System.out.println(iterationStep);
				System.out.println(mse);

				if (mse < 1e-6) {

					break;

				}
			}

			iterationStep = iterationStep + 1;

		}
		
		return testSignal ;
		
	}	
}
