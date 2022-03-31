import org.junit.Assert;

import org.junit.Test;

import coreloaded.LoadedNode;
import core.Node;

public class NodeTest {

	@Test
	public void nodeName() {
		Node node = new LoadedNode("test");
		Assert.assertEquals("test", node.toString());
	}

}
