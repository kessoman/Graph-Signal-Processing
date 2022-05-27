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

public class DiscMain {

	public static void main(String[] args) {
		System.out.println("Creating graph");
		DiscGraph discGraph = new DiscGraph("links_all.csv");
		GraphSignal graphSignal = new LoadedGraphSignal();
		GraphSignal randomSignal = new LoadedGraphSignal();
		GraphSignal secondSignal = new LoadedGraphSignal();
		for(Node node : discGraph.getNodes()) {
			randomSignal.setNodeScore(node, Math.random());
			graphSignal.setNodeScore(node, 0.);
		  if(node.toString().contains("org.apache")) {
			if(Math.random()< 0.5)
			graphSignal.setNodeScore(node, 1.);
		  }	
		}
		for(Node node : graphSignal.getkeySet()) {
			if(graphSignal.getNodeScore(node) == 0) 
				secondSignal.setNodeScore(node, 1.);
			else
				secondSignal.setNodeScore(node, 0.);
		}
		System.out.println("Calculating Pagerank");
		PageRank np = new PageRank();
		GraphSignal outputSignal = np.run(discGraph, graphSignal);
		//np.run(discGraph, graphSignal);
		Msqrt msqrt = new Msqrt();
		Msqrt randomMsqrt = new Msqrt();
		//System.out.println(msqrt.calculate(outputSignal, secondSignal));
		//System.out.println(msqrt.calculate(randomSignal, secondSignal));
		//GraphNorm graphNorm = new GraphNorm(discGraph);
	}

}
