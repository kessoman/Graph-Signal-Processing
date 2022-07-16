package loaded;

import core.*;

public class LoadedNode extends Node{

	String nodeName ;
	
	public LoadedNode(String name) {
		if(name == null)
			throw new IllegalArgumentException() ;
		this.nodeName = name ;
	}
	
	public String toString() {
		return nodeName ;
	}	
}
