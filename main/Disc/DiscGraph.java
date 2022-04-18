package disc;
import java.io.* ;
import java.util.* ;
import core.*;
import core.loaded.*;

public class DiscGraph implements Graph{
	protected  DiscEdgeList edgesList = new DiscEdgeList();
	 public  Iterable<Edge> getEdges(){
		 return edgesList ;
	 }
	 public  Iterable<Node> getNodes() {
		 Graph graph = new LoadedGraph();
		 for(Edge edge: edgesList)
			 graph.addEdge(edge.getSource(), edge.getDestination());
		 return graph.getNodes();
		//throw new RuntimeException();
	 }
	 public  Iterable<Edge> getIncomingEdges(Node destinationnode){
		 throw new RuntimeException();
	 }
	 public  Iterable<Edge> getOutgoingEdges(Node sourcenode){
		 throw new RuntimeException();
	 }
	 public  Integer getInDegree (Node destinationNode){
		 throw new RuntimeException();
	 }
	 public  Integer getOutDegree (Node sourceNode){
		 throw new RuntimeException();
	 }
	 public  int getNumberOfNodes(){
		 throw new RuntimeException();
	 }
	 public  int getNumberOfEdges(){
		 throw new RuntimeException();
	 }
	 public  void addEdge(Node sourceNode, Node destinationNode){
		 throw new RuntimeException();
	 }
}
