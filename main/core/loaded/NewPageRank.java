package core.loaded;
import java.util.*;

import java.util.Arrays;
import java.util.HashMap;

import core.*;

import java.math.*;

public class NewPageRank implements GraphFilter {

	protected double dumpingFactor = 0.85;
	protected double msqrt = 1.E-6;
	protected int maxIterations = 1000;

	public GraphSignal run(Graph graph, GraphSignal inputSignal) {	
		GraphSignal previousSignal = inputSignal;
		int iterationStep = 0;
		// Initialization
		while (iterationStep < maxIterations) {
				double tempSum = 0;
			GraphSignal tempSignal = new LoadedGraphSignal();	
			for (Edge tempEdge : graph.getEdges()) {
				Node v = tempEdge.getSource();
				tempSignal.setNodeScore(v, previousSignal.getNodeScore(v) /graph.getOutDegree(v));
					
			}
			GraphSignal nextSignal = new LoadedGraphSignal();
			for (Node firstNode : graph.getNodes()) {	
				nextSignal.setNodeScore(firstNode, 
						((1 - dumpingFactor)*inputSignal.getNodeScore(firstNode)) + (dumpingFactor * tempSum));				
			}
			double l1 = 0;
			for(Node node :nextSignal.getkeySet())
				l1 += nextSignal.getNodeScore(node);
			if(l1 != 0)
				for(Node node :nextSignal.getkeySet())
					nextSignal.setNodeScore(node, (nextSignal.getNodeScore(node)/l1));
			l1 = 0 ;
			for(Node node :nextSignal.getkeySet())
				l1 += nextSignal.getNodeScore(node);
			iterationStep = iterationStep + 1;
			if (iterationStep <= 1)
				continue ;
			double result = 0;
			for (Node node : nextSignal.getkeySet()) 
				result += Math.pow((previousSignal.getNodeScore(node) - nextSignal.getNodeScore(node)), 2);
			double msqrt = Math.pow (result / (nextSignal.getSize()), 0.5);
			previousSignal = nextSignal;
			System.out.println(msqrt + " \t " + iterationStep + " \t " + l1);
			if (msqrt < this.msqrt) 
				break;			
		}
		if(iterationStep == maxIterations)
			throw new RuntimeException("Needs more iterations to converge");
		return previousSignal;
	}
}
