package disc;
import core.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

public class PartialDiscGraph extends Graph{
	private File file ;
	private HashMap<String, Node> nodes = new HashMap<String, Node>();
	public PartialDiscGraph(File file) {
		this.file = file;
	}
	public void addEdge(Node sourceNode, Node destinationNode) {
		if(!nodes.containsKey(sourceNode.toString()))
			nodes.put(sourceNode.toString(), sourceNode);
		if(!!nodes.containsKey(destinationNode.toString()))
			nodes.put(destinationNode.toString(), destinationNode);
		String s = System.lineSeparator() + sourceNode.toString() + " , " + destinationNode.toString();
		boolean result;
		Path p = file.toPath();
		try {
			result = file.createNewFile();
			if (result) {
				System.out.println("file created " + file.getCanonicalPath());
				try {
					Files.write(p, s.getBytes(), StandardOpenOption.APPEND);
				} catch (IOException e) {
					System.err.println(e);
				}
			} else {
				System.out.println("File already exist at location: " + file.getCanonicalPath());
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
		throw new RuntimeException();
	}
	public Iterable<Node> getNodes() {
		return nodes.values();
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
}
