	
public abstract class Edges   {
	
	protected Node sourcenode;
	protected Node destinationnode;
	
	public Edges content;

	public Edges (Node sourcenode, Node destinationnode) {
		this.sourcenode = sourcenode;
		this.destinationnode = destinationnode;
	}
	
	public Node getsource() {
		return sourcenode;
	}
	
	public Node getdestination() {
		return destinationnode;
	}
	
	public Edges getEdge() {
        return this.content;
    }
	
	public  String edgeToString() {
		
		return "Sourcenode :" + this.sourcenode.toString() + "Destinationnode :" + this.destinationnode.toString();
		
	}
	
}
