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

	public static void main(String[] args) throws IOException
	{
		File file = new File("links_all.csv");
		
		try {

			Scanner scanner = new Scanner(file);
			
			HashMap<String,Node> nodes = new HashMap<String, Node>();
			ArrayList<Edge> edge = new ArrayList<Edge>();
			Graph graph = new LoadedGraph(edge,nodes);

			while (scanner.hasNextLine()) {
				String[] links = scanner.nextLine().split(",");
				//String[] newlinks1 = links[links.length - 3];
				//String[] newlinks2 = links[links.length - 2];
				//System.out.println(newlinks1 + newlinks2);
				Node node1 = nodes.getOrDefault(links[0], null);
				Node node2 = nodes.getOrDefault(links[1], null);
				if(node1 == null ) {

					node1 = new LoadedNode(links[0] , node1);

				}
				if(node2 == null) {

					node2 = new LoadedNode(links[1] , node2);

				}

				nodes.put(links[0], node1 );

				nodes.put(links[1], node2);

				//newedge = new LoadEdges(node1,node2);

				Edge  edge1 = new LoadedEdges(node1,node2);

				edge.add(edge1);
				
				Graph graphtest = new LoadedGraph(edge,nodes);

				System.out.println("hi");


				//System.out.println(edges);  

			}
			// System.out.println(edges.size());

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//InputStream ins = null; // raw byte-stream
		//Reader r = null; // cooked reader
		//BufferedReader br = null; // buffered for readLine()
		//try {
		//String s;
		//ins = new FileInputStream("links_all.csv");
		//r = new InputStreamReader(ins, "UTF-8"); // leave charset out for default
		//br = new BufferedReader(r);
		//while ((s = br.readLine()) != null) {
		// System.out.println(s);

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




