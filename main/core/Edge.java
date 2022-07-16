package core;
	
public abstract class Edge   {
	public abstract Node getSource();
	public abstract Node getDestination();
	public abstract String toString();
	public abstract double getEdgeWeight();
	}