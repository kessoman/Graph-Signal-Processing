package experiments;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.* ;
import java.util.* ;
import core.*;
import disc.*;
import loaded.*;
import metrics.*;
import normalizations.*;

public class RandomExperiments {
	public static void main(String[] args) {
		System.out.println("Creating graph");
		DiscGraph discGraph = new DiscGraph("pagetest.csv");
		GraphNorm graphNorm = new GraphNorm(discGraph);
		PageRank p = new PageRank();
		GraphSignal graphSignal = new LoadedGraphSignal();
		for(Node node : discGraph.getNodes()) {
			graphSignal.setNodeScore(node, 1.);
		}
		p.run(graphNorm, graphSignal);
		//for(Edge edge : graphNorm.getEdges())
			//System.out.println(edge.toString() + " " + edge.getEdgeWeight());
	}	
}
