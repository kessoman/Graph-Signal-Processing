package disc;
import java.io.* ;
import java.util.* ;
import core.*;
import core.loaded.*;

public class DiscGraph implements Graph{
	protected String fileName ;
	protected  DiscEdgeList edgesList ;
	public HashMap<Node, Integer> inDegree = new HashMap<Node, Integer>();
	public HashMap<Node, Integer> outDegree = new HashMap<Node, Integer>();
	public DiscGraph(String fileName) {
		this.fileName = fileName;
		edgesList = new DiscEdgeList(fileName);
		calcualateInOutDegree();
		getNodes();
	}
	public Iterable<Edge> getEdges() {
		return edgesList;
	}
	public Iterable<Node> getNodes() {
		return edgesList.getIteratorNodes().values();
		// throw new RuntimeException();
	}
	 private void calcualateInOutDegree() { 
		 for(Edge edge : edgesList) {
			 if(!inDegree.containsKey(edge.getDestination()))
				 inDegree.put(edge.getDestination(), 0) ;
			 if(!outDegree.containsKey(edge.getSource()))
				 outDegree.put(edge.getSource(), 0) ;
			 inDegree.put(edge.getDestination(), inDegree.get(edge.getDestination()) + 1);
			 outDegree.put(edge.getSource(), outDegree.get(edge.getSource()) + 1);
		 }
	 }
	 public  Iterable<Edge> getIncomingEdges(Node destinationnode){
		 throw new RuntimeException();
	 }
	 public  Iterable<Edge> getOutgoingEdges(Node sourcenode){
		 throw new RuntimeException();
	 }
	 public  Integer getInDegree (Node destinationNode){
		 return inDegree.get(destinationNode);
		 //throw new RuntimeException();
	 }
	 public  Integer getOutDegree (Node sourceNode){
		 return outDegree.get(sourceNode);
		 //throw new RuntimeException();
	 }
	 public  int getNumberOfNodes() {
		 return edgesList.getIteratorNodes().size();
		 //throw new RuntimeException();
	 }
	 public  int getNumberOfEdges(){
		 int counter = 0 ;
		 for(Edge edge : edgesList)
		  counter++ ;
		 return counter ;
	 }
	 public  void addEdge(Node sourceNode, Node destinationNode){
		 throw new RuntimeException();
	 }
}
class DiscEdgeList implements Iterable<Edge>{
    protected String fileName ;
	public HashMap<String, Node> discNodes = new HashMap<String, Node>();
    public DiscEdgeList(String fileName) {
    	this.fileName = fileName ;
    }
	public Iterator<Edge> iterator(){
		return new EdgeIterator(fileName) ;
	}
	public HashMap<String,Node> getIteratorNodes(){
		return discNodes ;
	}	
 class EdgeIterator implements Iterator<Edge>{
	   protected String fileName ;
	   protected Scanner scanner ;
	   //int current = 0 ;
	   public EdgeIterator(String fileName) {
		   this.fileName = fileName ;
		   File file = new File(fileName);
		   try {
			   scanner = new Scanner(file);
		   }
		   catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	   }
	   public boolean hasNext() {
		  if(scanner.hasNextLine())
			  return true ;
		  else
			  return false ;
	   }
	   public Edge next(){
		   String[] nodes = scanner.nextLine().split(",");
		   nodes[0] = nodes[0].strip();
		   nodes[1] = nodes[1].strip();
		   if(!discNodes.containsKey(nodes[0]))
			   discNodes.put(nodes[0], new LoadedNode(nodes[0]));
		   if(!discNodes.containsKey(nodes[1]))
			   discNodes.put(nodes[1], new LoadedNode(nodes[1]));
		   return new LoadedEdges(discNodes.get(nodes[0]), discNodes.get(nodes[1]));
	   }
   }
 }

