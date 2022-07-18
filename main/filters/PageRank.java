package filters;
import java.util.*;

import java.util.Arrays;
import java.util.HashMap;
import normalizations.*;
import core.*;
import loaded.LoadedGraphSignal;
import metrics.*;
import java.math.*;

public class PageRank extends GraphFilter {

	protected double dumpingFactor = 0.85;
	protected double msqrt = 1.E-6;
	protected int maxIterations = 1000;

	public GraphSignal run(Graph graph, GraphSignal inputSignal) {	
		GraphSignal previousSignal = inputSignal;
		//GraphNorm graphNorm = new GraphNorm(graph) ;

		//for(Node node : previousSignal.getkeySet())
			//System.out.println(previousSignal.getNodeScore(node));
		int iterationStep = 0;
		// Initialization
		while (iterationStep < maxIterations) {
			GraphSignal tempSignal = new LoadedGraphSignal(graph);
			for(Edge edge : graph.getEdges()) {
				Node s = edge.getSource();
				Node d = edge.getDestination();
				tempSignal.setNodeScore(d, (tempSignal.getNodeScore(d) +
						(previousSignal.getNodeScore(s) * edge.getEdgeWeight())));
			}
			//for (Edge tempEdge : graph.getEdges()) {
				//Node s = tempEdge.getSource();
				//Node d = tempEdge.getDestination();
				//tempSignal.setNodeScore(d, (tempSignal.getNodeScore(d) + 
						//(previousSignal.getNodeScore(s) /graph.getOutDegree(s))));
			//}
			GraphSignal nextSignal = new LoadedGraphSignal(graph);
			for (Node firstNode : graph.getNodes()) {	
				nextSignal.setNodeScore(firstNode, 
						((1 - dumpingFactor)*inputSignal.getNodeScore(firstNode)) + (dumpingFactor * tempSignal.getNodeScore(firstNode)));	
				//System.out.println(iterationStep + " " + nextSignal.getNodeScore(firstNode));
			}
			double l1 = 0;
			for(Node node :graph.getNodes())
				l1 += nextSignal.getNodeScore(node);
			if(l1 != 0)
				for(Node node :graph.getNodes())
					nextSignal.setNodeScore(node, (nextSignal.getNodeScore(node)/l1));
			l1 = 0 ;
			for(Node node :graph.getNodes())
				l1 += nextSignal.getNodeScore(node);
			iterationStep = iterationStep + 1;
			if (iterationStep <= 1)
				continue ;
			Msqrt msqrt = new Msqrt() ; 
			//previousSignal = nextSignal;
			System.out.println(msqrt.calculate(nextSignal, previousSignal) + " \t " + iterationStep + " \t " + l1);
			if (msqrt.calculate(nextSignal, previousSignal) < this.msqrt) 
				break;		
			previousSignal = nextSignal;
		}
		if(iterationStep == maxIterations)
			throw new RuntimeException("Needs more iterations to converge");
		return previousSignal;
	}
}
