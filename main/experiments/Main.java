package experiments;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.* ;
import java.util.* ;
import core.*;
import disc.*;
import filters.HeatKernels;
import filters.PageRank;
import loaded.*;
import normalizations.*;
/**
 * @author kesso
 *
 */
public class Main   {

	public static void main(String[] args) throws IOException {
		File file = new File("pagetest.csv");
		try {
			Scanner scanner = new Scanner(file);
			Graph graphTest = new LoadedGraph();
			//HashMap<String, Node> nodes = new HashMap<String, Node>();
			//ArrayList<Edge> edges = new ArrayList<Edge>();
			System.out.println("Importing");
			long mainGraphTic = System.currentTimeMillis();
			while (scanner.hasNextLine()) {
				String[] links = scanner.nextLine().split(",");
				links[1] = links[1].strip();
				links[0] = links[0].strip();
				Node node1 = graphTest.getOrCreateNode(links[0]);
				Node node2 = graphTest.getOrCreateNode(links[1]);
				//Node node1 = new LoadedNode(links[0]);
				//Node node2 = new LoadedNode(links[1]);
				graphTest.addEdge(node1, node2);
			}
			//while (scanner.hasNextLine()) {
				//String[] links = scanner.nextLine().split(",");	
				//Node node1 = null;
				//links[1] = links[1].strip();
				//links[0] = links[0].strip();
				//if (!nodes.containsKey(links[0])) {
					//node1 = new LoadedNode(links[0]);
					//nodes.put(links[0], node1);
				//}
				//else {
					//node1 = nodes.get(links[0]);
				//}
				//Node node2 = null ;
				//if (!nodes.containsKey(links[1])) {
					//node2 = new LoadedNode(links[1]);
					//nodes.put(links[1], node2);
				//}
				//else {
					//node2 = nodes.get(links[1]);
				//}
				//edges.add(new LoadedEdges(node1, node2));
				//edges.add(new LoadedEdges(node2, node1));	
			//}
			System.out.println("Creating graph");
			//long mainGraphTic = System.currentTimeMillis();
			//LoadedGraph graphtest = new LoadedGraph(edges, nodes);
			long mainGraphToc = System.currentTimeMillis();
			long edgeRemovalTic = System.currentTimeMillis();
			int counter = 0 ;
			for(Edge edge : graphTest.getEdges()) {
			  if(edge.getSource().toString().contains("org.apache.velocity")) {
					counter++ ;
					graphTest.removeEdge(edge.getSource(), edge.getDestination());
				}	
			}
			long edgeRemovalToc = System.currentTimeMillis();
			GraphSignal testingSignal = new LoadedGraphSignal(graphTest);
			GraphSignal randomSignal = new LoadedGraphSignal(graphTest);
			for(Node node : graphTest.getNodes()) {
				randomSignal.setNodeScore(node, Math.random());
				//if(node.toString().contains("org.apache.mina") && Math.random()< 0.5) {
					//testingSignal.setNodeScore(node, 1.);
				  //}			
			}
			//GraphNorm graphNorm = new GraphNorm(graphTest);
			System.out.println("Calculating Pagerank");
			PageRank p = new PageRank();
			long mainGrpahPagerankTic = System.currentTimeMillis();
			p.run(graphTest, randomSignal);
			long mainGraphPagerankToc = System.currentTimeMillis();
			HeatKernels hk = new HeatKernels();
			long mainHeatKernlesTic = System.currentTimeMillis();
			hk.run(graphTest, randomSignal);
			long mainHeatKernelsToc = System.currentTimeMillis();
			System.out.println("Time to remove Edges : " + (edgeRemovalToc - edgeRemovalTic)/1000 + "and removed " + counter + " edges");
			System.out.println("Main Graph" + " " + (mainGraphToc - mainGraphTic)/1000 + " " + graphTest.getNumberOfEdges() + " " + graphTest.getNumberOfNodes());
			System.out.println("MianGraphPagerank" + " " + (mainGraphPagerankToc -mainGrpahPagerankTic)/1000);
			System.out.println("MainHeatKernels" + " " + (mainHeatKernelsToc - mainHeatKernlesTic)/1000);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//GraphNorm graphNorm = new GraphNorm(discGraph);
	 }
}




