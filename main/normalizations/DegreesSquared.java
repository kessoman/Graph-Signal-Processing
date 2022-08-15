package normalizations;
import java.util.*;

import loaded.*;
import core.*;
import metrics.*;
import java.math.*;
public class DegreesSquared extends GraphNormalization{
	private Graph graph ;
	public HashMap<Node, Double> inDegree = new HashMap<Node, Double>();
	public HashMap<Node, Double> outDegree = new HashMap<Node, Double>();
	public DegreesSquared(Graph graph) {
		this.graph = graph ;
		getNodes();
		getEdges();
		calculateInOutEdges();
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
	            	 return new LoadedEdges(edge.getSource(), edge.getDestination(), calculateWeight(edge));
	             }
	       	};
	       	 return newEdgeIterator ;
			 }
		 };
	 }
	 public  Iterable<Node> getNodes(){
		 return graph.getNodes();
	 }
	 private void calculateInOutEdges() {
			for (Node n : getNodes()) {
				inDegree.put(n, 0.0);
				outDegree.put(n, 0.0);
			}
			for (Edge edge : getEdges()) {
				inDegree.put(edge.getDestination(), inDegree.get(edge.getDestination()) + edge.getEdgeWeight());
				outDegree.put(edge.getSource(), outDegree.get(edge.getSource()) + edge.getEdgeWeight());
			}
		}
	 public  Double getInDegree (Node destinationNode) {
			return inDegree.get(destinationNode);
	 }
	 public  Double getOutDegree (Node sourceNode) {
			return outDegree.get(sourceNode);
	 }
	public double calculateWeight(Edge edge) {
		return 1/(Math.sqrt(graph.getOutDegree(edge.getSource()) * graph.getInDegree(edge.getDestination())));
	}
	 public  int getNumberOfNodes() {
		 throw new RuntimeException();
	 }
	 public void removeEdge(Node sourceNode, Node destinationNode) {
		 throw new RuntimeException();
	 }
	 public void clearGraphHistory() {
		 throw new RuntimeException();
	 }
	 public  int getNumberOfEdges() {
		 throw new RuntimeException();
	 }
	 public  void addEdge(Node sourceNode, Node destinationNode) {
		 throw new RuntimeException();
	 }
}
