package core;
import java.util.*;

public abstract class GraphSignal {
	public abstract double getNodeScore(Node tempNode) ;
	public abstract void setNodeScore(Node newTempNode, Double tempDpuble) ;	
	public abstract int getSize() ;		
	public abstract Iterable<Node> getkeySet() ;     
}
