import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.* ;
import java.util.* ;
/**
 * 
 */

/**
 * @author kesso
 *
 */
public class Main   {

	public static void main(String[] args) throws IOException {
		
		File file = new File("pagetest.csv");

		try {

			Scanner scanner = new Scanner(file);

			HashMap<String, Node> nodes = new HashMap<String, Node>();
			ArrayList<Edge> edges = new ArrayList<Edge>();
			// Graph graph = new LoadedGraph(edges,nodes);

			while (scanner.hasNextLine()) {
				String[] links = scanner.nextLine().split(",");
				Node node1 = nodes.getOrDefault(links[0], null);
				Node node2 = nodes.getOrDefault(links[1], null);
				if (node1 == null) {

					node1 = new LoadedNode(links[0], node1);

				}
				if (node2 == null) {

					node2 = new LoadedNode(links[1], node2);

				}

				nodes.put(links[0], node1);

				nodes.put(links[1], node2);

				// newedge = new LoadEdges(node1,node2);

				Edge edge1 = new LoadedEdges(node1, node2);

				edges.add(edge1);

			}

			Graph graphtest = new LoadedGraph(edges, nodes);
			
			HashMap<Node, Double> testingMap = new HashMap<Node, Double>();

			GraphSignal testingSignal = new GraphSignal(testingMap);

			Pagerank p = new Pagerank();

			p.run(graphtest, testingSignal);

			//System.out.println(p.mse);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	//catch (Exception e)
	//{
	// System.err.println(e.getMessage()); // handle exception
	//}
	//finally {
	//if (br != null) { try { br.close(); } catch(Throwable t) { /* ensure close happens */ } }
	//if (r != null) { try { r.close(); } catch(Throwable t) { /* ensure close happens */ } }
	//if (ins != null) { try { ins.close(); } catch(Throwable t) { /* ensure close happens */ } }

	//}

	//public void read(File links_all.csv) throws IOException{
	//Scanner scanner = new Scanner("links_all.csv");

	//while(scanner.hasNext()){
	//String[] links = scanner.nextLine().split(",");
	//String last = links[links.length - 1];
	//System.out.println(last);
	//}

    }




