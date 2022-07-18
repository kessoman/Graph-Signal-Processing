package loaded;
import java.util.HashMap;

import java.util.Set;

import core.*;

public class LoadedGraphSignal extends GraphSignal {
	
	protected HashMap<Node, Double> tempMap ;
	protected Graph graph ;

	public LoadedGraphSignal(Graph graph) {
		tempMap = new HashMap<Node, Double>()  ;
		this.graph = graph ;
	}

	public double getNodeScore(Node tempNode) {
		return tempMap.getOrDefault(tempNode, 0.);
	}

	public void setNodeScore(Node newTempNode, Double tempDpuble) {
		tempMap.put(newTempNode, tempDpuble);
	}
	
	public int getSize() {
		return tempMap.size();
	}
	
	public Iterable<Node> getkeySet(){
		return graph.getNodes();
	}
	
}
