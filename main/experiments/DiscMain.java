package experiments;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.* ;
import java.util.* ;
import core.*;
import disc.*;
import filters.*;
import loaded.*;
import metrics.*;
import normalizations.*;

public class DiscMain {

	public static void main(String[] args) {
		System.out.println("Creating graph");
		long discGraphTic = System.currentTimeMillis();
		DiscGraph discGraph = new DiscGraph("links_all.csv");
		long discGraphToc = System.currentTimeMillis();
		System.out.println("DiscGraph creation" + (discGraphToc - discGraphTic)/1000);
		GraphSignal graphSignal = new LoadedGraphSignal(discGraph);
		//GraphSignal randomSignal = new LoadedGraphSignal(discGraph);
		//GraphSignal secondSignal = new LoadedGraphSignal(discGraph);
		for(Node node : discGraph.getNodes()) {
			//randomSignal.setNodeScore(node, Math.random());
		   if(node.toString().contains("org.apache.mina") && Math.random()< 0.5) {
			graphSignal.setNodeScore(node, 1.);//yest me randomsignal
		  }
		}
		
		//for(Node node : discGraph.getNodes()) {
			//if(Math.random() < 0.5)
				//secondSignal.setNodeScore(node, 1.);
			//else
				//secondSignal.setNodeScore(node, 0.);
			//if(graphSignal.getNodeScore(node) == 0 && node.toString().contains("org.apache.mina")) 
				//secondSignal.setNodeScore(node, 1.);
		//}
		//double s1 = 0;
		//for(Node node :discGraph.getNodes())
			//s1 += secondSignal.getNodeScore(node);
		//if(s1 != 0)
			//for(Node node :discGraph.getNodes())
			 //secondSignal.setNodeScore(node, (secondSignal.getNodeScore(node)/s1));
		//s1 = 0 ;
		//for(Node node :discGraph.getNodes())
			//s1 += secondSignal.getNodeScore(node);
		//double r1 = 0;
		//for(Node node :discGraph.getNodes())
			//r1 += randomSignal.getNodeScore(node);
		//if(r1 != 0)
			//for(Node node :discGraph.getNodes())
				//randomSignal.setNodeScore(node, (randomSignal.getNodeScore(node)/r1));
	    //r1 = 0 ;
		//for(Node node :discGraph.getNodes())
			//r1 += secondSignal.getNodeScore(node);
		System.out.println("Calculating Pagerank");
		PageRank np = new PageRank();
		HeatKernels hk = new HeatKernels();
		GraphNormalization graphNorm = new GraphNorm(discGraph);
		GraphNormalization degreeAquared = new DegreesSquared(discGraph);
		long discGraphHeatTic = System.currentTimeMillis();
		GraphSignal heatKernelsSignal = hk.run(degreeAquared, graphSignal);
		long discGraphHeatToc = System.currentTimeMillis();
		System.out.println("discGraphHeatKenrels" + (discGraphHeatToc - discGraphHeatTic)/1000);
		long discGraphPagerankTIc = System.currentTimeMillis();
		GraphSignal outputSignal = np.run(graphNorm, graphSignal);
		long discGraphPagerankToc = System.currentTimeMillis();
		System.out.println("discGraphPagerank" + (discGraphPagerankToc - discGraphPagerankTIc)/1000);
		Msqrt msqrt = new Msqrt();
		KLDivergence klDivergence = new KLDivergence();
		MannUTest mannUTest = new MannUTest();
		//System.out.println(mannUTest.calculate(heatKernelsSignal, secondSignal));
		//System.out.println(mannUTest.calculate(randomSignal, secondSignal));
	    //System.out.println(klDivergence.calculate(outputSignal, secondSignal));
		//System.out.println(klDivergence.calculate(randomSignal, secondSignal));
		//System.out.println(msqrt.calculate(heatKernelsSignal, secondSignal));
		//System.out.println(msqrt.calculate(randomSignal, secondSignal));
		//GraphNorm graphNorm = new GraphNorm(discGraph);
	}

}
