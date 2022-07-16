package loaded;
import java.util.HashMap;

import java.util.Set;

import core.*;

public class LoadedGraphSignal extends GraphSignal {
	
	protected HashMap<Node, Double> tempMap ;

	public LoadedGraphSignal() {
		tempMap = new HashMap<Node, Double>()  ; 
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
		return tempMap.keySet();
	}
	
}
