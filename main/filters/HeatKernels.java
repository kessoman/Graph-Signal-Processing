package filters;
import java.util.*;

import java.util.Arrays;
import java.util.HashMap;
import normalizations.*;
import core.*;
import loaded.LoadedGraphSignal;
import metrics.*;
import java.math.*;

public class HeatKernels implements GraphFilter{
	protected int temperature = 3 ;
	protected int k = 20 ;
	protected double msqrt = 1.E-6;
	
	public GraphSignal run(Graph graph, GraphSignal graphSignal) {
		GraphSignal previousSignal = graphSignal ;
		int k = 0 ;
		while (k < this.k) {
		GraphSignal tempSignal = new LoadedGraphSignal();
		for(Edge edge : graph.getEdges()) {
			Node s = edge.getSource();
			Node d = edge.getDestination();
			tempSignal.setNodeScore(s, ((Math.pow(Math.E, -temperature) * Math.pow(temperature, k))/factorial(k)) * Math.pow(graph.getOutDegree(s), k));
		}
		k = k + 1;
		if(k <= 1)
			continue ;
		Msqrt msqrt = new Msqrt();
		if(msqrt.calculate(tempSignal, previousSignal) < this.msqrt)
			break;
		previousSignal = tempSignal ;
		}
		GraphSignal outputSignal = new LoadedGraphSignal();
		for(Node node : graph.getNodes())
			outputSignal.setNodeScore(node, previousSignal.getNodeScore(node) * graphSignal.getNodeScore(node));
		return outputSignal ;
	}
	public static double factorial(double n)
	 {
	  if (n == 0)
	  return 1;
	   
	  return n*factorial(n-1);
	 }
}
