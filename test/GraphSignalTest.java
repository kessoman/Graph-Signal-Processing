import org.junit.Assert;

import org.junit.Test;

import core.GraphSignal;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;
import experiments.*;
import filters.*;
import metrics.*;
import normalizations.* ;
import disc.*;
import core.*;
import loaded.*;
public class GraphSignalTest {
	public Node node1 = new LoadedNode("node1");
	public Node node2 = new LoadedNode("node2");
	public Node node3 = new LoadedNode("node3");
	public Node node4 = new LoadedNode("node4");
	public Node node5 = new LoadedNode("node5");
	public Node node6 = new LoadedNode("node6");
	public Node node7 = new LoadedNode("node7");
	public Node node8 = new LoadedNode("node8");
	public Node node9 = new LoadedNode("node9");
	public Node node10 = new LoadedNode("node10");
	public GraphSignal graphSignal = new LoadedGraphSignal();
	public GraphSignal secondSignal = new LoadedGraphSignal();
	@Test
	public  void testAuc() {
		graphSignal.setNodeScore(node1, 0.1);
		graphSignal.setNodeScore(node2, 0.3);
		graphSignal.setNodeScore(node3, 0.5);
		graphSignal.setNodeScore(node4, 0.8);
		graphSignal.setNodeScore(node5, 0.7);
		graphSignal.setNodeScore(node6, 0.6);
		graphSignal.setNodeScore(node7, 0.4);
		graphSignal.setNodeScore(node8, 0.32);
		graphSignal.setNodeScore(node9, 0.3);
		graphSignal.setNodeScore(node10, 0.13);
		secondSignal.setNodeScore(node1, 1.);
		secondSignal.setNodeScore(node2, 1.);
		secondSignal.setNodeScore(node3, 1.);
		secondSignal.setNodeScore(node4, 1.);
		secondSignal.setNodeScore(node5, 1.);
		secondSignal.setNodeScore(node6, 0.);
		secondSignal.setNodeScore(node7, 0.);
		secondSignal.setNodeScore(node8, 0.);
		secondSignal.setNodeScore(node9, 0.);
		secondSignal.setNodeScore(node10, 0.);
		MannUTest mannUTest = new MannUTest();
		System.out.println(mannUTest.calculate(graphSignal, secondSignal));
		//for (Node node : secondSignal.getkeySet()) {
			//System.out.println("Nodename : " + " " + node.toString() + " " + "NodeScore : " + " " + graphSignal.getNodeScore(node));
			//System.out.println("Nodename : " + " " + node.toString() + " " + "NodeScore : " + " " + secondSignal.getNodeScore(node));

		//}
	}
	}
