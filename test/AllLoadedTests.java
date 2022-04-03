import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;

import org.junit.Test;

import core.*;
import core.loaded.*;
public class AllLoadedTests {
	
	// Edge
	private Node sourceNode = new LoadedNode("sourceNode");
	private Node destinationNode = new LoadedNode("destinationNode");
	private Edge newEdge = new LoadedEdges(sourceNode, destinationNode);

	// Graph
	HashMap<String, Node> nodes = new HashMap<String, Node>();
	ArrayList<Edge> edges = new ArrayList<Edge>();
	Graph graph = new LoadedGraph(edges, nodes);
	Node node = new LoadedNode("");
	Iterable<Edge> incomingEdges = graph.getIncomingEdges(node);
	
	//@Test
	//public void testPagerankAlgorithms() {
	//GraphTest graphTest = new GraphTest();
	//Graph newGraph = graphTest.createGraph();
	//GraphSignal graphSignal = new LoadedGraphSignal();
	//for(Node node : newGraph.getNodes())
		//graphSignal.setNodeScore(node, 1.);
	//PageRank p = new PageRank();
	//NewPageRank np = new NewPageRank();
	//p.run(newGraph, graphSignal);
	//np.run(newGraph, graphSignal);
	//}

	//Nodes
	@Test
	public void testLoadedNodeConstructor() {
		Assert.assertEquals("", new LoadedNode("").toString());
		Assert.assertEquals("test", new LoadedNode("test").toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLoadedNodeConstructorNullName() {
		new LoadedNode(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullNodesTest() {
		new LoadedNode(null);
	}
	
	//Edges
	@Test
	public void testLoadedEdgesConstructor() {
		Assert.assertEquals("sourceNode", newEdge.getSource().toString());
		Assert.assertEquals("destinationNode", newEdge.getDestination().toString());
	}
	@Test(expected = IllegalArgumentException.class)
	public void testLoadedEdgesConstructorNullSourceNodes() {
		new LoadedEdges(null, destinationNode);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testLoadedEdgesConstructorNullDestinationNodes() {
		new LoadedEdges(sourceNode, null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void nullEdgesTest() {
		new LoadedEdges(null,null);
	}
	
	//Graph
	@Test
	public void testIncomingEdges() {
		Assert.assertEquals(incomingEdges, graph.getIncomingEdges(node));
	}

}
