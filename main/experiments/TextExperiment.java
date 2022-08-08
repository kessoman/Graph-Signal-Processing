package experiments;
import core.*;
import disc.PartialDiscGraph;
import loaded.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class TextExperiment {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\kesso\\Documents\\Edges\\");
		PartialDiscGraph graph = new PartialDiscGraph(file);
		
		//Path p = Paths.get("pagetest.csv");	
		Node sourceNode = new LoadedNode("Q");
		Node middleNode = new LoadedNode("R");
		Node destinationNode = new LoadedNode("S");
		graph.addEdge(sourceNode, destinationNode);
		graph.addEdge(sourceNode, middleNode);
		graph.addEdge(middleNode, destinationNode);
		for(Edge edge : graph.getEdges())
			System.out.println(edge.toString());
		System.out.println(graph.getNumberOfEdges()
				);
		long tic = System.currentTimeMillis();
		graph.removeEdge(sourceNode, destinationNode);
		graph.addEdge(sourceNode, destinationNode);
		graph.removeEdge(sourceNode, middleNode);
		graph.addEdge(sourceNode, destinationNode);
		graph.addEdge(sourceNode, middleNode);
		long toc = System.currentTimeMillis();
		System.out.println((toc - tic)/1000);
        
	}

}
