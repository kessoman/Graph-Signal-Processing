import org.junit.Assert;
import org.junit.Test;

import core.LoadedNode;
import core.Node;

public class LoadedNodeTest {

	@Test
	public void testLoadedNodeConstructor() {
		Assert.assertEquals("", new LoadedNode("").toString());
		Assert.assertEquals("test", new LoadedNode("test").toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLoadedNodeConstructorNullName() {
		new LoadedNode(null);
	}


}
