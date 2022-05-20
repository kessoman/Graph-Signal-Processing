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
		getNodes();
		getEdges();
		calculateInOutEdges();
	}
	 public  Iterable<Edge> getEdges(){
		 ArrayList <Edge> weightedEdges = new ArrayList<Edge>();
		 for(Edge edge : graph.getEdges())
			 weightedEdges.add(new LoadedEdges(edge.getSource(), edge.getDestination(), 1/graph.getOutDegree(edge.getSource())));
		 return weightedEdges ;
	 }
	 public  Iterable<Node> getNodes(){
		 return graph.getNodes();
	 }
	 private void calculateInOutEdges() {
			for (Node n : getNodes()) {
				//inGoingEdges.put(n, new ArrayList<Edge>());
				//outGoingEdges.put(n, new ArrayList<Edge>());
				inDegree.put(n, 0.0);
				outDegree.put(n, 0.0);
			}
			for (Edge edge : getEdges()) {
				//inGoingEdges.get(newEdge.getDestination()).add(newEdge);
				inDegree.put(edge.getDestination(),inDegree.get(edge.getDestination()) +  edge.getEdgeWeight()) ;
				//outGoingEdges.get(edge.getSource()).add(newEdge);
				outDegree.put(edge.getSource(), outDegree.get(edge.getSource()) +  edge.getEdgeWeight()) ;
			}
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
