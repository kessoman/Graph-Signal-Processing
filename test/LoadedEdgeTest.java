import org.junit.Assert;

import org.junit.Test;

import core.Edge;
import core.Node;
import core.loaded.LoadedEdges;
import core.loaded.LoadedNode;

public class LoadedEdgeTest{
	
	 private Node sourceNode = new LoadedNode("sourceNode");
	 private Node destinationNode = new LoadedNode("destinationNode");
	 private Edge newEdge = new LoadedEdges(sourceNode, destinationNode);

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
		@Test(expected = IllegalArgumentException.class)
		public void nullEdgesTest() {
			new LoadedEdges(null,null);
		}
}