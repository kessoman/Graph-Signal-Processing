	
public abstract class Edge   {
	
	protected Node sourcenode;
	protected Node destinationnode;
	
	public Edge content;

	public Edge (Node sourcenode, Node destinationnode) {
		this.sourcenode = sourcenode;
		this.destinationnode = destinationnode;
	}
	
	public Node getsource() {
		return sourcenode;
	}
	
	public Node getdestination() {
		return destinationnode;
	}
	
	public Edge getEdge() {
        return this.content;
    }
	
	public  String edgeToString() {
		
		return "Sourcenode :" + this.sourcenode.toString() + "Destinationnode :" + this.destinationnode.toString();
		
	}
	
}