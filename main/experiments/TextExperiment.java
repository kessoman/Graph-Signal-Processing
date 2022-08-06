package experiments;
import core.*;
import disc.PartialDiscGraph;
import loaded.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class TextExperiment {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\kesso\\Documents\\Edges\\");
		PartialDiscGraph graph = new PartialDiscGraph(file);
		
		//Path p = Paths.get("pagetest.csv");	
		Node sourceNode = new LoadedNode("Q");
		Node destinationNode = new LoadedNode("S");
		graph.addEdge(sourceNode, destinationNode);
		for(Edge edge : graph.getEdges())
			System.out.println(edge.toString());
		long tic = System.currentTimeMillis();
		//graph.removeEdge(sourceNode, destinationNode);
		long toc = System.currentTimeMillis();
		System.out.println((toc - tic)/1000);
		//File file = new File("C:\\Users\\kesso\\Documents\\Edges\\" + sourceNode.toString() + ".neighbours");
		//String s = System.lineSeparator() + sourceNode.toString() + " , " + destinationNode.toString();
		//boolean result;  
		//try   
		//{  
		//result = file.createNewFile();  //creates a new file  
		//if(result)      // test if successfully created a new file  
		//{  
		//System.out.println("file created "+file.getCanonicalPath()); //returns the path string
		//Path p = file.toPath();
		//try {
			//Files.write(p, s.getBytes(), StandardOpenOption.APPEND);
		//} catch (IOException e) {
			//System.err.println(e);
		//}
		//}  
		//else  
		//{  
		//System.out.println("File already exist at location: "+file.getCanonicalPath());
		//Path p = file.toPath();
		//String s2 = System.lineSeparator() + sourceNode.toString() + " , " + destinationNode.toString();
		//try {
			//Files.write(p, s.getBytes(), StandardOpenOption.APPEND);
		//} catch (IOException e) {
			//System.err.println(e);
		//}
		//}  
		//}   
		//catch (IOException e)   
		//{  
		//e.printStackTrace();    //prints exception if any  
		//}         
	}

}
