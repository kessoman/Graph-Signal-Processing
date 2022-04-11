package Disc;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.* ;
import java.util.* ;
import core.*;
import core.loaded.*;

public class DiscEdgeList implements Iterable<Edge> {

	public Iterator<Edge> iterator(){
		return new ListIterator<Edge>(this);
	}
	
}

