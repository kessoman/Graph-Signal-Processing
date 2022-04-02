package core.loaded;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import core.*;


public class LoadedGraph implements Graph {

	protected HashMap<String, Node> nodes ;
	protected ArrayList<Edge> edges ;
	private HashMap<Node,ArrayList<Edge>> inGoingEdges = new HashMap<Node, ArrayList<Edge>>();
	private HashMap<Node,ArrayList<Edge>> outGoingEdges = new HashMap<Node, ArrayList<Edge>>();
	public HashMap<Node, Integer> inDegree = new HashMap<Node, Integer>();
	public HashMap<Node, Integer> outDegree = new HashMap<Node, Integer>();

	public LoadedGraph(ArrayList<Edge> edges, HashMap<String, Node> nodes) {

		this.edges = edges;
		this.nodes = nodes;
		calculateInOutEdges(edges, nodes);

	}

	private void calculateInOutEdges(ArrayList<Edge> edges, HashMap<String, Node> nodes) {
		for (Node n : nodes.values()) {
			inGoingEdges.put(n, new ArrayList<Edge>());
			outGoingEdges.put(n, new ArrayList<Edge>());
			inDegree.put(n, 0);
			outDegree.put(n, 0);
		}
		for (Edge newEdge : edges) {
			inGoingEdges.get(newEdge.getDestination()).add(newEdge);
			inDegree.put(newEdge.getDestination(),inDegree.get(newEdge.getDestination()) + 1) ;
			outGoingEdges.get(newEdge.getSource()).add(newEdge);
			outDegree.put(newEdge.getSource(), outDegree.get(newEdge.getSource()) + 1) ;
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
		
        Iterable<Edge> inEdgesIterable = inGoingEdges.get(destinationnode);
		return inEdgesIterable;

	}

	public Iterable<Edge> getOutgoingEdges(Node sourcenode) {
		
		Iterable<Edge> outEdgesIterable = outGoingEdges.get(sourcenode);
		return outEdgesIterable;

	}
	
	public Integer getInDegree(Node destinationNode) {
		return inDegree.get(destinationNode);
	}
	
	public Integer getOutDegree(Node sourceNode) {
		return outDegree.get(sourceNode);
	}
	
	public int getIteratorSize(Iterable<Edge> sizeIterator) {
		AtomicInteger count = new AtomicInteger(0);
        sizeIterator.forEach(element -> {
            count.incrementAndGet();
        });
        return count.get();
	}

}