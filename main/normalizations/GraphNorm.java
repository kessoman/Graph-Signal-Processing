package normalizations;
import java.util.*;

import loaded.*;
import core.*;
import metrics.*;
import java.math.*;
public class GraphNorm implements Graph {
	private Graph graph ;
	public HashMap<Node, Double> inDegree = new HashMap<Node, Double>();
	public HashMap<Node, Double> outDegree = new HashMap<Node, Double>();
	public GraphNorm(Graph graph) {
		this.graph = graph ;
		//edgeeIterator();
		getNodes();
		getEdges();
	}
	 public  Iterable<Edge> getEdges(){
		 return new Iterable <Edge> () {
			 @Override
			 public Iterator<Edge> iterator(){
			 Iterator <Edge> graphEdgeIterator = graph.getEdges().iterator();
	    	 Iterator <Edge> newEdgeIterator = new Iterator <Edge>() { 
	       	  @Override 
	       	  public boolean hasNext() {
	       		  if(graphEdgeIterator.hasNext())
	       			  return true ;
	       		  else
	       			  return false;
	       	  }
	          @Override
	          public Edge next() {
	        	  Edge edge = graphEdgeIterator.next();
	            	 return new LoadedEdges(edge.getSource(), edge.getDestination(), 1/graph.getOutDegree(edge.getSource()));
	             }
	       	};
	       	 return newEdgeIterator ;
			 }
		 };
		 //ArrayList <Edge> weightedEdges = new ArrayList<Edge>();
		 //for(Edge edge : graph.getEdges())
			// weightedEdges.add(new LoadedEdges(edge.getSource(), edge.getDestination(), 1/graph.getOutDegree(edge.getSource())));
		 //return weightedEdges ;
	 }
	 public  Iterable<Node> getNodes(){
		 return graph.getNodes();
	 }
	 private void calculateInOutEdges() {
		 throw new RuntimeException();
		}
	 public  Iterable<Edge> getIncomingEdges(Node destinationnode){
		 throw new RuntimeException();
	 }
	 public  Iterable<Edge> getOutgoingEdges(Node sourcenode){
		 throw new RuntimeException();
	 }
	 public  Double getInDegree (Node destinationNode) {
		 throw new RuntimeException();
	 }
	 public  Double getOutDegree (Node sourceNode) {
		 throw new RuntimeException();
	 }
	 public  int getNumberOfNodes() {
		 throw new RuntimeException();
	 }
	 public  int getNumberOfEdges() {
		 throw new RuntimeException();
	 }
	 public  void addEdge(Node sourceNode, Node destinationNode) {
		 throw new RuntimeException();
	 }
}
