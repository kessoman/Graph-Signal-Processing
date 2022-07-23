package experiments;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;
import org.apache.tinkerpop.gremlin.*;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.*;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
public class Neo4jMain {
	public static void main(String[] args) throws IOException {
		Graph graph = TinkerGraph.open();
		GraphTraversalSource g = traversal().withEmbedded(graph);
		Vertex sourceNode = g.addV("Node").property("name", "SourceLink").next();
		Vertex destinationNode = g.addV("Node").property("name", "DestinationLink").next();
		g.V(sourceNode).addE("connects with").to(destinationNode).property("weight",1).iterate();
		g.E("connects with");
	}
}

