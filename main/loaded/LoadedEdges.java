package loaded;

import core.*;

public class LoadedEdges extends Edge {
	
	protected Node sourcenode;
	protected Node destinationnode;
	protected double edgeWeight ;

	public LoadedEdges(Node sourcenode, Node destinationnode) {
		if(sourcenode == null)
			throw new IllegalArgumentException("Edge without sourceNode") ;
		if(destinationnode == null)
			throw new IllegalArgumentException("Edge without destinationNode") ;			
		this.sourcenode = sourcenode ;
		this.destinationnode = destinationnode;
		edgeWeight = 1 ;
		}
	public LoadedEdges(Node sourcenode, Node destinationnode, double edgeWeight) {
		this.sourcenode = sourcenode ;
		this.destinationnode = destinationnode;
		this.edgeWeight = edgeWeight ;
	}
	 
	public Node getSource() {
		return sourcenode;
	}
	
	public Node getDestination() {
		return destinationnode;
	}
	
	public String toString() {
		return "Sourcenode : " + this.sourcenode.toString() + " " + "Destinationnode : " + this.destinationnode.toString();
	}
	public double getEdgeWeight() {
		return edgeWeight ;
	}
}
