package core;
import java.util.*;
import java.util.stream.Stream;


public class LoadedGraph implements Graph {

	protected HashMap<String, Node> nodes ;
	protected ArrayList<Edge> edges ;
	private HashMap<Node,ArrayList<Edge>> inGoingEdges = new HashMap<Node,ArrayList<Edge>>();
	private HashMap<Node,ArrayList<Edge>> outGoingEdges = new HashMap<Node,ArrayList<Edge>>();

	public LoadedGraph(ArrayList<Edge> edges, HashMap<String, Node> nodes) {

		this.edges = edges;
		this.nodes = nodes;
		calculateInOutEdges(edges, nodes);

	}

	public void calculateInOutEdges(ArrayList<Edge> edges, HashMap<String, Node> nodes) {

		for (Node n: nodes.values()	){
			inGoingEdges.put(n, new ArrayList<Edge>());
			outGoingEdges.put(n, new ArrayList<Edge>());
		}

		for (Edge newEdge : edges) {

			// Ingoing edges

			if (inGoingEdges.containsKey(newEdge.getDestination())) {
			} else {
				inGoingEdges.put(newEdge.getDestination(), new ArrayList<Edge>());
			}

			ArrayList<Edge> inEdges = inGoingEdges.computeIfAbsent(newEdge.getDestination(), k -> new ArrayList<Edge>());
			//ArrayList<Edge> inEdges = inGoingEdges.get(newEdge.destinationnode);

			if (inEdges == null) {

				inEdges = new ArrayList<Edge>();
				inEdges.add(newEdge);
				inGoingEdges.put(newEdge.getDestination(), inEdges);

			}

			else {

			if (!inEdges.contains(newEdge)) {

				inEdges.add(newEdge);
				inGoingEdges.put(newEdge.getDestination(), inEdges);

			}

			}

			// Outgoing Edges

			if (outGoingEdges.containsKey(newEdge.getSource())) {
			} else {

				outGoingEdges.put(newEdge.getSource(), new ArrayList<Edge>());

			}

			ArrayList<Edge> outEdges = outGoingEdges.computeIfAbsent(newEdge.getSource(), k -> new ArrayList<Edge>());

			if (outEdges == null) {

				outEdges = new ArrayList<Edge>();
				outEdges.add(newEdge);
				outGoingEdges.put(newEdge.getSource(), outEdges);

			}

			else {

				if (!outEdges.contains(newEdge)) {

					outEdges.add(newEdge);
					outGoingEdges.put(newEdge.getSource(), outEdges);

				}

			}

		}

	}
	
	public ArrayList<Edge> getEdges() {

		return edges;

	}
	
	public ArrayList<Node> getNodes(){
		 return new ArrayList<Node>(nodes.values());
	 }

	public ArrayList<Edge> getIncomingEdges(Node destinationnode) {

		return inGoingEdges.get(destinationnode);

	}

	public ArrayList<Edge> getOutgoingEdges(Node sourcenode) {

		return outGoingEdges.get(sourcenode);

	}

}