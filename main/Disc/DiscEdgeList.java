package disc;
import java.io.* ;
import java.util.* ;
import core.*;
import core.loaded.*;

public class DiscEdgeList implements Iterable<Edge>{
    ArrayList<Edge> edgeList = new ArrayList<Edge>();
	public Iterator<Edge> iterator(){
		return new EdgeIterator("pagetest.csv");
	}
   class EdgeIterator<Edge> implements Iterator<Edge>{
	   protected String fileName ;
	   protected Scanner scanner ;
	   int current = 0 ;
	   public EdgeIterator(String fileName) {
		   this.fileName = fileName ;
	   }
	   public boolean hasNext() {
		   File file = new File(fileName);
		   try {
				scanner = new Scanner(file);
				HashMap<String, Node> nodes = new HashMap<String, Node>();
				System.out.println("Importing disc edges");
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
					if(!nodes.containsKey(links[1])) {
						node2 = new LoadedNode(links[1]);
						nodes.put(links[1], node2);
					}
					else {
						node2 = nodes.get(links[1]);
					}
					edgeList.add(new LoadedEdges(node1, node2));
					edgeList.add(new LoadedEdges(node2, node1));
				}
		   }
		   catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		   if(current < edgeList.size())
			   return true ;
		   else
			   return false;
	   }
	   public Edge next(){
		   if(!hasNext())
			   throw new NoSuchElementException() ;
		   else
			   current++ ;
			   return (Edge) edgeList.get(current);
	   }
   }
	public Iterable<Edge> getEdges(){
		for(Edge edge: edgeList)
			System.out.println(edge.toString());
		return edgeList ;
	}
}
