import java.util.*;
import java.util.stream.Stream;


public class LoadedGraph extends Graph {
	
	private HashMap<Node,ArrayList<Edge>> inGoingEdges = new HashMap<Node,ArrayList<Edge>>();
	private HashMap<Node,ArrayList<Edge>> outGoingEdges = new HashMap<Node,ArrayList<Edge>>();
  
	//private ArrayList<Edge> inEdges = new ArrayList<Edge>();
	//private ArrayList<Edge> outEdges = new ArrayList<Edge>();

	
	public LoadedGraph(ArrayList<Edge> edges, HashMap<String, Node> nodes) {

		super(edges, nodes);
		calculateInOutEdges(edges);

	}

	public void calculateInOutEdges(ArrayList<Edge> edges) {

		for (Edge newEdge : edges) {

			// Ingoing edges

			if (inGoingEdges.containsKey(newEdge.destinationnode)) {

				//System.out.println("All good");

			} else {

				inGoingEdges.put(newEdge.destinationnode, new ArrayList<Edge>());

			}

			ArrayList<Edge> inEdges = inGoingEdges.computeIfAbsent(newEdge.destinationnode, k -> new ArrayList<Edge>());
			//ArrayList<Edge> inEdges = inGoingEdges.get(newEdge.destinationnode);

			//if (inEdges == null) {

				//inEdges = new ArrayList<Edge>();
				//inEdges.add(newEdge);
				//inGoingEdges.put(newEdge.sourcenode, inEdges);

			//}

			//else {

			if (!inEdges.contains(newEdge)) {

				inEdges.add(newEdge);
				inGoingEdges.put(newEdge.sourcenode, inEdges);

			}

			//}

			// Outgoing Edges

			if (outGoingEdges.containsKey(newEdge.sourcenode)) {

				//System.out.println("All good");

			} else {

				outGoingEdges.put(newEdge.sourcenode, new ArrayList<Edge>());

			}

			ArrayList<Edge> outEdges = outGoingEdges.get(newEdge.sourcenode);

			if (outEdges == null) {

				outEdges = new ArrayList<Edge>();
				outEdges.add(newEdge);
				outGoingEdges.put(newEdge.destinationnode, outEdges);

			}

			else {

				if (!outEdges.contains(newEdge)) {

					outEdges.add(newEdge);
					outGoingEdges.put(newEdge.destinationnode, outEdges);

				}

			}

		}

	}
	
	public ArrayList<Edge> getEdges() {

		return edges;

	}

	public ArrayList<Edge> getIncomingEdges(Node destinationnode) {

		return inGoingEdges.get(destinationnode);

	}

	public ArrayList<Edge> getOutgoingEdges(Node sourcenode) {

		return outGoingEdges.get(sourcenode);

	}

}
