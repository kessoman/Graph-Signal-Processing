import java.util.HashMap;
import java.util.Set;

public class LoadedGraphSignal implements GraphSignal {
	
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
	
	public HashMap<Node, Double> getHashMap (){
		return tempMap;
	}
	
	public Set<Node> getkeySet(){
		return tempMap.keySet();
	}
	
}
