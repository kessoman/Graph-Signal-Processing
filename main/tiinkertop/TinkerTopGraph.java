package tiinkertop;
import java.io.* ;
import java.util.* ;
import java.util.function.Supplier;
import core.*;
import loaded.*;
import org.apache.tinkerpop.gremlin.*;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.tinkergraph.*;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.neo4j.cypher.internal.compiler.eagerUpdateStrategy;
import org.neo4j.internal.recordstorage.RelationshipCreator.NodeDataLookup;
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.*;
import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;
public class TinkerTopGraph extends core.Graph{
	public Graph graph ;
	private HashMap<String, Node> nodes ;
	private HashMap<Node, Double> inDegree = new HashMap<Node, Double>();
	private HashMap<Node, Double> outDegree = new HashMap<Node, Double>();
	private GraphTraversalSource g ;
	public TinkerTopGraph(Graph graph) {
		this.graph = graph;
		this.nodes = new HashMap<String, Node>();
		graph = TinkerGraph.open();
		g = graph.traversal();
				}
	public  void addEdge(Node sourceNode, Node destinationNode){
		//g.V().has("link", "name", sourceNode.toString()).fold().coalesce(unfold() , addV("link").property
				//("name", sourceNode.toString()));
		//if(!nodes.containsKey(sourceNode.toString())) {	
			//Vertex v1 = g.addV(sourceNode.toString()).next();
			  // nodes.put(v1.label(), new TinkerTopNode(v1));
		//}	   
		//if(!nodes.containsKey(destinationNode.toString())) {
			//Vertex v2 = g.addV(destinationNode.toString()).next();
			//nodes.put(v2.label(),  new TinkerTopNode(v2));
		//}
		Vertex v1 = graph.addVertex(sourceNode.toString());
		Vertex v2 = graph.addVertex(destinationNode.toString());
		//g.addE("connects").from(V(sourceNode.toString())).to(V(destinationNode.toString())).iterate();
		//Vertex v1 = g.V(sourceNode.toString()).next();
		//Vertex v2 = g.V(destinationNode.toString()).next();
		//g.addE("connects").from(v1).to(v2).iterate();
	 }
	public Iterable<core.Edge> getEdges() {
		return new Iterable<core.Edge>() {
			@Override
			public Iterator<core.Edge> iterator() {
				Iterable<Edge> edgeIterable = g.E().toList();
				Iterator<Edge> graphEdgeIterator = edgeIterable.iterator();
				//Iterator<Edge> graphEdgeIterator = g.E("connects to").toList().iterator();
				Iterator<core.Edge> newEdgeIterator = new Iterator<core.Edge>() {
					@Override
					public boolean hasNext() {
						if (graphEdgeIterator.hasNext())
							return true;
						else
							return false;
					}

					@Override
					public core.Edge next() {
						Edge edge = graphEdgeIterator.next();
						return new TinkerTopEdge(nodes.get(edge.inVertex().label()), nodes.get(edge.outVertex().label()));
					}
				};
				return newEdgeIterator;
			}
		};
		
	}
	public Iterable<Node> getNodes() {
		 return nodes.values();
	}
	public  Iterable<core.Edge> getIncomingEdges(Node destinationnode){
		 throw new RuntimeException();
	 }
	 public  Iterable<core.Edge> getOutgoingEdges(Node sourcenode){
		 throw new RuntimeException();
	 }
	 public  Double getInDegree (Node destinationNode){
		 throw new RuntimeException();
	 }
	 public  Double getOutDegree (Node sourceNode){
		 double d =g.V(sourceNode.toString()).group().by().by(inE().count()).toList().size();
		return	d ;
		 //throw new RuntimeException();
	 }
	 public  int getNumberOfNodes() {
		 return nodes.size();
	 }
	 public  int getNumberOfEdges(){
		 return g.E().toList().size();
	 }
}
