import org.junit.Assert;

import org.junit.Test;

import core.Edge;
import core.Node;
import loaded.LoadedEdges;
import loaded.LoadedNode;

public class EdgeTest {
	
	private Node sourceNode = new LoadedNode("S");
	private Node destinationNode = new LoadedNode("D");
	
	@Test
	public void edgeSource() {
		
		Assert.assertEquals(sourceNode, new LoadedEdges(sourceNode, destinationNode).getSource());
	}
	
	@Test
	public void edgeDestination() {
		
		Assert.assertEquals(destinationNode, new LoadedEdges(sourceNode, destinationNode).getDestination());
	}

}