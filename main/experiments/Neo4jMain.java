package experiments;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.apache.tinkerpop.gremlin.*;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.*;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import core.Node;
import loaded.LoadedNode;
import tiinkertop.TinkerTopGraph;

import java.lang.Iterable;
import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;

public class Neo4jMain {
	public static void main(String[] args) throws IOException {
		File file = new File("pagetest.csv");
		Graph graph = TinkerGraph.open();
		long tinkerPopGrpahTic =System.currentTimeMillis();
		core.Graph coreGraph = new TinkerTopGraph(graph);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String[] links = scanner.nextLine().split(",");
				links[1] = links[1].strip();
				links[0] = links[0].strip();
				Node node1 = new LoadedNode(links[0]);
				Node node2 = new LoadedNode(links[1]);
				coreGraph.addEdge(node1, node2);				
			}
		long tinkerPopGrpahToc =System.currentTimeMillis();
		System.out.println("TinkerPopGraph" + " " + (tinkerPopGrpahToc - tinkerPopGrpahTic)/1000.0);
		System.out.println(coreGraph.getNumberOfEdges() + " " + coreGraph.getNumberOfNodes());
	    ArrayList<core.Edge> edgesToRemove = new ArrayList<core.Edge>();
	    for(core.Edge edge : coreGraph.getEdges()) {
	    	if(Math.random() < 0.01) {
	    		edgesToRemove.add(edge);
	    	}
	    }
	    //long firstStepTic = System.currentTimeMillis();
		int counter2 = 0 ;
		long edgeRemovalTic = System.currentTimeMillis();
		for(core.Edge edge : edgesToRemove) {
			counter2 ++;
			coreGraph.removeEdge(edge.getSource(), edge.getDestination());
		}
		long edgeRemovalToc = System.currentTimeMillis();
		System.out.println("Time to remove Edges : " + (edgeRemovalToc - edgeRemovalTic)/1000 + "and removed " + counter2 + " edges" + " " + edgesToRemove.size());
		System.out.println(coreGraph.getNumberOfEdges() + " " + coreGraph.getNumberOfNodes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

