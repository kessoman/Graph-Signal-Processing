import java.util.ArrayList;
import java.util.HashMap;
import java.math.*;

public class Pagerank extends GraphFilter {

	protected double dumpingFactor = 0.85 ; 
	protected double mse = 0 ;            
    protected GraphSignal testSignal ;


	public GraphSignal run(Graph graph, GraphSignal testSignal) {
		
	    HashMap<Node, Double > pageRankOdd = new HashMap<Node,Double>();
		GraphSignal tempSignal = new GraphSignal(pageRankOdd) ;
		double initPagerank = 1/(graph.nodes.size()) ;
		int iterationStep = 1 ;
		
		//Initialization
		
		for(Node node : graph.nodes.values()) {
			testSignal.setNodeScore(node, initPagerank);
			//pageRankEven.put(node, initPagerank);
		}
		
		while (iterationStep <= 2) {

			testSignal.copyMaps(tempSignal);

			for (Node firstNode : graph.nodes.values()) {

				double tempSum = 0;

				for (Edge tempEdge : graph.getOutgoingEdges(firstNode)) {

					tempSum = tempSum + (tempSignal.getNodeScore(tempEdge.getDestination())
							* (1 / graph.getOutgoingEdges(tempEdge.getDestination()).size()));
					// testSignal.setNodeScore(firstNode, ((1-dumpingFactor /
					// graph.nodes.size()))+(dumpingFactor * tempSum));

				}

				testSignal.setNodeScore(firstNode,
						((1 - dumpingFactor) / graph.nodes.size()) + (dumpingFactor * tempSum));

			}

			if (iterationStep > 1) {

				double result = 0;

				for (Node mseNode : testSignal.tempMap.keySet()) {
					result = result
							+ Math.pow((tempSignal.getNodeScore(mseNode) - testSignal.getNodeScore(mseNode)), 2);
				}

				// System.out.println(result);

				mse = result / (testSignal.returnSize());

				// System.out.println(mse);

				if (mse < 1e-6) {

					break;

				}
			}

			iterationStep = iterationStep + 1;

		}
		
		return testSignal ;
		
	}	
}
