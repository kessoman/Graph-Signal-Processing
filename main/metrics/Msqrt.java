package metrics;
import java.io.* ;
import java.util.* ;
import core.*;
import disc.*;
import loaded.*;
import core.*;

public class Msqrt extends Metric {
	public double calculate(GraphSignal nextSignal, GraphSignal previousSignal) {
		double result = 0;
		for (Node node : nextSignal.getkeySet()) 
			result += Math.pow((previousSignal.getNodeScore(node) - nextSignal.getNodeScore(node)), 2);
		double msqrt = Math.pow (result / (nextSignal.getSize()), 0.5);
		return msqrt ;
	}
}
