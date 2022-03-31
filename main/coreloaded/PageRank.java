package coreloaded;
import java.util.*;

import java.util.Arrays;
import java.util.HashMap;

import core.*;

import java.math.*;

public class PageRank implements GraphFilter {

	protected double dumpingFactor = 1;
	protected double mse = 1.E-6;
	protected int maxIterations = 100;

	public GraphSignal run(Graph graph, GraphSignal inputSignal) {	
		GraphSignal previousSignal = inputSignal;
		int iterationStep = 0;
		// Initialization
		while (iterationStep < maxIterations) {
			GraphSignal nextSignal = new LoadedGraphSignal();
			for (Node firstNode : graph.getNodes()) {
				double tempSum = 0;
				for (Edge tempEdge : graph.getIncomingEdges(firstNode)) 
					tempSum = tempSum + (previousSignal.getNodeScore(tempEdge.getSource())
							/graph.getIteratorSize(graph.getOutgoingEdges(tempEdge.getSource())));
				nextSignal.setNodeScore(firstNode, 
						((1 - dumpingFactor)*inputSignal.getNodeScore(firstNode)) + (dumpingFactor * tempSum));				
			}
			double l1 = 0;
			for(Node node :nextSignal.getkeySet())
				l1 += nextSignal.getNodeScore(node);
			if(l1 != 0)
				for(Node node :nextSignal.getkeySet())
					nextSignal.setNodeScore(node, (nextSignal.getNodeScore(node)/l1));
			iterationStep = iterationStep + 1;
			if (iterationStep <= 1)
				continue ;
			double result = 0;
			for (Node node : nextSignal.getkeySet()) 
				result += Math.pow((previousSignal.getNodeScore(node) - nextSignal.getNodeScore(node)), 2);
			double mse = result / (nextSignal.getSize());
			previousSignal = nextSignal;
			System.out.println(mse + " \t " + iterationStep + " \t " + l1);
			if (mse < this.mse) 
				break;			
		}
		if(iterationStep == maxIterations)
			throw new RuntimeException("Needs more iterations to converge");
		return previousSignal;
	}
}