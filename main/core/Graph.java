package core;
import java.util.*;
import java.util.stream.Stream;

 public interface Graph  {
	 public  Iterable<Edge> getEdges() ;
	 public  Iterable<Node> getNodes() ;
	 public  Iterable<Edge> getIncomingEdges(Node destinationnode);
	 public  Iterable<Edge> getOutgoingEdges(Node sourcenode);
	 public  Integer getInDegree (Node destinationNode);
	 public  Integer getOutDegree (Node sourceNode);
	 public  int getIteratorSize(Iterable<Edge> iterator);	 
}