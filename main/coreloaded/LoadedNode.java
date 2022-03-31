package coreloaded;

import core.*;

public class LoadedNode implements Node{

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
