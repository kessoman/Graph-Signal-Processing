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
				// newedge = new LoadEdges(node1,node2);
				System.out.println("" + node1 + node2);
				Edge edge1 = new LoadedEdges(node1, node2);
				edges.add(edge1);	
			}
			Graph graphtest = new LoadedGraph(edges, nodes);
			
			//System.out.println("Size :" + nodes.size());
			
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




