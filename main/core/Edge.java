package core;
	
public interface Edge   {
	public Node getSource();
	public Node getDestination();
	public String toString();
	public int getEdgeWeight();
	}