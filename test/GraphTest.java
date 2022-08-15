import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

import core.*;
import loaded.*;

public class GraphTest {

	HashMap<String, Node> nodes = new HashMap<String, Node>();
	ArrayList<Edge> edges = new ArrayList<Edge>();
	Graph graph = new LoadedGraph(edges, nodes);
	Node node = new LoadedNode("node");
	Iterable<Node> nodeIterable = graph.getNodes();
	Iterable<Edge> edgeIterable = graph.getEdges();

	
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
		double inDegree = newGraph.getInDegree(node1);
		double outDegree = newGraph.getOutDegree(node2);
		Graph testGraph = new LoadedGraph();
		testGraph.addEdge(node1, node2);
		testGraph.addEdge(node1, node3);
		ArrayList<Edge> list = new ArrayList<Edge>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		newGraph.getNodes().forEach(nodes :: add);
		Assert.assertTrue(nodes.contains(node4));
		for(Edge edge : list)
			Assert.assertEquals(edge.getSource(), node3);
		
    }
	
	
}
