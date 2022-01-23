
public class LoadEdges extends Edges {

	public LoadEdges(Node sourcenode, Node destinationnode) {
			super(sourcenode,destinationnode);
		}	
	
	public Node getsource() {
		return sourcenode;
	}
	
	public Node getdestination() {
		return destinationnode;
	}
	
	 public Edges getEdges() {
	  	  return content;
	    }
	
}
