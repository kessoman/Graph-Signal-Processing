import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;

import org.junit.Test;

import core.*;
import core.loaded.*;

public class LoadedGraphTest {
	
	HashMap<String, Node> nodes = new HashMap<String, Node>();
	ArrayList<Edge> edges = new ArrayList<Edge>();
	Graph graph = new LoadedGraph(edges, nodes);
	Node node = new LoadedNode("");
	Iterable<Edge> incomingEdges = graph.getIncomingEdges(node);
	
	@Test(expected = IllegalArgumentException.class)
	public void nullNodesTest() {
		new LoadedNode(null);
	}
	@Test
	public void testIncomingEdges() {
		Assert.assertEquals(incomingEdges, graph.getIncomingEdges(node));
	}

}
