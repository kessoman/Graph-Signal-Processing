package disc;
import java.io.* ;
import java.util.* ;
import core.*;
import core.loaded.*;

public class DiscGraph implements Graph{
	 public  Iterable<Edge> getEdges(){
		 DiscEdgeList edgesList = new DiscEdgeList();
		 return edgesList ;
	 }
	 public  Iterable<Node> getNodes() {
		 throw new RuntimeException();
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
