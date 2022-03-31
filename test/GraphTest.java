import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

import core.*;
import coreloaded.*;

public class GraphTest {

	HashMap<String, Node> nodes = new HashMap<String, Node>();
	ArrayList<Edge> edges = new ArrayList<Edge>();
	Graph graph = new LoadedGraph(edges, nodes);
	Node node = new LoadedNode("D");
	Iterable<Node> nodeIterable = graph.getNodes();
	Iterable<Edge> edgeIterable = graph.getEdges();
	Iterable<Edge> incomingEdges = graph.getIncomingEdges(node);
	Iterable<Edge> outgoingEdges = graph.getOutgoingEdges(node);
	
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
		Assert.assertEquals(incomingEdges, graph.getIncomingEdges(new LoadedNode("D")));
	}
	@Test
	public void testGetOutgoingEdges() {
		Assert.assertEquals(outgoingEdges, graph.getOutgoingEdges(new LoadedNode("D")));
	}
	
	
}
