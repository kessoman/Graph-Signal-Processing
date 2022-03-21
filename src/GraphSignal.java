import java.util.*;

public class GraphSignal {

	protected HashMap<Node, Double> tempMap;
	
	public GraphSignal(HashMap<Node, Double> tempMap) {
		this.tempMap = tempMap;
	}

	public double getNodeScore(Node tempNode) {
		return tempMap.get(tempNode);
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
	
	public  void copyMaps(GraphSignal secondSignal){
		
        for (HashMap.Entry<Node, Double> entry : secondSignal.getHashMap().entrySet()) {
        	 
            tempMap.put(entry.getKey(),
                           entry.getValue());
        }
         
	}

}
