package disc;
import core.*;


public class PartialDiscGraph extends Graph{
	public Iterable<Edge> getEdges() {
		throw new RuntimeException();
	}
	public Iterable<Node> getNodes() {
		throw new RuntimeException();
	}
	public Iterable<Edge> getIncomingEdges(Node destinationnode) {
		throw new RuntimeException();
	}

	public Iterable<Edge> getOutgoingEdges(Node sourcenode) {
		throw new RuntimeException();
	}

	public Double getInDegree(Node destinationNode) {
		throw new RuntimeException();
	}

	public Double getOutDegree(Node sourceNode) {
		throw new RuntimeException();
	}

	public int getNumberOfNodes() {
		throw new RuntimeException();
	}

	public int getNumberOfEdges() {
		throw new RuntimeException();
	}

	public void addEdge(Node sourceNode, Node destinationNode) {
		throw new RuntimeException();
	}
}
