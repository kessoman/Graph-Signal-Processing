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
		double grasphSize = graph.nodes.size();
		double initPagerank = 1 / grasphSize ;
		int iterationStep = 1 ;
		
		//Initialization
		
		for(Node node : graph.nodes.values()) {
			testSignal.setNodeScore(node, initPagerank);
		}
		
		while (iterationStep <= 2) {
			
            //tempSignal.tempMap.putAll(testSignal.tempMap);
			testSignal.copyMaps(tempSignal);
			//for(Node nullNode : tempSignal.tempMap.keySet()) {
				//Double nullvalue = tempSignal.tempMap.getOrDefault(nullNode, initPagerank);
			//}

			for (Node firstNode : graph.nodes.values()) {

				double tempSum = 0;

				for (Edge tempEdge : graph.getIncomingEdges(firstNode)) {

					//tempSignal.tempMap.getOrDefault(tempEdge.getSource(), initPagerank);
					
                    //System.out.println(tempSignal.getNodeScore(tempEdge.getSource()));
					tempSum = tempSum + tempSignal.getNodeScore(tempEdge.getSource())
							* (1 / (graph.getOutgoingEdges(tempEdge.getSource()).size()));
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

			    System.out.println(result);

				mse = result / (testSignal.returnSize());

				//System.out.println(iterationStep);
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
