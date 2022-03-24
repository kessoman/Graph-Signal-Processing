import java.util.*;

public class GraphSignal {

	protected HashMap<Node, Double> tempMap;
	
	public GraphSignal() {
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
