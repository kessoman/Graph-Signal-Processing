package core;
import java.io.* ;
import java.util.* ;
import core.*;
import disc.*;
import loaded.*;

public interface Metrics {
	public double calculate(GraphSignal nextSignal, GraphSignal previousSignal) ;
}
