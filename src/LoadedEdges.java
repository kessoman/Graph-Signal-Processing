
public class LoadedEdges extends Edge {

	public LoadedEdges(Node sourcenode, Node destinationnode) {
			super(sourcenode,destinationnode);
		}	
	
	public Node getsource() {
		return sourcenode;
	}
	
	public Node getdestination() {
		return destinationnode;
	}
	
	 public Edge getEdge() {
	  	  return content;
	    }
	
	 public String edgeToString() {
		 
		 return "Sourcenode :" + sourcenode.toString() + "Destinationnode :" + destinationnode.toString() ;
		 
	 }
	 
}
