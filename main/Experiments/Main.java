package experiments;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.* ;
import java.util.* ;
import core.*;
import disc.*;
import loaded.*;
import normalizations.*;
/**
 * @author kesso
 *
 */
public class Main   {

	public static void main(String[] args) throws IOException {
		File file = new File("links_all.csv");
		try {
			Scanner scanner = new Scanner(file);
			HashMap<String, Node> nodes = new HashMap<String, Node>();
			ArrayList<Edge> edges = new ArrayList<Edge>();
			System.out.println("Importing");
			while (scanner.hasNextLine()) {
				String[] links = scanner.nextLine().split(",");	
				Node node1 = null;
				links[1] = links[1].strip();
				links[0] = links[0].strip();
				if (!nodes.containsKey(links[0])) {
					node1 = new LoadedNode(links[0]);
					nodes.put(links[0], node1);
				}
				else {
					node1 = nodes.get(links[0]);
				}
				Node node2 = null ;
				if (!nodes.containsKey(links[1])) {
					node2 = new LoadedNode(links[1]);
					nodes.put(links[1], node2);
				}
				else {
					node2 = nodes.get(links[1]);
				}
				edges.add(new LoadedEdges(node1, node2));
				edges.add(new LoadedEdges(node2, node1));	
			}
			System.out.println("Creating graph");
			Graph graphtest = new LoadedGraph(edges, nodes);
			GraphSignal testingSignal = new LoadedGraphSignal();
			for(Node node : graphtest.getNodes()) { 
					testingSignal.setNodeScore(node, 1.);
			}
			//GraphNorm graphNorm = new GraphNorm(graphTest);
			System.out.println("Calculating Pagerank");
			PageRank p = new PageRank();
			//p.run(graphtest, testingSignal);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//GraphNorm graphNorm = new GraphNorm(discGraph);
	 }
}
  




