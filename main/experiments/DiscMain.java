package experiments;
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
		//throwsIoException
		System.out.println("Creating graph");
		File file = new File("C:\\Users\\kesso\\Documents\\DiscGraph\\");
		File infile = new File("links_1k.csv");
		long discGraphTic = System.currentTimeMillis();
		DiscGraph discGraph = new DiscGraph(file);
		Scanner scanner = null ;
		discGraph.clearGraphHistory();
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
				Node node1 = discGraph.getOrCreateNode(links[0]);
				Node node2 = discGraph.getOrCreateNode(links[1]);
				//Node node1 = new LoadedNode(links[0]);
				//Node node2 = new LoadedNode(links[1]);
				discGraph.addEdge(node1, node2);
			}
		long discGraphToc = System.currentTimeMillis();	
		System.out.println("DiscGraph creation" + (discGraphToc - discGraphTic)/1000);
		//discGraph.clearGraphHistory();
		//GraphSignal graphSignal = new LoadedGraphSignal(discGraph);
		GraphSignal randomSignal = new LoadedGraphSignal(discGraph);
		//GraphSignal secondSignal = new LoadedGraphSignal(discGraph);
		for(Node node : discGraph.getNodes()) {
			randomSignal.setNodeScore(node, Math.random());
		   //if(node.toString().contains("org.apache.mina") && Math.random()< 0.5) {
			//graphSignal.setNodeScore(node, 1.);//yest me randomsignal
		  //}
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
		Normalization norm = new DividedByOutDegree(discGraph);
		long discPageRankTic =  System.currentTimeMillis();
		PageRank p = new PageRank();
		p.run(discGraph, randomSignal);
		long discPageRankToc = System.currentTimeMillis();
		System.out.println("DiscPagerank :" + (discPageRankToc - discPageRankTic)/1000 + " " + discGraph.getNumberOfNodes() + " " + discGraph.getNumberOfEdges());
		//PageRank np = new PageRank();
		//HeatKernels hk = new HeatKernels();
		//GraphNormalization graphNorm = new GraphNorm(discGraph);
		//GraphNormalization degreeAquared = new DegreesSquared(discGraph);
	    //ArrayList<Edge> edgesToRemove = new ArrayList<Edge>();
	    //for(Edge edge : discGraph.getEdges()) {
	    	//if(Math.random() < 0.1)
	    		//edgesToRemove.add(edge);
	    //}
	    //long firstStepTic = System.currentTimeMillis();
		//int counter2 = 0 ;
		//long edgeRemovalTic = System.currentTimeMillis();
		//for(Edge edge : edgesToRemove) {
			//counter2 ++;
			//discGraph.removeEdge(edge.getSource(), edge.getDestination());
		//}
		//long edgeRemovalToc = System.currentTimeMillis();
		//System.out.println("Time to remove Edges : " + (edgeRemovalToc - edgeRemovalTic)/1000 + "and removed " + counter2 + " edges" + " " + edgesToRemove.size());
		//long discPageRankTic =  System.currentTimeMillis();
		//PageRank p = new PageRank();
		//p.run(discGraph, randomSignal);
		//long discPageRankToc = System.currentTimeMillis();
		//System.out.println("DiscPagerank :" + (discPageRankToc - discPageRankTic)/1000);
		//long discHeatKernelsTic =  System.currentTimeMillis();
		//HeatKernels h = new HeatKernels();
		//h.run(discGraph, randomSignal);
		//long discHeatKernelsToc = System.currentTimeMillis();
		//System.out.println("DiscGraph creation" + (discGraphToc - discGraphTic)/1000);
		//System.out.println("DiscHeatKernels :" + (discHeatKernelsToc - discHeatKernelsTic)/1000);
		//System.out.println("Time to remove Edges : " + (edgeRemovalToc - edgeRemovalTic)/1000 + "and removed " + counter + " edges");
		//long discGraphHeatTic = System.currentTimeMillis();
		//GraphSignal heatKernelsSignal = hk.run(degreeAquared, randomSignal);
		//hk.run(discGraph, randomSignal);
		//long discGraphHeatToc = System.currentTimeMillis();
		//System.out.println("discGraphHeatKenrels" + " " + (discGraphHeatToc - discGraphHeatTic)/1000);
		//long discGraphPagerankTIc = System.currentTimeMillis();
		//GraphSignal outputSignal = np.run(graphNorm, randomSignal);
		//np.run(discGraph, randomSignal);
		//long discGraphPagerankToc = System.currentTimeMillis();
		//System.out.println("discGraphPagerank" + " " +  (discGraphPagerankToc - discGraphPagerankTIc)/1000);
		//Msqrt msqrt = new Msqrt();
		//KLDivergence klDivergence = new KLDivergence();
		//MannUTest mannUTest = new MannUTest();
		//System.out.println(mannUTest.calculate(heatKernelsSignal, secondSignal));
		//System.out.println(mannUTest.calculate(randomSignal, secondSignal));
	    //System.out.println(klDivergence.calculate(outputSignal, secondSignal));
		//System.out.println(klDivergence.calculate(randomSignal, secondSignal));
		//System.out.println(msqrt.calculate(heatKernelsSignal, secondSignal));
		//System.out.println(msqrt.calculate(randomSignal, secondSignal));
		//GraphNorm graphNorm = new GraphNorm(discGraph);
	}

}