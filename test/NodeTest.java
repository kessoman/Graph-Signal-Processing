import org.junit.Assert;

import org.junit.Test;

import core.Node;
import core.loaded.LoadedNode;

public class NodeTest {

	@Test
	public void nodeName() {
		Node node = new LoadedNode("test");
		Assert.assertEquals("test", node.toString());
	}

}
