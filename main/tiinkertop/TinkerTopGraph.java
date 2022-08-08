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
		if(!nodes.containsKey(sourceNode.toString()))
			nodes.put(sourceNode.toString(), sourceNode);
		if(!nodes.containsKey(destinationNode.toString()))
			nodes.put(destinationNode.toString(), destinationNode);
		List<Vertex> v1 =  g.V().hasLabel(sourceNode.toString()).fold().coalesce(unfold(),addV(sourceNode.toString())).toList();
		List<Vertex> v2 =  g.V().hasLabel(destinationNode.toString()).fold().coalesce(unfold(),addV(destinationNode.toString())).toList();
		g.addE("connexts to").from(V().hasLabel(sourceNode.toString())).to(V().hasLabel(destinationNode.toString())).iterate();
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
						return new TinkerTopEdge(nodes.get(edge.outVertex().label()), nodes.get(edge.inVertex().label()));
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
			double d = g.V().hasLabel(destinationNode.toString()).inE().count().next();
			return d ;	 
	}
	 public  Double getOutDegree (Node sourceNode){
		double d = g.V().hasLabel(sourceNode.toString()).outE().count().next();
		return d ;
	 }
	 public  int getNumberOfNodes() {
		 System.out.println("Test "  + g.V().toList().size());
		 return nodes.size();
	 }
	 public  int getNumberOfEdges(){
		 return g.E().toList().size();
	 }
}
