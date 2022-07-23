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
public class TinkerTopEdge extends Edge{
	private Node sourceNode ;
	private Node destinationNode ;
	private double edgeWeight ;
	public TinkerTopEdge(Node sourceNode, Node destinationNode, Double edgeWeight) {
		this.sourceNode = sourceNode ;
		this.destinationNode = destinationNode ;
		this.edgeWeight = edgeWeight;
	}
	public Node getSource() {
		return sourceNode;
	}
	
	public Node getDestination() {
		return destinationNode;
	}
	
	public String toString() {
		return "Sourcenode : " + this.sourceNode.toString() + " " + "Connects with" + " " + "Destinationnode : " + this.destinationNode.toString();
	}
	public double getEdgeWeight() {
		return edgeWeight ;
	}
}
