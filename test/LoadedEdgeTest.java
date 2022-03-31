import org.junit.Assert;

import org.junit.Test;

import core.Edge;
import coreloaded.LoadedEdges;
import core.Node;
import coreloaded.LoadedNode;

public class LoadedEdgeTest{
	
	 Node sourceNode = new LoadedNode("sourceNode");
	 Node destinationNode = new LoadedNode("destinationNode");
	 Edge newEdge = new LoadedEdges(sourceNode, destinationNode);

		@Test
		public void testLoadedEdgesConstructor() {
			Assert.assertEquals("sourceNode", newEdge.getSource().toString());
			Assert.assertEquals("destinationNode", newEdge.getDestination().toString());
		}
		@Test(expected = IllegalArgumentException.class)
		public void testLoadedEdgesConstructorNullSourceNodes() {
			new LoadedEdges(null, destinationNode);
		}
		@Test(expected = IllegalArgumentException.class)
		public void testLoadedEdgesConstructorNullDestinationNodes() {
			new LoadedEdges(sourceNode, null);
		}
	
}