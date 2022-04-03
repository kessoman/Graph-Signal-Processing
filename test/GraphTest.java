import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

import core.*;
import core.loaded.*;

public class GraphTest {

	HashMap<String, Node> nodes = new HashMap<String, Node>();
	ArrayList<Edge> edges = new ArrayList<Edge>();
	Graph graph = new LoadedGraph(edges, nodes);
	Node node1 = new LoadedNode("A");
	Node node2 = new LoadedNode("B");
	Node node3 = new LoadedNode("c");
	Iterable<Node> nodeIterable = graph.getNodes();
	Iterable<Edge> edgeIterable = graph.getEdges();
	Iterable<Edge> incomingEdges = graph.getIncomingEdges(node1);
	Iterable<Edge> outgoingEdges = graph.getOutgoingEdges(node2);
	
	public void createGraph() {
		Graph graph = new LoadedGraph();
		graph.addEdge(node1, node2);
		graph.addEdge(node1, node3);
		graph.addEdge(node2, node1);
		graph.addEdge(node2, node3);
		graph.addEdge(node3, node1);
		graph.addEdge(node3, node2);
	}
	
	@Test
	public void testGetEdges() {
		Assert.assertEquals(edgeIterable, graph.getEdges());
	}
	@Test
	public void testGetnNodes() {
		Assert.assertEquals(nodeIterable, graph.getNodes());
	}
	@Test
	public void testGetIncomingEdges() {
		Assert.assertEquals(incomingEdges, graph.getIncomingEdges(new LoadedNode("A")));
	}
	@Test
	public void testGetOutgoingEdges() {
		Assert.assertEquals(outgoingEdges, graph.getOutgoingEdges(new LoadedNode("A")));
	}
	
	
}
