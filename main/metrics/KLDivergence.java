package metrics;
import java.io.* ;
import java.util.* ;
import core.*;
import disc.*;
import loaded.*;

public class KLDivergence implements Metrics{
	
	protected double constant = 1.E-12;
	
	public double calculate(GraphSignal trainSignal, GraphSignal testSignal) {
		double result = 0 ;
		for(Node node : trainSignal.getkeySet()) {
			double division = (trainSignal.getNodeScore(node)/(testSignal.getNodeScore(node) + constant)) + constant ;
			result += trainSignal.getNodeScore(node) * Math.log(division);
		}
		return result ;
	}
}
