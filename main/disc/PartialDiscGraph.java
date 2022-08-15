
package disc;
import core.*;

import loaded.LoadedEdges;
import java.util.UUID;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class PartialDiscGraph extends Graph{
	private File file ;
	public HashMap<String, Node> nodes = new HashMap<String, Node>();
	public HashMap<String, UUID> uuids = new HashMap<String, UUID>();
	public HashMap<Node, Double> inDegree = null;
	public HashMap<Node, Double> outDegree =null;
	public PartialDiscGraph(File file) {
		this.file = file;
		inDegree = new HashMap<Node, Double>();
		outDegree = new HashMap<Node, Double>();
	}
	public void addEdge(Node sourceNode, Node destinationNode) {
		if(!nodes.containsKey(sourceNode.toString())) {
			nodes.put(sourceNode.toString(), sourceNode);
			uuids.put(sourceNode.toString(), UUID.randomUUID());
			outDegree.put(sourceNode, 0.);
			inDegree.put(sourceNode, 0.);
		}
		   else {
			   sourceNode = nodes.get(sourceNode.toString());
				}
		if(!nodes.containsKey(destinationNode.toString())) {
			nodes.put(destinationNode.toString(), destinationNode);
			uuids.put(destinationNode.toString(), UUID.randomUUID());
			outDegree.put(destinationNode, 0.);
			inDegree.put(destinationNode, 0.);
		}
		   else {
			   destinationNode = nodes.get(destinationNode.toString());
				}
		File nodeFile = new File(file + "\\" + uuids.get(sourceNode.toString()).toString() + ".neighbours");
		String s1 = sourceNode.toString() + " , " + destinationNode.toString();
		String s2 = System.lineSeparator() + sourceNode.toString() + " , " + destinationNode.toString();
		boolean result;
		Path p = nodeFile.toPath();
		try {
			result = nodeFile.createNewFile();
			if (result) {
				System.out.println("file created " + nodeFile.getCanonicalPath());
				try {
					Files.write(p, s1.getBytes(), StandardOpenOption.APPEND);
				} catch (IOException e) {
					System.err.println(e);
				}
			} else {
				System.out.println("File already exist at location: " + nodeFile.getCanonicalPath());
				try {
					Files.write(p, s2.getBytes(), StandardOpenOption.APPEND);
				} catch (IOException e) {
					System.err.println(e);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		inDegree.put(destinationNode, inDegree.get(destinationNode) + 1);
		outDegree.put(sourceNode, outDegree.get(sourceNode) + 1);
	}
	public Iterable<Edge> getEdges() {
		PartialDiscEdgeList edgeList = new PartialDiscEdgeList(file);
		return edgeList ;
	}
	public Iterable<Node> getNodes() {
		return nodes.values();
	}
	public void removeEdge(Node sourceNode , Node destinationNode) {
		outDegree.put(sourceNode, outDegree.get(sourceNode) - 1);
		inDegree.put(destinationNode, inDegree.get(destinationNode) - 1);
		try {
		      File inFile = new File(file + "\\" + uuids.get(sourceNode.toString()).toString() + ".neighbours");
		      if (!inFile.isFile()) {
		        System.out.println("Parameter is not an existing file");
		        return;
		      }
		      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
		      BufferedReader br = new BufferedReader(new FileReader(file + "\\" +  uuids.get(sourceNode.toString()).toString()  + ".neighbours"));
		      //BufferedReader br = new BufferedReader(new FileReader(file + "\\" + sourceNode.toString() + ".neighbours"));
		      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		      String line = null;
		      String lineToRemove = sourceNode.toString() + " , " + destinationNode.toString();
		      boolean nextLine = false;
		      while ((line = br.readLine()) != null) {
		        if (!line.trim().equals(lineToRemove)) {
					if (nextLine == false) {
						pw.print(line); // if line = first xwris /n alliws /n + line
						pw.flush();
						nextLine = true;
					}
		        	else {
				          pw.print('\n' +line); //if line = first xwris /n alliws /n + line
				          pw.flush();     
		        	}
		        }
		      }
		      pw.close();
		      br.close();
		      if (!inFile.delete()) {
		        System.out.println("Could not delete file"); //anti gia system.out system.err
		        return;
		      }
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
	public void clearGraphHistory () {
		try {
        FileUtils.cleanDirectory(file);
		}
	    catch (IOException ex) {
		      ex.printStackTrace();
		    }
	}
	public Double getInDegree(Node destinationNode) {
		return inDegree.get(destinationNode);
	}
	public Double getOutDegree(Node sourceNode) {
		return outDegree.get(sourceNode);
	}
	public int getNumberOfNodes() {
		return nodes.size();
	}
	public int getNumberOfEdges() {
		 int counter = 0 ;
		 for(Edge edge : getEdges())
		  counter++ ;
		 return counter ;	
		 }

class PartialDiscEdgeList implements Iterable<Edge>{
    public PartialDiscEdgeList(File file) {
    }
	public Iterator<Edge> iterator(){
		return new PartialEdgeIterator(file) ;
	}	
 class PartialEdgeIterator implements Iterator<Edge>{
	   protected Scanner scanner ;
	   protected Iterator<File> it ;
	   public PartialEdgeIterator(File file) {
		   it = FileUtils.iterateFiles(file, null, true);
		   try {
			   scanner = new Scanner(it.next());
		   }
		   catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	   }
	   public boolean hasNext() {
		   if(!scanner.hasNext() && !it.hasNext() ) {
			   scanner.close();
			   return false ;
		   }
		   else
			   return true ;
	   }
	   public Edge next(){
		   if(scanner == null) {
			 try {
				   scanner = new Scanner(it.next());
			   }
			  catch (FileNotFoundException e) {
					e.printStackTrace();
			 }
		   }
		   else {
			   if(!scanner.hasNextLine()) {
				   scanner.close();
				   try {
					   File nameFile = it.next();
					   scanner = new Scanner(nameFile);
				   }
				  catch (FileNotFoundException e) {
						e.printStackTrace();
				 }			   }
		   }
		   String[] stringNodes = scanner.nextLine().split(",");
		   stringNodes[0] = stringNodes[0].strip();
		   stringNodes[1] = stringNodes[1].strip();
		   return new LoadedEdges(nodes.get(stringNodes[0]), nodes.get(stringNodes[1]));
	   }
   }
 }
}