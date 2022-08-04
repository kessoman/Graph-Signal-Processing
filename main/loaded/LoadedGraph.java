package loaded;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import core.*;


public class LoadedGraph extends Graph {

	protected HashMap<String, Node> nodes ;
	protected ArrayList<Edge> edges ;
	private HashMap<Node,ArrayList<Edge>> inGoingEdges = new HashMap<Node, ArrayList<Edge>>();
	private HashMap<Node,ArrayList<Edge>> outGoingEdges = new HashMap<Node, ArrayList<Edge>>();
	public HashMap<Node, Double> inDegree = new HashMap<Node, Double>();
	public HashMap<Node, Double> outDegree = new HashMap<Node, Double>();

	public LoadedGraph(ArrayList<Edge> edges, HashMap<String, Node> nodes) {

		this.edges = edges;
		this.nodes = nodes;
		calculateInOutEdges(edges, nodes);

	}
	
	public LoadedGraph() {
		this.nodes = new HashMap<String, Node>() ;
		this.edges = new ArrayList<Edge>() ;
	}
	
	 public  int getNumberOfNodes(){
		 return nodes.size();
	 }
	 
	 public  int getNumberOfEdges() {
		 return edges.size();
	 }
	
		public void addEdge(Node sourceNode, Node destinationNode) {
			Edge edge = new LoadedEdges(sourceNode, destinationNode);
			if (!edges.contains(edge))
				edges.add(edge);
			if (!nodes.containsValue(edge.getSource()))
				nodes.put(edge.getSource().toString(), edge.getSource());
			if (!nodes.containsValue(edge.getDestination()))
				nodes.put(edge.getDestination().toString(), edge.getDestination());
			for (Node n : nodes.values()) {
				inGoingEdges.put(n, new ArrayList<Edge>());
				outGoingEdges.put(n, new ArrayList<Edge>());
				inDegree.put(n, 0.0);
				outDegree.put(n, 0.0);
			}
			for (Edge newEdge : edges) {
				inGoingEdges.get(newEdge.getDestination()).add(newEdge);
				inDegree.put(newEdge.getDestination(), inDegree.get(newEdge.getDestination()) + 1);
				outGoingEdges.get(newEdge.getSource()).add(newEdge);
				outDegree.put(newEdge.getSource(), outDegree.get(newEdge.getSource()) + 1);
			}
		}
	public void removeEdge(Node sourceNode, Node destinationNode) {
		for(Edge edge : edges) {
			if(edge.getSource().toString() == sourceNode.toString() && edge.getDestination().toString() == destinationNode.toString()) {
				edges.remove(edge);
			}
		}
		outDegree.put(sourceNode, outDegree.get(sourceNode) - 1);
		inDegree.put(destinationNode, inDegree.get(destinationNode) - 1);
	}	

	private void calculateInOutEdges(ArrayList<Edge> edges, HashMap<String, Node> nodes) {
		for (Node n : nodes.values()) {
			inGoingEdges.put(n, new ArrayList<Edge>());
			outGoingEdges.put(n, new ArrayList<Edge>());
			inDegree.put(n, 0.0);
			outDegree.put(n, 0.0);
		}
		for (Edge newEdge : edges) {
			inGoingEdges.get(newEdge.getDestination()).add(newEdge);
			inDegree.put(newEdge.getDestination(),inDegree.get(newEdge.getDestination()) +  newEdge.getEdgeWeight()) ;
			outGoingEdges.get(newEdge.getSource()).add(newEdge);
			outDegree.put(newEdge.getSource(), outDegree.get(newEdge.getSource()) +  newEdge.getEdgeWeight()) ;
		}
	}
	
	public Iterable<Edge> getEdges() {
		if(edges == null)
			throw new IllegalArgumentException("Could not initialize graph without edges");
		return edges ;
	}
	
	public Iterable<Node> getNodes(){
		
		if(nodes == null)
			throw new IllegalArgumentException("Couldnot initialize graph withouth nodes");
		 return nodes.values();
	 }

	public Iterable<Edge> getIncomingEdges(Node destinationnode) {
		if(destinationnode == null)
			throw new IllegalArgumentException("DestinationNode is null");
        Iterable<Edge> inEdgesIterable = inGoingEdges.get(destinationnode);
		return inEdgesIterable;

	}

	public Iterable<Edge> getOutgoingEdges(Node sourcenode) {
		if(sourcenode == null)
			throw new IllegalArgumentException("SourceNode is null");
		Iterable<Edge> outEdgesIterable = outGoingEdges.get(sourcenode);
		return outEdgesIterable;

	}
	
	public Double getInDegree(Node destinationNode) {
		if(destinationNode == null)
			throw new IllegalArgumentException("DestinationNode is null");
		return inDegree.get(destinationNode);
	}
	
	public Double getOutDegree(Node sourceNode) {
		if(sourceNode == null)
			throw new IllegalArgumentException("SourceNode is null");
		return outDegree.get(sourceNode);
	}
}