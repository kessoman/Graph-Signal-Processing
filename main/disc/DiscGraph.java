package disc;
import java.io.* ;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.* ;
import core.*;
import loaded.*;

public class DiscGraph extends  Graph{
	protected String fileName ;
	protected  DiscEdgeList edgesList ;
	public HashMap<Node, Double> inDegree = new HashMap<Node, Double>();
	public HashMap<Node, Double> outDegree = new HashMap<Node, Double>();
	public DiscGraph(String fileName) {
		this.fileName = fileName;
		edgesList = new DiscEdgeList(fileName);
		calcualateInOutDegree();
		getNodes();
	}
	public Iterable<Edge> getEdges() {
		return edgesList;
	}
	public Iterable<Node> getNodes() {
		return edgesList.getIteratorNodes().values();
		// throw new RuntimeException();
	}
	 private void calcualateInOutDegree() { 
		 for(Edge edge : edgesList) {
			 if(!inDegree.containsKey(edge.getDestination()))
				 inDegree.put(edge.getDestination(), 0.0) ;
			 if(!outDegree.containsKey(edge.getSource()))
				 outDegree.put(edge.getSource(), 0.0) ;
			 inDegree.put(edge.getDestination(), inDegree.get(edge.getDestination()) + edge.getEdgeWeight());
			 outDegree.put(edge.getSource(), outDegree.get(edge.getSource()) + edge.getEdgeWeight());
		 }
	 }
	 public  Iterable<Edge> getIncomingEdges(Node destinationnode){
		 throw new RuntimeException();
	 }
	 public  Iterable<Edge> getOutgoingEdges(Node sourcenode){
		 throw new RuntimeException();
	 }
	 public  Double getInDegree (Node destinationNode){
		 return inDegree.get(destinationNode);
		 //throw new RuntimeException();
	 }
	 public  Double getOutDegree (Node sourceNode){
		 return outDegree.get(sourceNode);
		 //throw new RuntimeException();
	 }
	 public  int getNumberOfNodes() {
		 return edgesList.getIteratorNodes().size();
		 //throw new RuntimeException();
	 }
	 public  int getNumberOfEdges(){
		 int counter = 0 ;
		 for(Edge edge : edgesList)
		  counter++ ;
		 return counter ;
	 }
	 public  void addEdge(Node sourceNode, Node destinationNode){
		 Path p = Paths.get(fileName);
			String s = System.lineSeparator() + sourceNode.toString() + " , " + destinationNode.toString();
			try {
			    Files.write(p, s.getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
			    System.err.println(e);
			}
	 }
	 public void removeEdge(Node sourceNode , Node destinationNode) {
			try {

			      File inFile = new File(fileName);

			      if (!inFile.isFile()) {
			        System.out.println("Parameter is not an existing file");
			        return;
			      }

			      //Construct the new file that will later be renamed to the original filename.
			      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			      BufferedReader br = new BufferedReader(new FileReader(fileName));
			      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			      String line = null;
			      String lineToRemove = sourceNode.toString() + " , " + destinationNode.toString();

			      //Read from the original file and write to the new
			      //unless content matches data to be removed.
			      while ((line = br.readLine()) != null) {

			        if (!line.trim().equals(lineToRemove)) {

			          pw.println(line);
			          pw.flush();
			        }
			      }
			      pw.close();
			      br.close();

			      //Delete the original file
			      if (!inFile.delete()) {
			        System.out.println("Could not delete file");
			        return;
			      }

			      //Rename the new file to the filename the original file had.
			      if (!tempFile.renameTo(inFile))
			        System.out.println("Could not rename file");

			    }
			    catch (FileNotFoundException ex) {
			      ex.printStackTrace();
			    }
			    catch (IOException ex) {
			      ex.printStackTrace();
			    }
		}
}
class DiscEdgeList implements Iterable<Edge>{
    protected String fileName ;
	public HashMap<String, Node> discNodes = new HashMap<String, Node>();
    public DiscEdgeList(String fileName) {
    	this.fileName = fileName ;
    }
	public Iterator<Edge> iterator(){
		return new EdgeIterator(fileName) ;
	}
	public HashMap<String,Node> getIteratorNodes(){
		return discNodes ;
	}	
 class EdgeIterator implements Iterator<Edge>{
	   protected String fileName ;
	   protected Scanner scanner ;
	   //int current = 0 ;
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
		   if(!discNodes.containsKey(nodes[0]))
			   discNodes.put(nodes[0], new LoadedNode(nodes[0]));
		   if(!discNodes.containsKey(nodes[1]))
			   discNodes.put(nodes[1], new LoadedNode(nodes[1]));
		   return new LoadedEdges(discNodes.get(nodes[0]), discNodes.get(nodes[1]));
	   }
   }
 }

