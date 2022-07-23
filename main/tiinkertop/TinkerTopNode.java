package tiinkertop;
import org.apache.tinkerpop.gremlin.structure.Vertex;


import core.Node;
public class TinkerTopNode extends Node{
	public Vertex vertex ;
	public TinkerTopNode(Vertex vertex) {
		this.vertex = vertex ;
	}
	public String toString() {
		return vertex.label();
	}
}
