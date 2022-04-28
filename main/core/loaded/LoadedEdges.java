package core.loaded;

import core.*;

public class LoadedEdges implements Edge {
	
	protected Node sourcenode;
	protected Node destinationnode;

	public LoadedEdges(Node sourcenode, Node destinationnode) {
		if(sourcenode == null)
			throw new IllegalArgumentException("Edge without sourceNode") ;
		if(destinationnode == null)
			throw new IllegalArgumentException("Edge without destinationNode") ;			
		this.sourcenode = sourcenode ;
		this.destinationnode = destinationnode;
		}	
	 
	public Node getSource() {
		return sourcenode;
	}
	
	public Node getDestination() {
		return destinationnode;
	}
	
	public String toString() {
		return "Sourcenode : " + this.sourcenode.toString() + "Destinationnode : " + this.destinationnode.toString();
	}
	
}
