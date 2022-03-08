import java.util.*;

public class GraphSignal {
	
	protected HashMap<Node, Double> tempMap ;
    
	public GraphSignal(HashMap<Node, Double> tempMap) {
		this.tempMap = tempMap ;
	}
	
	public double getNodeScore (Node tempNode) {
		return tempMap.get(tempNode);
	}

	public void setNodescore (Node newTempNode,Double tempDpuble) {
		tempMap.put(newTempNode, tempDpuble);
	}
	
}
