package core;
import java.util.*;
import java.util.stream.Stream;

 public abstract class Graph  {
	 public abstract  Iterable<Edge> getEdges() ;
	 public abstract  Iterable<Node> getNodes() ;
	 public abstract  Iterable<Edge> getIncomingEdges(Node destinationnode);
	 public abstract  Iterable<Edge> getOutgoingEdges(Node sourcenode);
	 public abstract  Double getInDegree (Node destinationNode);
	 public abstract  Double getOutDegree (Node sourceNode);
	 public abstract  int getNumberOfNodes();
	 public abstract  int getNumberOfEdges();
	 public abstract  void addEdge(Node sourceNode, Node destinationNode);
}