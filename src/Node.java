
public abstract class Node {
	
	public String nodename ; 
	
	public Node node;
	
  public Node(String name, Node node) {
	  this.nodename = name;
	  this.node = node ;
  } ;
  
  public Node getNode() {
	  return node;
  }
  
  public String toString() {
  return this.nodename;
  }
  
}