
public class LoadedEdges extends Edge {

	public LoadedEdges(Node sourcenode, Node destinationnode) {
			super(sourcenode,destinationnode);
		}	
	
	public Node getSource() {
		return sourcenode;
	}
	
	public Node getDestination() {
		return destinationnode;
	}
	
	 public Edge getEdge() {
	  	  return content;
	    }
	
	 public String edgeToString() {
		 
		 return "Sourcenode :" + sourcenode.toString() + "Destinationnode :" + destinationnode.toString() ;
		 
	 }
	 
}
