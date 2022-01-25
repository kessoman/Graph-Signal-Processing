
public class LoadedEdges extends Edges {

	public LoadedEdges(Node sourcenode, Node destinationnode) {
			super(sourcenode,destinationnode);
		}	
	
	public Node getsource() {
		return sourcenode;
	}
	
	public Node getdestination() {
		return destinationnode;
	}
	
	 public Edges getEdge() {
	  	  return content;
	    }
	
	 public String edgeToString() {
		 
		 return sourcenode.nodename + destinationnode.nodename ;
		 
	 }
	 
}
