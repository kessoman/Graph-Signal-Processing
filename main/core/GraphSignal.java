package core;
import java.util.*;

public interface GraphSignal {
	public double getNodeScore(Node tempNode) ;
	public void setNodeScore(Node newTempNode, Double tempDpuble) ;	
	public int getSize() ;		
	public Iterable<Node> getkeySet() ;     
}
