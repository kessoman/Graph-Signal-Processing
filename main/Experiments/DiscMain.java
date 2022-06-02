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
		  if(node.toString().contains("org.apache.velocity") && Math.random()< 0.5) {
			graphSignal.setNodeScore(node, 1.);
		  }	
		}
		for(Node node : discGraph.getNodes()) {
			if(graphSignal.getNodeScore(node) == 0 && node.toString().contains("org.apache.velocity")) 
				secondSignal.setNodeScore(node, 1.);
		}
		double s1 = 0;
		for(Node node :discGraph.getNodes())
			s1 += secondSignal.getNodeScore(node);
		if(s1 != 0)
			for(Node node :discGraph.getNodes())
			 secondSignal.setNodeScore(node, (secondSignal.getNodeScore(node)/s1));
		//s1 = 0 ;
		//for(Node node :discGraph.getNodes())
			//s1 += secondSignal.getNodeScore(node);
		double r1 = 0;
		for(Node node :discGraph.getNodes())
			r1 += randomSignal.getNodeScore(node);
		if(r1 != 0)
			for(Node node :discGraph.getNodes())
				randomSignal.setNodeScore(node, (randomSignal.getNodeScore(node)/r1));
	    //r1 = 0 ;
		//for(Node node :discGraph.getNodes())
			//r1 += secondSignal.getNodeScore(node);
		System.out.println("Calculating Pagerank");
		PageRank np = new PageRank();
		GraphNorm graphNorm = new GraphNorm(discGraph);
		GraphSignal outputSignal = np.run(graphNorm, graphSignal);
		//np.run(discGraph, graphSignal);
		Msqrt msqrt = new Msqrt();
		//System.out.println(s1);
		KLDivergence klDivergence = new KLDivergence();
	    System.out.println(klDivergence.calculate(outputSignal, secondSignal));
		System.out.println(klDivergence.calculate(randomSignal, secondSignal));
		//System.out.println(msqrt.calculate(outputSignal, secondSignal));
		//System.out.println(msqrt.calculate(randomSignal, secondSignal));
		//GraphNorm graphNorm = new GraphNorm(discGraph);
	}

}
