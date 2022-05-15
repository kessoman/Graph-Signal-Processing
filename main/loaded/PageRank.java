package loaded;
import java.util.*;

import java.util.Arrays;
import java.util.HashMap;

import core.*;
import metrics.*;
import java.math.*;

public class PageRank implements GraphFilter {

	protected double dumpingFactor = 0.85;
	protected double msqrt = 1.E-6;
	protected int maxIterations = 1000;

	public GraphSignal run(Graph graph, GraphSignal inputSignal) {	
		GraphSignal previousSignal = inputSignal;
		//for(Node node : previousSignal.getkeySet())
			//System.out.println(previousSignal.getNodeScore(node));
		int iterationStep = 0;
		// Initialization
		while (iterationStep < maxIterations) {
			GraphSignal tempSignal = new LoadedGraphSignal();
			for (Edge tempEdge : graph.getEdges()) {
				Node s = tempEdge.getSource();
				Node d = tempEdge.getDestination();
				tempSignal.setNodeScore(d, (tempSignal.getNodeScore(d) + 
						(previousSignal.getNodeScore(s) /graph.getOutDegree(s))));
			}
			GraphSignal nextSignal = new LoadedGraphSignal();
			for (Node firstNode : graph.getNodes()) {	
				nextSignal.setNodeScore(firstNode, 
						((1 - dumpingFactor)*inputSignal.getNodeScore(firstNode)) + (dumpingFactor * tempSignal.getNodeScore(firstNode)));	
				//System.out.println(iterationStep + " " + nextSignal.getNodeScore(firstNode));
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
			//double result = 0;
			//for (Node node : nextSignal.getkeySet()) 
				//result += Math.pow((previousSignal.getNodeScore(node) - nextSignal.getNodeScore(node)), 2);
			//double msqrt = Math.pow (result / (nextSignal.getSize()), 0.5);
			Msqrt msqrt = new Msqrt() ;
			//previousSignal = nextSignal;
			System.out.println(msqrt.calculate(nextSignal, previousSignal));
			//System.out.println(msqrt + " \t " + iterationStep + " \t " + l1);
			if (msqrt.calculate(nextSignal, previousSignal) < this.msqrt) 
				break;		
			previousSignal = nextSignal;
		}
		if(iterationStep == maxIterations)
			throw new RuntimeException("Needs more iterations to converge");
		//for(Node node : previousSignal.getkeySet())
			//System.out.println(previousSignal.getNodeScore(node));
		return previousSignal;
	}
}
