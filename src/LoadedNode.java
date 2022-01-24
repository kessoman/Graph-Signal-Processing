
public class LoadedNode extends Node {

	public LoadedNode(String name, Node node) {
		super(name , node);
	}
    public 	String toString() {
    	return nodename;
    }
    
    public Node getNode() {
  	  return node;
    }
    
}
