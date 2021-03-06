package tiinkertop;
import java.io.* ;
import java.util.* ;
import core.*;
import loaded.*;
import org.apache.tinkerpop.gremlin.*;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
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
		g = traversal().withEmbedded(graph);
	}
	public  void addEdge(Node sourceNode, Node destinationNode){
		Vertex sourceVertex = g.addV(sourceNode.toString()).next();
		Vertex destinationVertex = g.addV(destinationNode.toString()).next();
		g.addE("connects to").from(sourceVertex).to(destinationVertex).iterate();
		//g.V(sourceVertex).addE("connects to").to(destinationVertex).iterate();
		g.V().group().by().by(inE().count());
		if(!nodes.containsKey(destinationVertex.label()))
			   nodes.put(destinationVertex.label(), new TinkerTopNode(destinationVertex));
		if(!nodes.containsKey(sourceVertex.label()))
			   nodes.put(sourceVertex.label(),  new TinkerTopNode(sourceVertex));
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
		 System.out.println(g.V().toList().size());
		 return nodes.size();
	 }
	 public  int getNumberOfEdges(){
		 int edgeCounter = 0 ;
		 for(core.Edge edge : getEdges())
		  edgeCounter++ ;
		 return edgeCounter ;
	 }
}
