package core;
import java.util.*;
import java.util.stream.Stream;

 public interface Graph  {
	 public  Iterable<Edge> getEdges() ;
	 public  Iterable<Node> getNodes() ;
	 public  Iterable<Edge> getIncomingEdges(Node destinationnode);
	 public  Iterable<Edge> getOutgoingEdges(Node sourcenode);
	 public  Double getInDegree (Node destinationNode);
	 public  Double getOutDegree (Node sourceNode);
	 public  int getNumberOfNodes();
	 public  int getNumberOfEdges();
	 public  void addEdge(Node sourceNode, Node destinationNode);
}