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
   class EdgeIterator implements Iterator<Edge>{
	   protected String fileName ;
	   protected Scanner scanner ;
	   int current = 0 ;
	   public EdgeIterator(String fileName) {
		   this.fileName = fileName ;
		   File file = new File(fileName);
		   try {
			   scanner = new Scanner(file);
		   }
		   catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	   }
	   public boolean hasNext() {
		  if(scanner.hasNextLine())
			  return true ;
		  else
			  return false ;
	   }
	   public Edge next(){
		   String[] nodes = scanner.nextLine().split(",");
		   nodes[0] = nodes[0].strip();
		   nodes[1] = nodes[1].strip();
		   //Graph graph = new LoadedGraph();
		   //graph.addEdge(new LoadedNode(nodes[0]), new LoadedNode(nodes[1]));
		   return new LoadedEdges(new LoadedNode(nodes[0]), new LoadedNode(nodes[1]));
	   }
   }
}
