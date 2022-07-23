package tiinkertop;
import java.io.* ;
import java.util.* ;
import core.*;
import loaded.*;
import org.apache.tinkerpop.gremlin.*;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.*;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
public class TinkerTopGraph extends core.Graph{
	public Graph graph ;
	private Iterable<TinkerTopEdge> edgeIterable ;
	private HashMap<Node, Double> inDegree = new HashMap<Node, Double>();
	private HashMap<Node, Double> outDegree = new HashMap<Node, Double>();
	public TinkerTopGraph() {
		
	}
	public  void addEdge(Node sourceNode, Node destinationNode, Double edgeWeight){
		if (!outDegree.containsValue(sourceNode))
			outDegree.put(sourceNode, 0.);
		if (!inDegree.containsValue(destinationNode))
			inDegree.put(destinationNode, 0.);
		inDegree.put(destinationNode, inDegree.get(destinationNode) + 1);
		outDegree.put(sourceNode, outDegree.get(sourceNode) + 1);
		TinkerTopEdge edge = new TinkerTopEdge(sourceNode, destinationNode, edgeWeight);
	 }
	public Iterable<Edge> getEdges(){
		 throw new RuntimeException();
	}
	public Iterable<Node> getNodes() {
		 throw new RuntimeException();
	}
	public  Iterable<Edge> getIncomingEdges(Node destinationnode){
		 throw new RuntimeException();
	 }
	 public  Iterable<Edge> getOutgoingEdges(Node sourcenode){
		 throw new RuntimeException();
	 }
	 public  Double getInDegree (Node destinationNode){
		 throw new RuntimeException();
	 }
	 public  Double getOutDegree (Node sourceNode){
		 throw new RuntimeException();
	 }
	 public  int getNumberOfNodes() {
		 throw new RuntimeException();
	 }
	 public  int getNumberOfEdges(){
		 throw new RuntimeException();
	 }
	 public  void addEdge(Node sourceNode, Node destinationNode){
		 throw new RuntimeException();
	 }
}
