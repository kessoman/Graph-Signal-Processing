package experiments;
import core.*;
import disc.PartialDiscGraph;
import filters.HeatKernels;
import filters.PageRank;
import loaded.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class TextExperiment {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\kesso\\Documents\\Edges\\");
		PartialDiscGraph graph = new PartialDiscGraph(file);
		File infile = new File("links_1k.csv");
		long tic = System.currentTimeMillis();
		Scanner scanner = null ;
		graph.clearGraphHistory();
		try {
			 scanner = new Scanner(infile);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			while (scanner.hasNextLine()) {
				String[] links = scanner.nextLine().split(",");
				links[1] = links[1].strip();
				links[0] = links[0].strip();
				Node node1 = new LoadedNode(links[0]);
				Node node2 = new LoadedNode(links[1]);
				graph.addEdge(node1, node2);
			}
			GraphSignal randomSignal = new LoadedGraphSignal(graph);	
			for(Node node : graph.getNodes()) {
				randomSignal.setNodeScore(node, Math.random());
			}
		//long toc = System.currentTimeMillis();
			GraphSignal tempSignal = new LoadedGraphSignal(graph);
			long firstStepTic = System.currentTimeMillis();
			for(Edge edge : graph.getEdges()) {
				Node s = edge.getSource();
				Node d = edge.getDestination();
				tempSignal.setNodeScore(d, (tempSignal.getNodeScore(d) +
						(randomSignal.getNodeScore(s) * edge.getEdgeWeight())));
			}
		long firstStepToc = System.currentTimeMillis();
		System.out.println("Step : " + (firstStepToc - firstStepTic)/1000);	
		//int counter = 0 ;
		//for(Edge edge : graph.getEdges()) {
			//System.out.println(edge.toString());
		//}
		//System.out.println("Time to remove Edges : " + (edgeRemovalToc - edgeRemovalTic)/1000 + "and removed " + counter + " edges");
		//System.out.println("PartialDiscGraphgetEdges" + (edgeRemovalToc - edgeRemovalTic)/1000);
		long partialPageRankTic =  System.currentTimeMillis();
		PageRank p = new PageRank();
		p.run(graph, randomSignal);
		long partialPageRankToc = System.currentTimeMillis();
		System.out.println("PartialPagerank :" + (partialPageRankToc - partialPageRankTic)/1000);
		long partialHeatKernelsTic =  System.currentTimeMillis();
		HeatKernels h = new HeatKernels();
		h.run(graph, randomSignal);
		long partialHeatKernelsToc = System.currentTimeMillis();
		System.out.println("PartialHeatKernels :" + (partialHeatKernelsToc - partialHeatKernelsTic)/1000);
		//System.out.println("Time to remove Edges : " + (edgeRemovalToc - edgeRemovalTic)/1000 + "and removed " + counter + " edges");
}

}