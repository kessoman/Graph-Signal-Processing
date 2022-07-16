package filters;
import java.util.*;

import java.util.Arrays;
import java.util.HashMap;
import normalizations.*;
import core.*;
import loaded.LoadedGraphSignal;
import metrics.*;
import java.math.*;

public class HeatKernels extends GraphFilter{
	protected int temperature = 3 ;
	protected int k = 20 ;
	protected double msqrt = 1.E-6;
	
	public GraphSignal run(Graph graph, GraphSignal graphSignal) {
		GraphSignal outputSignal = new LoadedGraphSignal();
		outputSignal = graphSignal ;
		for(int k = 0 ; k < this.k; k++) {
			for(Edge edge : graph.getEdges()) {
				graphSignal.setNodeScore(edge.getSource(), (graphSignal.getNodeScore(edge.getSource()) 
						+ (edge.getEdgeWeight() * graph.getOutDegree(edge.getSource()))));
			}
			for(Node node : graph.getNodes())
				outputSignal.setNodeScore(node, outputSignal.getNodeScore(node) + 
						((Math.pow(temperature, k) * Math.pow(Math.E, -temperature)) / factorial(k)) * graphSignal.getNodeScore(node));
		}
		return outputSignal ;
	}
	public static double factorial(double n)
	 {
	  if (n == 0)
	  return 1;
	   
	  return n*factorial(n-1);
	 }
}
