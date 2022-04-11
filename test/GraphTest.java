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
	Node node = new LoadedNode("node");
	Iterable<Node> nodeIterable = graph.getNodes();
	Iterable<Edge> edgeIterable = graph.getEdges();
	Iterable<Edge> incomingEdges = graph.getIncomingEdges(node);
	Iterable<Edge> outgoingEdges = graph.getOutgoingEdges(node);
	
	public GraphTest (){
		
	}
	
	public Graph createGraph() {
		Node node1 = new LoadedNode("A");
		Node node2 = new LoadedNode("B");
		Node node3 = new LoadedNode("C");
		Graph graph = new LoadedGraph();
		graph.addEdge(node1, node2);
		graph.addEdge(node1, node3);
		graph.addEdge(node2, node1);
		graph.addEdge(node2, node3);
		graph.addEdge(node3, node1);
		graph.addEdge(node3, node2);
		return graph ;
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
	@Test
	public void testCreateGraph() {
		GraphTest graph = new GraphTest();
		Graph newGraph = graph.createGraph();
		Node node1 = new LoadedNode("A");
		Node node2 = new LoadedNode("B");
		Node node3 = new LoadedNode("C");
		Node node4 = new LoadedNode("D");
		newGraph.addEdge(node1, node2);
		newGraph.addEdge(node1, node3);
		newGraph.addEdge(node2, node1);
		newGraph.addEdge(node2, node3);
		newGraph.addEdge(node3, node1);
		newGraph.addEdge(node3, node2);
		newGraph.addEdge(node3, node4);
		int inDegree = newGraph.getInDegree(node1);
		int outDegree = newGraph.getOutDegree(node2);
		Graph testGraph = new LoadedGraph();
		testGraph.addEdge(node1, node2);
		testGraph.addEdge(node1, node3);
		ArrayList<Edge> list = new ArrayList<Edge>();
		newGraph.getOutgoingEdges(node3).forEach(list :: add);
		ArrayList<Node> nodes = new ArrayList<Node>();
		newGraph.getNodes().forEach(nodes :: add);
		Assert.assertEquals(inDegree, 2);
		Assert.assertEquals(outDegree, 2);
		Assert.assertEquals((int)newGraph.getOutDegree(node3), list.size());
		Assert.assertTrue(nodes.contains(node4));
		for(Edge edge : list)
			Assert.assertEquals(edge.getSource(), node3);
		
    }
	
	
}
