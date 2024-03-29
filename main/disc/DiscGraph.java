package disc;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

import org.apache.commons.io.FileUtils;

import core.*;
import loaded.*;

public class DiscGraph extends Graph {
	private File file;
	protected DiscEdgeList edgesList;
	public HashMap<String, Node> nodes = new HashMap<String, Node>();
	public HashMap<Node, Double> inDegree = new HashMap<Node, Double>();
	public HashMap<Node, Double> outDegree = new HashMap<Node, Double>();
	public DiscGraph(File file) {
		this.file = file;
	}
	public Iterable<Edge> getEdges() {
		edgesList = new DiscEdgeList(file);
		return edgesList;
	}
	public Iterable<Node> getNodes() {
		return nodes.values();
	}
	private void calcualateInOutDegree() {
		for (Edge edge : edgesList) {
			if (!inDegree.containsKey(edge.getDestination()))
				inDegree.put(edge.getDestination(), 0.0);
			if (!outDegree.containsKey(edge.getSource()))
				outDegree.put(edge.getSource(), 0.0);
			inDegree.put(edge.getDestination(), inDegree.get(edge.getDestination()) + edge.getEdgeWeight());
			outDegree.put(edge.getSource(), outDegree.get(edge.getSource()) + edge.getEdgeWeight());
		}
	}
	public void clearGraphHistory() {
		try {
			FileUtils.cleanDirectory(file);
		} catch (IOException ex) {
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
		// throw new RuntimeException();
	}
	public int getNumberOfEdges() {
		int counter = 0;
		for (Edge edge : edgesList)
			counter++;
		return counter;
	}
	public void addEdge(Node sourceNode, Node destinationNode) {
		sourceNode = nodes.get(sourceNode.toString());
		destinationNode = nodes.get(destinationNode.toString());
		File nodeFile = new File(file + "\\" + "edges.edges");
		Path p = nodeFile.toPath();
		String s1 = sourceNode.toString() + " , " + destinationNode.toString();
		String s2 = System.lineSeparator() + sourceNode.toString() + " , " + destinationNode.toString();
		boolean result;
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
	public void removeEdge(Node sourceNode, Node destinationNode) {
		try {
			File inFile = new File(file + "\\" + "edges.edges");
			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return;
			}
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
			BufferedReader br = new BufferedReader(new FileReader(inFile));
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
					} else {
						pw.print('\n' + line); // if line = first xwris /n alliws /n + line
						pw.flush();
					}
				}
			}
			pw.close();
			br.close();
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			if (!tempFile.renameTo(inFile))
				System.out.println("Could not rename file");
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		outDegree.put(sourceNode, outDegree.get(sourceNode) - 1);
		inDegree.put(destinationNode, inDegree.get(destinationNode) - 1);
	}
	public Node getOrCreateNode(String name) {
		if (!nodes.containsKey(name)) {
			Node node = new LoadedNode(name);
			nodes.put(name, node);
			inDegree.put(node, 0.0);
			outDegree.put(node, 0.0);
		}
		return nodes.get(name);
	}
	class DiscEdgeList implements Iterable<Edge> {
		public HashMap<String, Node> discNodes = new HashMap<String, Node>();

		public DiscEdgeList(File file) {
		}
		public Iterator<Edge> iterator() {
			return new EdgeIterator(file);
		}
		public HashMap<String, Node> getIteratorNodes() {
			return discNodes;
		}
		class EdgeIterator implements Iterator<Edge> {
			protected Scanner scanner;
			protected Iterator<File> it;

			public EdgeIterator(File file) {
				it = FileUtils.iterateFiles(file, null, true);
				try {
					scanner = new Scanner(it.next());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			public boolean hasNext() {
				boolean var = scanner != null && scanner.hasNext();
				if (!var && scanner != null)
					scanner.close();
				return var;
			}
			public Edge next() {
				if (scanner == null) {
					try {
						scanner = new Scanner(it.next());
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} else {
					if (!scanner.hasNextLine()) {
						scanner.close();
						try {
							File nameFile = it.next();
							scanner = new Scanner(nameFile);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
				String[] stringNodes = scanner.nextLine().split(",");
				stringNodes[0] = stringNodes[0].strip();
				stringNodes[1] = stringNodes[1].strip();
				return new LoadedEdges(nodes.get(stringNodes[0]), nodes.get(stringNodes[1]));
			}
		}
	}
}