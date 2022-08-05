package disc;
import core.*;
import loaded.LoadedEdges;

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
	public HashMap<Node, Double> inDegree = new HashMap<Node, Double>();
	public HashMap<Node, Double> outDegree = new HashMap<Node, Double>();
	public PartialDiscGraph(File file) {
		this.file = file;
	}
	public void addEdge(Node sourceNode, Node destinationNode) {
		if(!nodes.containsKey(sourceNode.toString())) {
			nodes.put(sourceNode.toString(), sourceNode);
			outDegree.put(sourceNode, 0.);
			inDegree.put(sourceNode, 0.);
		}
		if(!nodes.containsKey(destinationNode.toString())) {
			nodes.put(destinationNode.toString(), destinationNode);
			outDegree.put(destinationNode, 0.);
			inDegree.put(destinationNode, 0.);
		}
		inDegree.put(destinationNode, inDegree.get(destinationNode) + 1);
		outDegree.put(sourceNode, outDegree.get(sourceNode) + 1);
		File nodeFile = new File(file + "\\" + sourceNode.toString() + ".neighbours");
		String s = System.lineSeparator() + sourceNode.toString() + " , " + destinationNode.toString();
		boolean result;
		Path p = nodeFile.toPath();
		try {
			result = nodeFile.createNewFile();
			if (result) {
				System.out.println("file created " + nodeFile.getCanonicalPath());
				try {
					Files.write(p, s.getBytes(), StandardOpenOption.APPEND);
				} catch (IOException e) {
					System.err.println(e);
				}
			} else {
				System.out.println("File already exist at location: " + nodeFile.getCanonicalPath());
				try {
					Files.write(p, s.getBytes(), StandardOpenOption.APPEND);
				} catch (IOException e) {
					System.err.println(e);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		      File inFile = new File(file + "\\" + sourceNode.toString() + ".neighbours");
		      if (!inFile.isFile()) {
		        System.out.println("Parameter is not an existing file");
		        return;
		      }
		      //Construct the new file that will later be renamed to the original filename.
		      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
		      BufferedReader br = new BufferedReader(new FileReader(file + "\\" + sourceNode.toString() + ".neighbours"));
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
	public Iterable<Edge> getIncomingEdges(Node destinationnode) {
		throw new RuntimeException();
	}

	public Iterable<Edge> getOutgoingEdges(Node sourcenode) {
		throw new RuntimeException();
	}

	public Double getInDegree(Node destinationNode) {
		throw new RuntimeException();
	}

	public Double getOutDegree(Node sourceNode) {
		throw new RuntimeException();
	}

	public int getNumberOfNodes() {
		return nodes.size();
	}

	public int getNumberOfEdges() {
		throw new RuntimeException();
	}

class PartialDiscEdgeList implements Iterable<Edge>{
    protected File file ;
    public PartialDiscEdgeList(File file) {
    	this.file = file ;
    }
	public Iterator<Edge> iterator(){
		return new PartialEdgeIterator(file) ;
	}	
 class PartialEdgeIterator implements Iterator<Edge>{
	   protected File file ;
	   protected Scanner scanner ;
	   protected Iterator<File> it ;
	   public PartialEdgeIterator(File file) {
		   this.file = file ; 
		   Collection<File> listFiles = FileUtils.listFiles(file, null, true);
		   for(File f : listFiles) 
		   it = listFiles.iterator();
		   try {
			   scanner = new Scanner(it.next());
		   }
		   catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	   }
	   public boolean hasNext() {
		   if(scanner.hasNext())
			   return true ;
		   else
			   return false ;
	   }
	   public Edge next(){
		   //if(!scanner.hasNextLine()) {
			  // scanner.close();
			   //try {
				  // scanner = new Scanner(it.next());
			   //}
			   //catch (FileNotFoundException e) {
					//e.printStackTrace();
			 //}		   
		   //}
		   String[] stringNodes = scanner.nextLine().split(",");
		   stringNodes[0] = stringNodes[0].strip();
		   stringNodes[1] = stringNodes[1].strip();
		   return new LoadedEdges(nodes.get(stringNodes[0]), nodes.get(stringNodes[1]));
	   }
   }
 }
}
